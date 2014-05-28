package mambo.rpc.service.nfsv3.types;

public class Nfs3FileType {
	
	/*
	  enum ftype3 {
         NF3REG    = 1,
         NF3DIR    = 2,
         NF3BLK    = 3,
         NF3CHR    = 4,
         NF3LNK    = 5,
         NF3SOCK   = 6,
         NF3FIFO   = 7
      };
	 */
	
	public static final Nfs3FileType NF3NON = new Nfs3FileType(0);
	public static final Nfs3FileType NF3REG = new Nfs3FileType(1);
	public static final Nfs3FileType NF3DIR = new Nfs3FileType(2);
	public static final Nfs3FileType NF3BLK = new Nfs3FileType(3);
	public static final Nfs3FileType NF3CHR = new Nfs3FileType(4);
	public static final Nfs3FileType NF3LNK = new Nfs3FileType(5);
	public static final Nfs3FileType NF3SOCK = new Nfs3FileType(6);
	public static final Nfs3FileType NF3FIFO = new Nfs3FileType(7);
	
	private final int value;
	
	public Nfs3FileType(int value) {
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
		Nfs3FileType other = (Nfs3FileType) obj;
		if (value != other.value)
			return false;
		return true;
	}
	
	
	
}
