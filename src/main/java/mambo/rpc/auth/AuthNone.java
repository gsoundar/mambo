package mambo.rpc.auth;

import java.nio.ByteBuffer;

import mambo.rpc.xdr.Xdr;


public class AuthNone extends OpaqueAuth {

	public AuthNone() {
		super(AuthFlavor.AUTH_NONE);
	}

	public AuthNone(ByteBuffer buffer) {
		int authBodySize = Xdr.decodeInteger(buffer);
		assert(authBodySize == 0);
	}
	
	public ByteBuffer serializeToXdr(ByteBuffer buffer) {
		Xdr.encodeInt(buffer, getAuthFlavor().getValue());
		Xdr.encodeDynamicOpaque(buffer, new byte[0]);
		return buffer;
	}

}
