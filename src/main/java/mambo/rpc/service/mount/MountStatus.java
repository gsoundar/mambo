package mambo.rpc.service.mount;

public class MountStatus {
	   
	public static final MountStatus MNT3_OK = new MountStatus(0);
	public static final MountStatus MNT3ERR_PERM = new MountStatus(1);
	public static final MountStatus MNT3ERR_NOENT = new MountStatus(2);
	public static final MountStatus MNT3ERR_IO = new MountStatus(5);
	public static final MountStatus MNT3ERR_ACCES = new MountStatus(13);
	public static final MountStatus MNT3ERR_NOTDIR = new MountStatus(20);
	public static final MountStatus MNT3ERR_INVAL = new MountStatus(22);
	public static final MountStatus MNT3ERR_NAMETOOLONG = new MountStatus(63);
	public static final MountStatus MNT3ERR_NOTSUPP = new MountStatus(10004);
	public static final MountStatus MNT3ERR_SERVERFAULT = new MountStatus(10006);
	
	private final int value;
	
	public MountStatus(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return value;
	}

	public String toString() {
		switch(value) {
		case 0:
			return "MNT_OK";
		case 1:
			return "MN3TERR_PERM";
		case 2:
			return "MNT3ERR_NOENT";
		case 5:
			return "MNT3ERR_IO";
		case 13:
			return "MNT3ERR_ACCES";
		case 20:
			return "MNT3ERR_NOTDIR";
		case 22:
			return "MNT3ERR_INVAL";
		case 63:
			return "MNT3ERR_NAMETOOLONG";
		case 10004:
			return "MNT3ERR_NOTSUPP";
		case 10006:
			return "MNT3ERR_SERVERFAULT";
		default:
			return "MountStatus = INVALID";
		}
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
		MountStatus other = (MountStatus) obj;
		if (value != other.value)
			return false;
		return true;
	}
	
	
	
}
