package mambo.rpc.service.nfsv3.msg;

import java.nio.ByteBuffer;

import mambo.rpc.auth.OpaqueAuth;
import mambo.rpc.service.nfsv3.Nfs3CallMessageBody;
import mambo.rpc.service.nfsv3.types.Nfs3FileHandle;
import mambo.rpc.service.nfsv3.types.Nfs3OperationType;

public class Nfs3GetAttrRequest extends Nfs3CallMessageBody {

	final Nfs3FileHandle handle;
	
	public Nfs3GetAttrRequest(OpaqueAuth credentials, Nfs3FileHandle handle) {
		super(Nfs3OperationType.NFS3_GETATTR, credentials);
		this.handle = handle;
	}

	public Nfs3GetAttrRequest(ByteBuffer buffer) {
		super(buffer);
		handle = new Nfs3FileHandle(buffer);
	}
	
	@Override
	public ByteBuffer serializeToXdr(ByteBuffer buffer) {
		super.serializeToXdr(buffer);
		handle.serializeToXdr(buffer);
		return buffer;
	}

	
}
