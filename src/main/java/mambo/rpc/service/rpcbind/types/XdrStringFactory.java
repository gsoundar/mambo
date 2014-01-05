package mambo.rpc.service.rpcbind.types;

import java.nio.ByteBuffer;

import mambo.rpc.function.ReturnValue;
import mambo.rpc.function.ReturnValueFactory;

public class XdrStringFactory implements ReturnValueFactory {

	public ReturnValue buildReturnValueFromXdr(ByteBuffer buffer) {
		return new XdrString(buffer);
	}

}
