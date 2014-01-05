package mambo.rpc.msg;

import mambo.rpc.MessageType;
import mambo.rpc.RpcMessage;

public class CallMessage extends RpcMessage {
	
	public CallMessage(Integer xid, CallMessageBody body) {
		super(MessageType.CALL, xid, body);
	}
	
	public CallMessageBody getMessageBody() {
		return (CallMessageBody) body;
	}

}
