package mambo.rpc.msg;

import java.nio.ByteBuffer;

import mambo.rpc.AuthStatus;
import mambo.rpc.RejectStatus;

public class AuthErrorReplyBody extends RejectedReplyBody {

	final AuthStatus authStatus;
	
	public AuthErrorReplyBody(AuthStatus authStatus) {
		super(RejectStatus.AUTH_ERROR);
		this.authStatus = authStatus;
	}

	public AuthStatus getAuthStatus() {
		return authStatus;
	}
	
	public ByteBuffer serializeToXdr(ByteBuffer buffer) {
		super.serializeToXdr(buffer);
		authStatus.serializeToXdr(buffer);
		return buffer;
	}

}
