package mambo.rpc.service.nfsv3.types;

import java.nio.ByteBuffer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import mambo.rpc.function.CallParameter;
import mambo.rpc.xdr.Xdr;

public class Nfs3DirectoryOperationArgs implements CallParameter {

	Nfs3FileHandle handle;
	String filename;
	
	public static final Logger LOG = LoggerFactory.getLogger(Nfs3DirectoryOperationArgs.class);
	
	public Nfs3DirectoryOperationArgs(Nfs3FileHandle handle, String filename) {
		this.handle = handle;
		this.filename = filename;
	}
	
	public Nfs3DirectoryOperationArgs(ByteBuffer buffer) {
		handle = new Nfs3FileHandle(buffer);
		filename = Xdr.decodeString(buffer);
	}

	public Nfs3FileHandle getHandle() {
		return handle;
	}
	
	public String getFilename() {
		return filename;
	}
	
	@Override
	public ByteBuffer serializeToXdr(ByteBuffer buffer) {
		handle.serializeToXdr(buffer);
		Xdr.encodeString(buffer, filename);
		return buffer;
	}
	
}
