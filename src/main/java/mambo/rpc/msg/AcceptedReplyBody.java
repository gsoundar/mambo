package mambo.rpc.msg;

import java.nio.ByteBuffer;

import mambo.rpc.AcceptStatus;
import mambo.rpc.ReplyStatus;
import mambo.rpc.auth.OpaqueAuth;
import mambo.rpc.xdr.Xdr;

public abstract class AcceptedReplyBody extends ReplyMessageBody {

	final AcceptStatus acceptStatus;
	final OpaqueAuth verf;
	
	public AcceptedReplyBody(AcceptStatus acceptStatus, OpaqueAuth verf) {
		super(ReplyStatus.MSG_ACCEPTED);
		this.acceptStatus = acceptStatus;
		this.verf = verf;
	}

	public AcceptStatus getAcceptStatus() {
		return acceptStatus;
	}
	
	public OpaqueAuth getVerf() {
		return verf;
	}
	
	public ByteBuffer serializeToXdr(ByteBuffer buffer) {
		super.serializeToXdr(buffer);
		Xdr.encodeInt(buffer, acceptStatus.getValue());
		verf.serializeToXdr(buffer);
		return buffer;
	}

}
