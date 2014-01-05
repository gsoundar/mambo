package mambo.rpc.service.rpcbind.types;

import java.nio.ByteBuffer;

import mambo.rpc.function.CallParameter;
import mambo.rpc.function.ReturnValue;
import mambo.rpc.xdr.Xdr;

public class RpcBindNetBuf implements CallParameter, ReturnValue {

	final int maxlen;
	final byte[] buffer;
	
	public RpcBindNetBuf(int maxlen, byte[] buffer) {
		this.maxlen = maxlen;
		this.buffer = buffer;
	}

	public ByteBuffer serializeToXdr(ByteBuffer buffer) {
		Xdr.encodeInt(buffer, maxlen);
		Xdr.encodeOpaque(buffer, this.buffer, 0, this.buffer.length);
		return buffer;
	}
	
}
