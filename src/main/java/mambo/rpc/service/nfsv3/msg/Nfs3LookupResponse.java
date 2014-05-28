package mambo.rpc.service.nfsv3.msg;

import java.nio.ByteBuffer;

import mambo.rpc.auth.OpaqueAuth;
import mambo.rpc.function.ReturnValue;
import mambo.rpc.service.nfsv3.types.Nfs3FileHandle;
import mambo.rpc.service.nfsv3.types.Nfs3PostOpAttributes;
import mambo.rpc.service.nfsv3.types.Nfs3Status;
import mambo.rpc.xdr.Xdr;

public class Nfs3LookupResponse implements ReturnValue {

	final Nfs3Status status;
	final Nfs3FileHandle handle;
	final Nfs3PostOpAttributes objectAttributes;
	final Nfs3PostOpAttributes directoryAttributes;
	
	public Nfs3LookupResponse(OpaqueAuth credentials, Nfs3Status status, Nfs3FileHandle handle, Nfs3PostOpAttributes objectAttributes, Nfs3PostOpAttributes directoryAttributes) {
		this.status = status;
		this.handle = handle;
		this.objectAttributes = objectAttributes;
		this.directoryAttributes = directoryAttributes;
	}

	public Nfs3LookupResponse(ByteBuffer buffer) {
		this.status = new Nfs3Status(Xdr.decodeInteger(buffer));
		this.handle = new Nfs3FileHandle(buffer);
		this.objectAttributes = new Nfs3PostOpAttributes(buffer);
		this.directoryAttributes = new Nfs3PostOpAttributes(buffer);
	}
	
	public Nfs3Status getStatus() {
		return status;
	}

	public Nfs3FileHandle getHandle() {
		return handle;
	}
	
	public Nfs3PostOpAttributes getObjectAttributes() {
		return objectAttributes;
	}
	
	public Nfs3PostOpAttributes getDirectoryAttributes() {
		return directoryAttributes;
	}
	
	@Override
	public ByteBuffer serializeToXdr(ByteBuffer buffer) {
		Xdr.encodeInt(buffer, status.getValue());
		if(status == Nfs3Status.NFS_OK) {
			handle.serializeToXdr(buffer);
			objectAttributes.serializeToXdr(buffer);
			directoryAttributes.serializeToXdr(buffer);
		} 
		else {
			directoryAttributes.serializeToXdr(buffer);
		}
		return buffer;
	}

	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("LOOKUP Response:").append("\n");
		buffer.append(handle).append("\n");
		buffer.append(objectAttributes);
		buffer.append(directoryAttributes);
		return buffer.toString();
	}
	
}
