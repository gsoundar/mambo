package mambo.rpc.msg;

import java.nio.ByteBuffer;

import mambo.rpc.RejectStatus;
import mambo.rpc.ReplyStatus;
import mambo.rpc.xdr.Xdr;

public abstract class RejectedReplyBody extends ReplyMessageBody {

	final RejectStatus rejectStatus;
	
	public RejectedReplyBody(RejectStatus rejectStatus) {
		super(ReplyStatus.MSG_DENIED);
		this.rejectStatus = rejectStatus;
	}

	public RejectStatus getRejectStatus() {
		return rejectStatus;
	}
	
	public ByteBuffer serializeToXdr(ByteBuffer buffer) {
		super.serializeToXdr(buffer);
		Xdr.encodeInt(buffer, rejectStatus.getValue());
		return buffer;
	}

}
