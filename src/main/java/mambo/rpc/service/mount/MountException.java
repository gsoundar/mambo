package mambo.rpc.service.mount;

public class MountException extends Exception {

	private static final long serialVersionUID = 150044897479953218L;

	public MountException(String message) {
		super(message);
	}
	
	public MountException(Throwable cause) {
		super(cause);
	}
	
}
