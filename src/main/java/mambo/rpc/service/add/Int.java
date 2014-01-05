package mambo.rpc.service.add;

import java.nio.ByteBuffer;

import mambo.rpc.function.ReturnValue;
import mambo.rpc.xdr.Xdr;

public class Int implements ReturnValue {

	final int x;
	
	public Int(int x) {
		this.x = x;
	}
	
	public int getValue() {
		return x;
	}
	
	public ByteBuffer serializeToXdr(ByteBuffer buffer) {
		Xdr.encodeInt(buffer, x);
		return buffer;
	}

}
