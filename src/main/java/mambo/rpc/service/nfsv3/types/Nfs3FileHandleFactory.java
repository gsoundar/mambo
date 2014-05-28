package mambo.rpc.service.nfsv3.types;

import java.nio.ByteBuffer;

import mambo.rpc.function.CallParameter;
import mambo.rpc.function.CallParameterFactory;
import mambo.rpc.function.ReturnValue;
import mambo.rpc.function.ReturnValueFactory;

public class Nfs3FileHandleFactory implements CallParameterFactory, ReturnValueFactory {

	@Override
	public ReturnValue buildReturnValueFromXdr(ByteBuffer buffer) {
		return new Nfs3FileHandle(buffer);
	}

	@Override
	public CallParameter buildCallParameterFromXdr(ByteBuffer buffer) {
		return new Nfs3FileHandle(buffer); 
	}

}
