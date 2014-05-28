package mambo.rpc.service.nfsv3.msg;

import java.nio.ByteBuffer;

import mambo.rpc.auth.OpaqueAuth;
import mambo.rpc.service.nfsv3.Nfs3CallMessageBody;
import mambo.rpc.service.nfsv3.types.Nfs3DirectoryOperationArgs;
import mambo.rpc.service.nfsv3.types.Nfs3FileHandle;
import mambo.rpc.service.nfsv3.types.Nfs3OperationType;

public class Nfs3LookupRequest extends Nfs3CallMessageBody {

	final Nfs3DirectoryOperationArgs what;
	
	public Nfs3LookupRequest(OpaqueAuth credentials, Nfs3DirectoryOperationArgs what) {
		super(Nfs3OperationType.NFS3_LOOKUP, credentials);
		this.what = what;
	}

	public Nfs3LookupRequest(ByteBuffer buffer) {
		super(buffer);
		what = new Nfs3DirectoryOperationArgs(buffer);
	}
	
	@Override
	public ByteBuffer serializeToXdr(ByteBuffer buffer) {
		super.serializeToXdr(buffer);
		what.serializeToXdr(buffer);
		return buffer;
	}

	
}
