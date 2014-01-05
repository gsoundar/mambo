package mambo.rpc.msg;

import java.nio.ByteBuffer;

import mambo.rpc.AcceptStatus;
import mambo.rpc.auth.OpaqueAuth;

public class OtherAcceptedReplyBody extends AcceptedReplyBody {
	
	public OtherAcceptedReplyBody(AcceptStatus acceptStatus, OpaqueAuth verf) {
		super(acceptStatus, verf);
	}
	
	public OtherAcceptedReplyBody(ByteBuffer buffer, AcceptStatus acceptStatus, OpaqueAuth verf) {
		super(acceptStatus, verf);
	}
	
	public ByteBuffer serializeToXdr(ByteBuffer buffer) {
		super.serializeToXdr(buffer);
		return buffer;
	}

}
