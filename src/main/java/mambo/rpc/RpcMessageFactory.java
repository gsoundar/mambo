package mambo.rpc;

import java.nio.ByteBuffer;

import mambo.rpc.function.CallParameterFactory;
import mambo.rpc.function.ReturnValueFactory;
import mambo.rpc.msg.CallMessage;
import mambo.rpc.msg.MessageBodyFactory;
import mambo.rpc.msg.ReplyMessage;
import mambo.rpc.xdr.Xdr;

public class RpcMessageFactory {

	static final RpcMessageFactory singleton;
	
	static {
		singleton = new RpcMessageFactory();
	}
	
	private RpcMessageFactory() {
		
	}
	
	public static RpcMessageFactory getInstance() {
		return singleton;
	}
	
	public RpcMessage buildMessageFromXdr(ByteBuffer buffer, CallParameterFactory callFactory, ReturnValueFactory replyFactory) {
		Integer xid = Xdr.decodeInteger(buffer);	
		MessageType type = new MessageType(Xdr.decodeInteger(buffer));

		/* CALL message */
		if(type.equals(MessageType.CALL)) {
			return buildCallMessageFromXdr(buffer, xid, callFactory);
		} 
		/* REPLY message */
		else if(type.equals(MessageType.REPLY)) {
			return buildReplyMessageFromXdr(buffer, xid, replyFactory);
		} 
		/* Something was garbled */
		else {
			throw new RuntimeException("Unknown message type!");
		}
	}
	
	public CallMessage buildCallMessageFromXdr(ByteBuffer buffer, Integer xid, CallParameterFactory factory) {
		MessageBodyFactory msgFactory = MessageBodyFactory.getInstance();
		return new CallMessage(xid, msgFactory.buildFromXdr(buffer, factory));
	}
	
	public ReplyMessage buildReplyMessageFromXdr(ByteBuffer buffer, Integer xid, ReturnValueFactory factory) {
		MessageBodyFactory msgFactory = MessageBodyFactory.getInstance();
		return new ReplyMessage(xid, msgFactory.buildFromXdr(buffer, factory));
	}
	
}
