package mambo.rpc.msg;

import java.nio.ByteBuffer;

import mambo.rpc.function.CallParameterFactory;

public interface CallBodyFactory {

	CallMessageBody buildFromXdr(ByteBuffer buffer, CallParameterFactory factory);
	
}
