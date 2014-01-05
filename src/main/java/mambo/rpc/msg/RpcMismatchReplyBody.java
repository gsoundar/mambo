package mambo.rpc.msg;

import java.nio.ByteBuffer;

import mambo.rpc.RejectStatus;

public class RpcMismatchReplyBody extends RejectedReplyBody {

	final MismatchInfo mismatchInfo;
	
	public RpcMismatchReplyBody(MismatchInfo mismatchInfo) {
		super(RejectStatus.RPC_MISMATCH);
		this.mismatchInfo = mismatchInfo;
	}

	public MismatchInfo getMismatchInfo() {
		return mismatchInfo;
	}
	
	public ByteBuffer serializeToXdr(ByteBuffer buffer) {
		super.serializeToXdr(buffer);
		mismatchInfo.serializeToXdr(buffer);
		return buffer;
	}

}
