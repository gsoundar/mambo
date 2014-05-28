package mambo.rpc.service.mount.types;

import java.nio.ByteBuffer;

import mambo.rpc.function.CallParameter;
import mambo.rpc.function.CallParameterFactory;
import mambo.rpc.function.ReturnValue;
import mambo.rpc.function.ReturnValueFactory;

public class XdrVoidFactory implements CallParameterFactory, ReturnValueFactory {

	public ReturnValue buildReturnValueFromXdr(ByteBuffer buffer) {
		return new XdrVoid();
	}

	public CallParameter buildCallParameterFromXdr(ByteBuffer buffer) {
		return new XdrVoid();
	}

}
