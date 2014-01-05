package mambo.rpc.msg;

import java.nio.ByteBuffer;

import mambo.rpc.xdr.Xdr;
import mambo.rpc.xdr.XdrSerializable;

public class MismatchInfo implements XdrSerializable {

	final Integer x;
	final Integer y;
	
	public MismatchInfo(Integer x, Integer y) {
		this.x = x;
		this.y = y;
	}

	public MismatchInfo(ByteBuffer buffer) {
		this.x = Xdr.decodeInteger(buffer);
		this.y = Xdr.decodeInteger(buffer);
	}
	
	public Integer getX() {
		return x;
	}
	
	public Integer getY() {
		return y;
	}

	public ByteBuffer serializeToXdr(ByteBuffer buffer) {
		Xdr.encodeInt(buffer, x);
		Xdr.encodeInt(buffer, y);
		return buffer;
	}
	
}
