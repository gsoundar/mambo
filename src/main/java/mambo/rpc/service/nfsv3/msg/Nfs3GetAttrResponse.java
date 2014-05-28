package mambo.rpc.service.nfsv3.msg;

import java.nio.ByteBuffer;

import mambo.rpc.auth.OpaqueAuth;
import mambo.rpc.function.ReturnValue;
import mambo.rpc.service.nfsv3.types.Nfs3FileAttributes;
import mambo.rpc.service.nfsv3.types.Nfs3Status;
import mambo.rpc.xdr.Xdr;

public class Nfs3GetAttrResponse implements ReturnValue {

	final Nfs3Status status;
	final Nfs3FileAttributes attributes;
	
	public Nfs3GetAttrResponse(OpaqueAuth credentials, Nfs3Status status, Nfs3FileAttributes attributes) {
		this.status = status;
		this.attributes = attributes;
	}

	public Nfs3GetAttrResponse(ByteBuffer buffer) {
		this.status = new Nfs3Status(Xdr.decodeInteger(buffer));
		this.attributes = new Nfs3FileAttributes(buffer);
	}
	
	public Nfs3Status getStatus() {
		return status;
	}
	
	public Nfs3FileAttributes getAttributes() {
		return attributes;
	}
	
	@Override
	public ByteBuffer serializeToXdr(ByteBuffer buffer) {
		Xdr.encodeInt(buffer, 0); //TODO: Fix
		attributes.serializeToXdr(buffer);
		return buffer;
	}

	
}
