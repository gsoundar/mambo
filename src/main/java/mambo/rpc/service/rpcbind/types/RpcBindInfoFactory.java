package mambo.rpc.service.rpcbind.types;

import java.nio.ByteBuffer;

import mambo.rpc.function.CallParameter;
import mambo.rpc.function.CallParameterFactory;
import mambo.rpc.function.ReturnValue;
import mambo.rpc.function.ReturnValueFactory;

public class RpcBindInfoFactory implements CallParameterFactory, ReturnValueFactory {

	public CallParameter buildCallParameterFromXdr(ByteBuffer buffer) {
		return new RpcBindInfo(buffer);
	}

	public ReturnValue buildReturnValueFromXdr(ByteBuffer buffer) {
		return new RpcBindInfo(buffer);
	}


}
