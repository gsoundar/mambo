package mambo.rpc.auth;

public final class AuthFlavor {

	
	public static final AuthFlavor AUTH_NONE = new AuthFlavor(0);
	public static final AuthFlavor AUTH_SYS = new AuthFlavor(1);
	public static final AuthFlavor AUTH_SHORT = new AuthFlavor(2);
	public static final AuthFlavor AUTH_DH = new AuthFlavor(3);
	public static final AuthFlavor RPCSEC_GSS = new AuthFlavor(6);
	
	private final int value;
	
	public AuthFlavor(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
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
		AuthFlavor other = (AuthFlavor) obj;
		if (value != other.value)
			return false;
		return true;
	}

	@Override
	public String toString() {
		if(value == 0) {
			return "AUTH_NONE";
		} else if(value == 1) {
			return "AUTH_SYS";
		} else if(value == 2) {
			return "AUTH_SHORT";
		} else if(value == 3) {
			return "AUTH_DH";
		} else if(value == 6) {
			return "RPCSEC_GSS";
		} else {
			throw new RuntimeException("No such authentication type");
		}
	}
	
}
