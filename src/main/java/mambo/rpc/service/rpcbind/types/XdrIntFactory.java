package mambo.rpc.service.rpcbind.types;

import java.nio.ByteBuffer;

import mambo.rpc.function.ReturnValue;
import mambo.rpc.function.ReturnValueFactory;

public class XdrIntFactory implements ReturnValueFactory {

	public ReturnValue buildReturnValueFromXdr(ByteBuffer buffer) {
		return new XdrInt(buffer);
	}

}
