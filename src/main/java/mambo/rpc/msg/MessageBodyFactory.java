package mambo.rpc.msg;

import java.nio.ByteBuffer;

import mambo.rpc.AcceptStatus;
import mambo.rpc.AuthStatus;
import mambo.rpc.RejectStatus;
import mambo.rpc.ReplyStatus;
import mambo.rpc.auth.AuthFactory;
import mambo.rpc.auth.OpaqueAuth;
import mambo.rpc.function.CallParameterFactory;
import mambo.rpc.function.ReturnValueFactory;
import mambo.rpc.xdr.Xdr;

public class MessageBodyFactory implements CallBodyFactory, ReplyBodyFactory {

	static final MessageBodyFactory singleton;
	
	static {
		singleton = new MessageBodyFactory();
	}
	
	public static MessageBodyFactory getInstance() {
		return singleton;
	}
	
	public CallMessageBody buildFromXdr(ByteBuffer buffer, CallParameterFactory factory) {
		// TODO Auto-generated method stub
		return null;
	}

	public ReplyMessageBody buildFromXdr(ByteBuffer buffer, ReturnValueFactory factory) {
		
		ReplyStatus replyStatus = new ReplyStatus(Xdr.decodeInteger(buffer));
		
		/* DENIED message */
		if(replyStatus.equals(ReplyStatus.MSG_DENIED)) {
			RejectStatus rejectStatus = new RejectStatus(Xdr.decodeInteger(buffer));
			if(rejectStatus.equals(RejectStatus.RPC_MISMATCH)) {
				return new RpcMismatchReplyBody(new MismatchInfo(buffer));
			}
			else if(rejectStatus.equals(RejectStatus.AUTH_ERROR)) {
				return new AuthErrorReplyBody(new AuthStatus(Xdr.decodeInteger(buffer)));
			}
			else {
				throw new RuntimeException("Reject status was invalid!");
			}
		}
		/* ACCEPTED message */
		else if(replyStatus.equals(ReplyStatus.MSG_ACCEPTED)) {
			OpaqueAuth verf = AuthFactory.buildFromXdr(buffer);
			AcceptStatus acceptStatus = new AcceptStatus(Xdr.decodeInteger(buffer));
			if(acceptStatus.equals(AcceptStatus.PROG_MISMATCH)) {
				return new ProgramMismatchReplyBody(buffer, verf);
			}
			else if(acceptStatus.equals(AcceptStatus.SUCCESS)) {
				return new SuccessfulReplyBody(buffer, verf, factory);
			}
			else {
				return new OtherAcceptedReplyBody(buffer, acceptStatus, verf);
			}
		}
		/* Unknown */
		else {
			throw new RuntimeException("Reply status was invalid!");
		}
		
	}

	
}
