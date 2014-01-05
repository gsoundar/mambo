package mambo.rpc.function;

import java.nio.ByteBuffer;

public interface ReturnValueFactory {

	ReturnValue buildReturnValueFromXdr(ByteBuffer buffer);
	
}
