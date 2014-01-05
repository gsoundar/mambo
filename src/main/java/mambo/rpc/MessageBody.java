package mambo.rpc;

import java.nio.ByteBuffer;

import mambo.rpc.xdr.XdrSerializable;


public abstract class MessageBody implements XdrSerializable {

	public ByteBuffer serializeToXdr(ByteBuffer buffer) {
		throw new RuntimeException("Method not implemented");
	}
	
}
