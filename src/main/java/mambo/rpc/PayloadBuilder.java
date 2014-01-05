package mambo.rpc;

import java.nio.ByteBuffer;

public interface PayloadBuilder<T> {

	T buildPayload(ByteBuffer buffer);
	
}
