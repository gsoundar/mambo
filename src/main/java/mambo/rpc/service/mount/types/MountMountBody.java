package mambo.rpc.service.mount.types;

import java.nio.ByteBuffer;

import mambo.rpc.auth.AuthNone;
import mambo.rpc.auth.OpaqueAuth;
import mambo.rpc.msg.CallMessageBody;
import mambo.rpc.service.mount.MountV3;
import mambo.rpc.xdr.Xdr;

public class MountMountBody extends CallMessageBody {

	final String path;
	
	public MountMountBody(OpaqueAuth credentials, String path) {
		super(MountV3.RPC_VERSION,
				MountV3.PROGRAM_ID,
				MountV3.PROGRAM_VERSION,
				MountMount.PROCEDURE_ID,
				credentials,
				new AuthNone()
				);
		this.path = path;
	}
	
	public ByteBuffer serializeToXdr(ByteBuffer buffer) {
		super.serializeToXdr(buffer);
		Xdr.encodeString(buffer, path);
		return buffer;
	}
	
}
