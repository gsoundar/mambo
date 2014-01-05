package mambo.rpc.auth;

import java.nio.ByteBuffer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import mambo.rpc.xdr.Xdr;

public class AuthFactory {

	public static final Logger LOG = LoggerFactory.getLogger(AuthFactory.class);
	
	public static OpaqueAuth buildFromXdr(ByteBuffer buffer) {
		
		Integer auth = Xdr.decodeInteger(buffer);
		AuthFlavor flavor = new AuthFlavor(auth);
		
		/* AUTH_NONE */
		if(flavor.equals(AuthFlavor.AUTH_NONE)) {
			return new AuthNone(buffer);
		}
		/* AUTH_SYS */
		else if(flavor.equals(AuthFlavor.AUTH_SYS)) {
			return new AuthUnix(buffer);
		}
		/* UNKNOWN */
		else {
			LOG.error("Got a authentication flavor of " + auth + " which I don't parse!");
			throw new RuntimeException("No such authentication flavor!");
		}
	}
	
}
