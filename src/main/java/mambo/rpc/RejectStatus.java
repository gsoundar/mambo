package mambo.rpc;

public final class RejectStatus {

	public static final RejectStatus RPC_MISMATCH = new RejectStatus(0);
	public static final RejectStatus AUTH_ERROR = new RejectStatus(1);
	
	private final int value;
	
	public RejectStatus(int value) {
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
		RejectStatus other = (RejectStatus) obj;
		if (value != other.value)
			return false;
		return true;
	}

	@Override
	public String toString() {
		if(value == RPC_MISMATCH.value) {
			return "RPC_MISMATCH";
		} else if(value == AUTH_ERROR.value) {
			return "AUTH_ERROR";
		} else {
			throw new RuntimeException("No such reply status!");
		}
	}
	
}
