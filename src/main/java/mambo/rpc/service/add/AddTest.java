package mambo.rpc.service.add;

import java.net.InetSocketAddress;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import mambo.rpc.AcceptStatus;
import mambo.rpc.ReplyStatus;
import mambo.rpc.function.RpcCallHandle;
import mambo.rpc.msg.AcceptedReplyBody;
import mambo.rpc.msg.ReplyMessage;
import mambo.rpc.msg.ReplyMessageBody;
import mambo.rpc.msg.SuccessfulReplyBody;
import mambo.rpc.service.rpcbind.RpcBindV4;

public class AddTest {

	public static final Logger LOG = LoggerFactory.getLogger(AddTest.class);
	
	public static void main(String args[]) throws Exception {
		
		int numRequests = 10000;
		long startTime, stopTime;
		
		/* Ask RPC bind for the address of Add service */
		RpcBindV4 rpcbind = new RpcBindV4("localhost");
		String address = rpcbind.getAddress(AddService.PROGRAM_ID, AddService.PROGRAM_VERSION);
		LOG.info("Add service is running on " + address);
		
		/* Connect to the Add service */
		InetSocketAddress sock = rpcbind.convertToSocketAddress(address);
		AddService service = new AddService(sock.getHostName(), sock.getPort());
		
		/* Send a bunch of add requests */
		startTime = System.currentTimeMillis();
		for(int i = 0; i < numRequests; i++) {

			RpcCallHandle handle = service.submit(new AddFunction(i, i+1));
			handle.waitForCompletion();
			
			/* Process reply */
			ReplyMessage reply = handle.getFunction().getReply();
			ReplyMessageBody replyBody = reply.getMessageBody();
			if(replyBody.getReplyStatus().equals(ReplyStatus.MSG_ACCEPTED)) {
				AcceptedReplyBody acceptedBody = (AcceptedReplyBody) replyBody;
				if(acceptedBody.getAcceptStatus().equals(AcceptStatus.SUCCESS)) {
					SuccessfulReplyBody successBody = (SuccessfulReplyBody) acceptedBody;
					Int returnValue = (Int) successBody.getReturnValue();
					LOG.debug("Got a reply of " + returnValue.x);
				}
			}
		}
		stopTime = System.currentTimeMillis();
		System.out.printf("Avg request time is %5.3f ms\n",  (stopTime-startTime+0.0)/numRequests);
		
		rpcbind.shutdown();
		service.shutdown();
		
	}
	
}
