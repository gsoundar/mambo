package mambo.rpc.service.nfsv3;

import mambo.rpc.msg.CallMessage;

public class Nfs3CallMessage extends CallMessage {

	public Nfs3CallMessage(Nfs3CallMessageBody callMessageBody) {
		super(Nfs3.getSequencer().getNextXid(), callMessageBody);
	}
	
}
