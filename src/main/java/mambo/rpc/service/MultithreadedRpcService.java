package mambo.rpc.service;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import mambo.rpc.RpcMessage;
import mambo.rpc.RpcMessageFactory;
import mambo.rpc.function.RpcCallHandle;
import mambo.rpc.function.RpcFunction;
import mambo.rpc.function.RpcFunctionState;
import mambo.rpc.msg.CallMessage;
import mambo.rpc.msg.ReplyMessage;
import mambo.rpc.xdr.Xdr;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MultithreadedRpcService {

	
	public static final Logger LOG = LoggerFactory.getLogger(MultithreadedRpcService.class);
	public static final int NUM_CONNECTIONS = 1;
	
	final String hostname;
	final int port;
	
	final ExecutorService pool;
	final Queue<SocketChannel> connections;
	
	final Map<Integer, RpcFunction> submitted;
	final Map<Integer, RpcFunction> inprogress;
	//Maybe keep 100 most recent entries

	class ServiceTask implements Callable<RpcCallHandle> {

		final RpcFunction function;
		
		public ServiceTask(RpcFunction function) {
			this.function = function;
		}
		
		public RpcCallHandle call() throws Exception {

			int RPC_LAST_STREAM_FRAGMENT = (1 << 31);
			int RPC_SIZE_MASK = (~RPC_LAST_STREAM_FRAGMENT);
			int bytesSent;
						
			RpcCallHandle handle = new RpcCallHandle(function);
			
			/* Get a connection to the server */
			/*SocketChannel conn = connections.poll();
			if(conn == null) {
				LOG.info("No available connection, so make a new one");
				conn = SocketChannel.open();
				conn.connect(new InetSocketAddress(hostname, port));
			}*/
			
			SocketChannel conn = SocketChannel.open();
			conn.connect(new InetSocketAddress(hostname, port));
			
			/* Make a RPC call */
			{
				/* 1-of-4 Serialize the contents of the RPC call */
				CallMessage callMessage = handle.getFunction().getCall();
				ByteBuffer callMessageBuffer = Xdr.getBuffer();
				callMessage.serializeToXdr(callMessageBuffer);
				int callMessageBufferSize = callMessageBuffer.position();
				handle.setState(RpcFunctionState.FUNCTION_ENQUEUED);
				
				/* 2-of-4 Make request marker - send as one message */
				ByteBuffer callHeaderBuffer = ByteBuffer.allocate(4);
				callHeaderBuffer.putInt(RPC_LAST_STREAM_FRAGMENT | (RPC_SIZE_MASK & callMessageBufferSize));
				callHeaderBuffer.flip();
				bytesSent = conn.write(callHeaderBuffer);
				assert(bytesSent == 4);
				LOG.info("Sent call message header to server bytes=" + bytesSent);
				
				/* 3-of-4 Send call body */
				callMessageBuffer.flip();
				bytesSent = conn.write(callMessageBuffer);
				assert(bytesSent == callMessageBufferSize);
				LOG.info("Sent call message body to server bytes=" + bytesSent);
				
				/* 4-of-4 Update state */
				handle.setState(RpcFunctionState.FUNCTION_SUBMITTED);
				
			}
			
			/* Get RPC reply */
			{
				/* 1-of-5 Read record marker */
				ByteBuffer replyHeader = ByteBuffer.allocate(4);
				int bytesRead = conn.read(replyHeader);
				assert(bytesRead == 4);
				replyHeader.flip();
				LOG.info("Got reply message header from server");
				
				/* 2-of-5 Figure out how much to read */
				int recordMarker = replyHeader.getInt();
				int replyMessageSize = recordMarker & RPC_SIZE_MASK;
				assert((recordMarker & RPC_LAST_STREAM_FRAGMENT) != 0);
				
				/* 3-of-5 Read the reply message */
				ByteBuffer replyMessageBuffer = Xdr.getBuffer();
				int bytesRecvd = conn.read(replyMessageBuffer);
				assert(bytesRecvd == replyMessageSize);
				replyMessageBuffer.flip();
				handle.setState(RpcFunctionState.REPLY_RECEIVED);
				LOG.info("Got reply body from server");
				
				/* 4-of-5 Construct the reply object */
				RpcMessageFactory messageFactory = RpcMessageFactory.getInstance();
				RpcMessage msg = messageFactory.buildMessageFromXdr(replyMessageBuffer, function.getCallParameterFactory(), function.getReturnValueFactory());
				handle.getFunction().setReply((ReplyMessage) msg);
				LOG.info("Constructed reply message from bytes");
				
				/* 5-of-5 Update function with new reply */
				handle.setState(RpcFunctionState.FUNCTION_COMPLETE);

			}
			
			/* Put the connection into the free list */
			//connections.add(conn);
			conn.close();
			
			/* Signal done */
			handle.signalCompletion();
			
			return handle;
			
		}
		
	}
	
	public MultithreadedRpcService(String hostname, int port) {
		this.hostname = hostname;
		this.port = port;
		
		/* Create different staging areas */
		submitted = new ConcurrentSkipListMap<Integer, RpcFunction>();
		inprogress = new ConcurrentSkipListMap<Integer, RpcFunction>();
		
		/* Make the connections */
		pool = Executors.newFixedThreadPool(NUM_CONNECTIONS);
		connections = new ConcurrentLinkedQueue<SocketChannel>();
		
	}
	
	public RpcCallHandle submit(RpcFunction function) {
		try {
			Future<RpcCallHandle> future = pool.submit(new ServiceTask(function));
			RpcCallHandle handle = future.get();
			return handle;
		} catch(ExecutionException e) {
			LOG.error("Caught an execution exception");
			e.printStackTrace();
		} catch(InterruptedException e) {
			LOG.error("Caught an interrupted exception");
			e.printStackTrace();
		}
		
		throw new RuntimeException("Executor failed");
		
	}
	
	public void shutdown() {
		LOG.info("Shutting down thread pool");
		pool.shutdown();
	}
	
	
}
