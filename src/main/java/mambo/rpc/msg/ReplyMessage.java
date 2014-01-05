package mambo.rpc.msg;

import mambo.rpc.MessageType;
import mambo.rpc.RpcMessage;

public class ReplyMessage extends RpcMessage {
	
	public ReplyMessage(Integer xid, ReplyMessageBody body) {
		super(MessageType.REPLY, xid, body);
	}
	
	public ReplyMessageBody getMessageBody() {
		return (ReplyMessageBody) body;
	}

}
