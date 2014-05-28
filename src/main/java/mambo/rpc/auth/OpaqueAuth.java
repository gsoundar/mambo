package mambo.rpc.auth;

import java.nio.ByteBuffer;

import mambo.rpc.xdr.Xdr;
import mambo.rpc.xdr.XdrSerializable;


public abstract class OpaqueAuth implements XdrSerializable {

	final AuthFlavor flavor;
	
	public OpaqueAuth() {
		flavor = null;
	}
	
	public OpaqueAuth(AuthFlavor flavor) {
		this.flavor = flavor;
	}
	
	public AuthFlavor getAuthFlavor() {
		return flavor;
	}

	public OpaqueAuth buildFromXdr(ByteBuffer buffer) {
		AuthFlavor flavor = new AuthFlavor(Xdr.decodeInteger(buffer));
		if(flavor.equals(AuthFlavor.AUTH_NONE)) {
			return new AuthNone().buildFromXdr(buffer);
		} 
		else if(flavor.equals(AuthFlavor.AUTH_SYS)) {
			return new AuthUnix().buildFromXdr(buffer);
		} 
		else {
			throw new RuntimeException("Don't know how to handle other types of auth!");
		}
	}

}
