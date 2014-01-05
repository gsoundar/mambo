package mambo.rpc.service.rpcbind.types;

import java.nio.ByteBuffer;

import mambo.rpc.function.CallParameter;
import mambo.rpc.function.ReturnValue;
import mambo.rpc.xdr.Xdr;

public class XdrInt implements CallParameter, ReturnValue {

	final int value;
	
	public XdrInt(int value) {
		this.value = value;
	}
	
	public XdrInt(ByteBuffer buffer) {
		this.value = Xdr.decodeInteger(buffer);
	}
	
	public int getValue() {
		return value;
	}
	
	public ByteBuffer serializeToXdr(ByteBuffer buffer) {
		Xdr.encodeInt(buffer, value);
		return buffer;
	}

}
