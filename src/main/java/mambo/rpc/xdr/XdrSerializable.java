package mambo.rpc.xdr;

import java.nio.ByteBuffer;

public interface XdrSerializable {

	public ByteBuffer serializeToXdr(ByteBuffer buffer);
	
}
