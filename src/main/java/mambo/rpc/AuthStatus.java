package mambo.rpc;

import java.nio.ByteBuffer;

import mambo.rpc.xdr.Xdr;
import mambo.rpc.xdr.XdrSerializable;

public final class AuthStatus implements XdrSerializable {

	/* Ok */
	public static final AuthStatus AUTH_OK = new AuthStatus(0);
	
	/* Failed at remote end */
	public static final AuthStatus AUTH_BADCRED = new AuthStatus(1);
	public static final AuthStatus AUTH_REJECTEDCRED = new AuthStatus(2);
	public static final AuthStatus AUTH_BADVERF = new AuthStatus(3);
	public static final AuthStatus AUTH_REJECTEDVERF = new AuthStatus(4);
	public static final AuthStatus AUTH_TOOWEAK = new AuthStatus(5);
	
	/* Failed locally */
	public static final AuthStatus AUTH_INVALIDREF = new AuthStatus(6);
	public static final AuthStatus AUTH_FAILED = new AuthStatus(7);
	
	/* KERB errors (deprecated) */
	public static final AuthStatus AUTH_KERB_GENERIC = new AuthStatus(8);
	public static final AuthStatus AUTH_TIMEEXPIRE = new AuthStatus(9);
	public static final AuthStatus AUTH_TKT_FILE = new AuthStatus(10);
	public static final AuthStatus AUTH_DECODE = new AuthStatus(11);
	public static final AuthStatus AUTH_NET_ADDR = new AuthStatus(12);
	
	/* GSS related errors */
	public static final AuthStatus RPCSEC_GSS_CREDPROBLEM = new AuthStatus(13);
	public static final AuthStatus RPCSEC_GSS_CTXPROBLEM = new AuthStatus(14);
	
	private final int value;
	
	public AuthStatus(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return value;
	}
	
	public ByteBuffer serializeToXdr(ByteBuffer buffer) {
		Xdr.encodeInt(buffer, value);
		return buffer;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + value;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AuthStatus other = (AuthStatus) obj;
		if (value != other.value)
			return false;
		return true;
	}

	public String toString() {
		/* TODO: Fill this in later */
		if(value == AUTH_OK.value) {
			return "AUTH_OK";
		} else {
			return "AUTH_FAILURE";
		}
	}

	
}
