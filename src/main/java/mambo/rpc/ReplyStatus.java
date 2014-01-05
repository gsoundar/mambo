package mambo.rpc;

public final class ReplyStatus {

	public static final ReplyStatus MSG_ACCEPTED = new ReplyStatus(0);
	public static final ReplyStatus MSG_DENIED = new ReplyStatus(1);
	
	private final int value;
	
	public ReplyStatus(int value) {
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
		ReplyStatus other = (ReplyStatus) obj;
		if (value != other.value)
			return false;
		return true;
	}

	@Override
	public String toString() {
		if(value == MSG_ACCEPTED.value) {
			return "MSG_ACCEPTED";
		} else if(value == MSG_DENIED.value) {
			return "MSG_DENIED";
		} else {
			throw new RuntimeException("No such accept status");
		}
	}
	
}
