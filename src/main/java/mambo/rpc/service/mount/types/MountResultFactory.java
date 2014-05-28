package mambo.rpc.service.mount.types;

import java.nio.ByteBuffer;

import mambo.rpc.function.CallParameter;
import mambo.rpc.function.CallParameterFactory;
import mambo.rpc.function.ReturnValue;
import mambo.rpc.function.ReturnValueFactory;

public class MountResultFactory implements CallParameterFactory, ReturnValueFactory {

	public ReturnValue buildReturnValueFromXdr(ByteBuffer buffer) {
		return new MountResult(buffer);
	}

	public CallParameter buildCallParameterFromXdr(ByteBuffer buffer) {
		return new MountResult(buffer);
	}

}
