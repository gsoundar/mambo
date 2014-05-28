package mambo.rpc.service.nfsv3.types;

public class Nfs3Status {

	public static final Nfs3Status NFS_OK = new Nfs3Status(0);
	
	private final int value;
	
	public Nfs3Status(int value) {
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
		Nfs3Status other = (Nfs3Status) obj;
		if (value != other.value)
			return false;
		return true;
	}
	
}
