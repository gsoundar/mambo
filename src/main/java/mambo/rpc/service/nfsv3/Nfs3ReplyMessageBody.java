package mambo.rpc.service.nfsv3;

import java.nio.ByteBuffer;

import mambo.rpc.ReplyStatus;
import mambo.rpc.msg.ReplyMessageBody;


public class Nfs3ReplyMessageBody extends ReplyMessageBody {

	public Nfs3ReplyMessageBody(ReplyStatus status) {
		super(status);
	}
	
	public Nfs3ReplyMessageBody(ByteBuffer buffer) {
		super(buffer);
	}
	
}
