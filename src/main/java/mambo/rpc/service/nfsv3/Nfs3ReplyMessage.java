package mambo.rpc.service.nfsv3;

import mambo.rpc.msg.ReplyMessage;

public class Nfs3ReplyMessage extends ReplyMessage {

	public Nfs3ReplyMessage(Integer xid, Nfs3ReplyMessageBody messageBody) {
		super(xid, messageBody);
	}
	
}
