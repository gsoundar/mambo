package mambo.rpc.service.mount.types;

import java.nio.ByteBuffer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import mambo.rpc.function.CallParameter;
import mambo.rpc.function.ReturnValue;
import mambo.rpc.service.mount.MountStatus;
import mambo.rpc.xdr.Xdr;

public class MountResult implements CallParameter, ReturnValue {

	MountStatus status;
	byte[] handle;
	int authFlavors[];

	public static final Logger LOG = LoggerFactory.getLogger(MountResult.class);
	
	public MountResult(MountStatus status, byte[] handle, int[] authFlavors) {
		this.status = status;
		this.handle = handle;
		this.authFlavors = authFlavors;
	}
	
	public MountResult(ByteBuffer buffer) {
		status = new MountStatus(Xdr.decodeInteger(buffer));
		if(status.equals(MountStatus.MNT3_OK)) {
			this.handle = Xdr.decodeDynamicOpaque(buffer);
			this.authFlavors = Xdr.decodeIntegerArray(buffer);
		} else {
			LOG.info("Mount returned with status NOT_OK " + status.toString());
			this.handle = null;
			this.authFlavors = null;
		}
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
	
	public ByteBuffer serializeToXdr(ByteBuffer buffer) {
		Xdr.encodeDynamicOpaque(buffer, handle);
		Xdr.encodeIntArray(buffer, authFlavors);
		return buffer;
	}

}
