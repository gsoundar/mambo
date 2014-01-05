package mambo.rpc;

public final class AcceptStatus {
	
	public static final AcceptStatus SUCCESS = new AcceptStatus(0);
	public static final AcceptStatus PROG_UNAVAIL = new AcceptStatus(1);
	public static final AcceptStatus PROG_MISMATCH = new AcceptStatus(2);
	public static final AcceptStatus PROC_UNAVAIL = new AcceptStatus(3);
	public static final AcceptStatus GARBAGE_ARGS = new AcceptStatus(4);
	public static final AcceptStatus SYSTEM_ERR = new AcceptStatus(5);
	
	private final int value;
	
	public AcceptStatus(int value) {
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
		AcceptStatus other = (AcceptStatus) obj;
		if (value != other.value)
			return false;
		return true;
	}

	@Override
	public String toString() {
		if(value == SUCCESS.value) {
			return "SUCCESS";
		} else if(value == PROG_UNAVAIL.value){
			return "PROG_UNAVAIL";
		} else if(value == PROG_MISMATCH.value){
			return "PROG_MISMATCH";
		} else if(value == PROC_UNAVAIL.value){
			return "PROC_UNAVAIL";
		} else if(value == GARBAGE_ARGS.value){
			return "GARBAGE_ARGS";
		} else {
			return "SYSTEM_ERR";
		}
	}
	
}
