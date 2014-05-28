package mambo.rpc.service.nfsv3.types;

import java.nio.ByteBuffer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import mambo.rpc.function.CallParameter;
import mambo.rpc.function.ReturnValue;
import mambo.rpc.xdr.Xdr;

public class Nfs3FileHandle implements CallParameter, ReturnValue {

	final byte[] handle;
	
	public static final Logger LOG = LoggerFactory.getLogger(Nfs3FileHandle.class);
	
	public Nfs3FileHandle(byte[] handle) {
		this.handle = handle;
	}
	
	public Nfs3FileHandle(ByteBuffer buffer) {
		handle = Xdr.decodeDynamicOpaque(buffer);
	}
	
	public byte[] getHandle() {
		return handle;
	}
	
	public String getHandleAsString() {
		StringBuffer buffer = new StringBuffer();
		byte[] handle = getHandle();
		for(byte b : handle) {
			buffer.append(String.format("%02x", b));
		}
		return buffer.toString();
	}

	@Override
	public ByteBuffer serializeToXdr(ByteBuffer buffer) {
		Xdr.encodeDynamicOpaque(buffer, handle);
		return buffer;
	}
	
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("Handle: ").append(getHandleAsString()).append("\n");
		return buffer.toString();
	}
	
}
