package mambo.rpc.service.nfsv3.types;

import java.nio.ByteBuffer;

import mambo.rpc.function.CallParameter;
import mambo.rpc.function.CallParameterFactory;

public class Nfs3DirectoryOperationsArgsFactory implements CallParameterFactory {

	@Override
	public CallParameter buildCallParameterFromXdr(ByteBuffer buffer) {
		return new Nfs3DirectoryOperationArgs(buffer); 
	}

}
