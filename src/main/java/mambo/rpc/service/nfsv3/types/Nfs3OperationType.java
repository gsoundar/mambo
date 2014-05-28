package mambo.rpc.service.nfsv3.types;

public class Nfs3OperationType {

	public static final Nfs3OperationType NFS3_NULL = new Nfs3OperationType(0);
	public static final Nfs3OperationType NFS3_GETATTR = new Nfs3OperationType(1);
	public static final Nfs3OperationType NFS3_SETATTR = new Nfs3OperationType(2);
	public static final Nfs3OperationType NFS3_LOOKUP = new Nfs3OperationType(3);
	
	private final int value;
	
	private Nfs3OperationType(int value) {
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
		Nfs3OperationType other = (Nfs3OperationType) obj;
		if (value != other.value)
			return false;
		return true;
	}
	
}
