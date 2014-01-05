package mambo.rpc.msg;

import java.nio.ByteBuffer;

import mambo.rpc.MessageBody;
import mambo.rpc.ReplyStatus;
import mambo.rpc.xdr.Xdr;

public class ReplyMessageBody extends MessageBody {

	final ReplyStatus replyStatus;
	
	public ReplyMessageBody(ReplyStatus replyStatus) {
		this.replyStatus = replyStatus;
	}
	
	public ReplyMessageBody(ByteBuffer buffer) {
		replyStatus = new ReplyStatus(Xdr.decodeInteger(buffer));
	}
	
	public ReplyStatus getReplyStatus() {
		return replyStatus;
	}
	
	public ByteBuffer serializeToXdr(ByteBuffer buffer) {
		Xdr.encodeInt(buffer, replyStatus.getValue());
		return buffer;
	}

}
