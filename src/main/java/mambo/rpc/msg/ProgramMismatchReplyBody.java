package mambo.rpc.msg;

import java.nio.ByteBuffer;

import mambo.rpc.AcceptStatus;
import mambo.rpc.auth.OpaqueAuth;

public class ProgramMismatchReplyBody extends AcceptedReplyBody {
	
	final MismatchInfo mismatchInfo;
	
	public ProgramMismatchReplyBody(OpaqueAuth verf, MismatchInfo mismatchInfo) {
		super(AcceptStatus.PROG_MISMATCH, verf);
		this.mismatchInfo = mismatchInfo;
	}
	
	public ProgramMismatchReplyBody(ByteBuffer buffer, OpaqueAuth verf) {
		super(AcceptStatus.PROG_MISMATCH, verf);
		this.mismatchInfo = new MismatchInfo(buffer);
	}
	
	public ByteBuffer serializeToXdr(ByteBuffer buffer) {
		super.serializeToXdr(buffer);
		mismatchInfo.serializeToXdr(buffer);
		return buffer;
	}

}
