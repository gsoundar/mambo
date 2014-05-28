package mambo.rpc.service.nfsv3.types;

import java.nio.ByteBuffer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import mambo.rpc.function.CallParameter;
import mambo.rpc.function.ReturnValue;
import mambo.rpc.xdr.Xdr;

public class Nfs3Time implements CallParameter, ReturnValue {

	int seconds;
	int nseconds;
	
	public static final Logger LOG = LoggerFactory.getLogger(Nfs3Time.class);
	
	public Nfs3Time(ByteBuffer buffer) {
		seconds = Xdr.decodeInteger(buffer);
		nseconds = Xdr.decodeInteger(buffer);
	}
	
	public int getSeconds() {
		return seconds;
	}

	public int getNanoSeconds() {
		return nseconds;
	}
	
	@Override
	public ByteBuffer serializeToXdr(ByteBuffer buffer) {
		Xdr.encodeInt(buffer, seconds);
		Xdr.encodeInt(buffer, nseconds);
		return buffer;
	}
	
	public String toString() {
		return "[" + seconds + "," + nseconds + "]";
	}
	
	
}
