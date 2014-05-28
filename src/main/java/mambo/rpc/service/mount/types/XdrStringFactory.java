package mambo.rpc.service.mount.types;

import java.nio.ByteBuffer;

import mambo.rpc.function.CallParameter;
import mambo.rpc.function.CallParameterFactory;
import mambo.rpc.function.ReturnValue;
import mambo.rpc.function.ReturnValueFactory;

public class XdrStringFactory implements CallParameterFactory, ReturnValueFactory {

	@Override
	public CallParameter buildCallParameterFromXdr(ByteBuffer buffer) {
		return new XdrString(buffer);
	}
	
	public ReturnValue buildReturnValueFromXdr(ByteBuffer buffer) {
		return new XdrString(buffer);
	}


}
