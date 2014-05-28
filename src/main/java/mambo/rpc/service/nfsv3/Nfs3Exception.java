package mambo.rpc.service.nfsv3;

public class Nfs3Exception extends Exception {

	private static final long serialVersionUID = 150044897479953218L;

	public Nfs3Exception(String message) {
		super(message);
	}
	
	public Nfs3Exception(Throwable cause) {
		super(cause);
	}
	
}
