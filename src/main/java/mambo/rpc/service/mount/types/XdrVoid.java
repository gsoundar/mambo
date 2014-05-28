package mambo.rpc.service.mount.types;

import java.nio.ByteBuffer;

import mambo.rpc.function.CallParameter;
import mambo.rpc.function.ReturnValue;

public class XdrVoid implements CallParameter, ReturnValue {

	public ByteBuffer serializeToXdr(ByteBuffer buffer) {
		return buffer;
	}

}
