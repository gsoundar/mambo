package mambo.rpc.service.nfsv3;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import mambo.rpc.AcceptStatus;
import mambo.rpc.RejectStatus;
import mambo.rpc.ReplyStatus;
import mambo.rpc.XidSequencer;
import mambo.rpc.auth.OpaqueAuth;
import mambo.rpc.function.BaseRpcFunction;
import mambo.rpc.function.ReturnValue;
import mambo.rpc.function.RpcCallHandle;
import mambo.rpc.msg.AcceptedReplyBody;
import mambo.rpc.msg.AuthErrorReplyBody;
import mambo.rpc.msg.RejectedReplyBody;
import mambo.rpc.msg.ReplyMessage;
import mambo.rpc.msg.SuccessfulReplyBody;
import mambo.rpc.service.MultithreadedRpcService;
import mambo.rpc.service.nfsv3.msg.Nfs3GetAttrFunction;
import mambo.rpc.service.nfsv3.msg.Nfs3GetAttrResponse;
import mambo.rpc.service.nfsv3.msg.Nfs3LookupFunction;
import mambo.rpc.service.nfsv3.msg.Nfs3LookupResponse;
import mambo.rpc.service.nfsv3.types.Nfs3DirectoryOperationArgs;
import mambo.rpc.service.nfsv3.types.Nfs3FileAttributes;
import mambo.rpc.service.nfsv3.types.Nfs3FileHandle;

public class Nfs3 extends MultithreadedRpcService {

	public static final int RPC_VERSION = 2;
	public static final int PROGRAM_ID = 100003;
	public static final int PROGRAM_VERSION = 3;

	public static final int MNTPATHLEN = 1024;  /* Maximum bytes in a path name */
	public static final int MNTNAMLEN  = 255;   /* Maximum bytes in a name */
	public static final int FHSIZE3    = 64;    /* Maximum bytes in a V3 file handle */

	final static XidSequencer sequencer = new XidSequencer();

	final static Logger LOG = LoggerFactory.getLogger(Nfs3.class);

	public Nfs3(String hostname, int port) {
		super(hostname, port);
	}
	
	public Nfs3FileAttributes getFileAttributes(OpaqueAuth credentials, Nfs3FileHandle handle) throws Nfs3Exception {
		Nfs3GetAttrFunction function = new Nfs3GetAttrFunction(credentials, handle);
		Nfs3GetAttrResponse response = (Nfs3GetAttrResponse) execute(function);
		Nfs3FileAttributes attributes = response.getAttributes();
		return attributes;
	}
	
	public Nfs3LookupResponse lookup(OpaqueAuth credentials, Nfs3DirectoryOperationArgs what) throws Nfs3Exception {
		Nfs3LookupFunction function = new Nfs3LookupFunction(credentials, what);
		Nfs3LookupResponse response = (Nfs3LookupResponse) execute(function);
		return response;
	}
	
	 public <T extends BaseRpcFunction> ReturnValue execute(T function) throws Nfs3Exception {
		 try {
			 
			 /* Execute the function */
			 RpcCallHandle handle = this.submit(function);
			 
			 /* Wait for it to finish */
			 handle.waitForCompletion();
			 
			 /* Check status and respond */
			 ReplyMessage reply = handle.getFunction().getReply();
			 if(reply.getMessageBody().getReplyStatus().equals(ReplyStatus.MSG_ACCEPTED)) {
				 AcceptedReplyBody accepted = (AcceptedReplyBody) reply.getMessageBody();
				 if(accepted.getAcceptStatus().equals(AcceptStatus.SUCCESS)) {
					 SuccessfulReplyBody success = (SuccessfulReplyBody) accepted;
					 LOG.debug("Operation with xid: " + reply.getXid() + " executed successfully");
					 int nfsStatus = 1;
					 return success.getReturnValue();
				 } 
				 else {
					 LOG.error("Operation got accept status as: " + accepted.getAcceptStatus());
					 throw new Nfs3Exception("Operation got accept status as: " + accepted.getAcceptStatus());
				 }
			 } 
			 else {
				 RejectedReplyBody rejected = (RejectedReplyBody) reply.getMessageBody();
				 if(rejected.getRejectStatus().equals(RejectStatus.AUTH_ERROR)) {
					 AuthErrorReplyBody auth = (AuthErrorReplyBody) reply.getMessageBody();
					 LOG.error("Operation got a MSG_DENIED error with auth status: " + auth.getAuthStatus());
					 throw new Nfs3Exception("Operation got a MSG_DENIED error with auth status: " + auth.getAuthStatus());
				 }
				 LOG.error("Operation got a MSG_DENIED error with reject status: " + rejected.getRejectStatus());
				 throw new Nfs3Exception("Operation got a MSG_DENIED error with reject status: " + rejected.getRejectStatus());
			 }
			 
		 } catch(InterruptedException e) {
			 LOG.error("Got an error while executing");
			 throw new Nfs3Exception(e.getCause());
		 }
		 
	 }
	
	public static XidSequencer getSequencer() {
		return sequencer;
	}

}
