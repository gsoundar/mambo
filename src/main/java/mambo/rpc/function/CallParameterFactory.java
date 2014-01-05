package mambo.rpc.function;

import java.nio.ByteBuffer;

public interface CallParameterFactory {

	CallParameter buildCallParameterFromXdr(ByteBuffer buffer);
	
}
