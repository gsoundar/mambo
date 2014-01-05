package mambo.rpc.service.add;

import java.nio.ByteBuffer;

import mambo.rpc.function.CallParameter;
import mambo.rpc.xdr.Xdr;

public class IntPair implements CallParameter {

	final int x;
	final int y;
	
	public IntPair(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public ByteBuffer serializeToXdr(ByteBuffer buffer) {
		Xdr.encodeInt(buffer, x);
		Xdr.encodeInt(buffer, y);
		return buffer;
	}

}
