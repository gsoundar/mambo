package mambo.rpc.service.mount;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import mambo.rpc.AcceptStatus;
import mambo.rpc.RejectStatus;
import mambo.rpc.ReplyStatus;
import mambo.rpc.XidSequencer;
import mambo.rpc.auth.OpaqueAuth;
import mambo.rpc.function.RpcCallHandle;
import mambo.rpc.msg.AcceptedReplyBody;
import mambo.rpc.msg.AuthErrorReplyBody;
import mambo.rpc.msg.RejectedReplyBody;
import mambo.rpc.msg.ReplyMessage;
import mambo.rpc.msg.SuccessfulReplyBody;
import mambo.rpc.service.MultithreadedRpcService;
import mambo.rpc.service.mount.types.MountMount;
import mambo.rpc.service.mount.types.MountNull;
import mambo.rpc.service.mount.types.MountResult;
import mambo.rpc.service.mount.types.XdrVoid;

public class MountV3 extends MultithreadedRpcService {

	public static final int RPC_VERSION = 2;
	public static final int PROGRAM_ID = 100005;
	public static final int PROGRAM_VERSION = 3;
	
	 public static final int MNTPATHLEN = 1024;  /* Maximum bytes in a path name */
	 public static final int MNTNAMLEN  = 255;   /* Maximum bytes in a name */
	 public static final int FHSIZE3    = 64;    /* Maximum bytes in a V3 file handle */
	
	 final static XidSequencer sequencer = new XidSequencer();
	 
	 final static Logger LOG = LoggerFactory.getLogger(MountV3.class);
	 
	 public MountV3(String hostname, int port) throws IOException {
		 super(hostname, port);
	 }
	 
	 public void doNull() throws MountException {
		 try {
			 
			 /* Execute the function */
			 MountNull nullFunction = new MountNull();
			 RpcCallHandle handle = this.submit(nullFunction);
			 
			 /* Wait for it to finish */
			 handle.waitForCompletion();
			 
			 /* Check status and respond */
			 ReplyMessage reply = handle.getFunction().getReply();
			 if(reply.getMessageBody().getReplyStatus().equals(ReplyStatus.MSG_ACCEPTED)) {
				 AcceptedReplyBody accepted = (AcceptedReplyBody) reply.getMessageBody();
				 if(accepted.getAcceptStatus().equals(AcceptStatus.SUCCESS)) {
					 SuccessfulReplyBody success = (SuccessfulReplyBody) accepted;
					 XdrVoid value = (XdrVoid) success.getReturnValue();
					 LOG.debug("MOUNT NULL with xid: " + reply.getXid() + " executed successfully");
					 LOG.debug("MOUNT NULL with xid: " + reply.getXid() + " got value: " + value);
				 } 
				 else {
					 LOG.error("MOUNT NULL got accept status as: " + accepted.getAcceptStatus());
					 throw new MountException("MOUNT NULL got accept status as: " + accepted.getAcceptStatus());
				 }
			 } 
			 else {
				 LOG.error("MOUNT NULL got a MSG_DENIED error");
				 throw new MountException("MOUNT NULL got a MSG_DENIED error");
			 }
			 
		 } catch(InterruptedException e) {
			 LOG.error("Got an error while executing the MOUNT NULL function");
			 throw new MountException(e.getCause());
		 }
		 
	 }
	 
	 public MountResult mount(OpaqueAuth credentials, String path) throws MountException {
		 try {
			 
			 /* Execute the function */
			 MountMount function = new MountMount(credentials, path);
			 RpcCallHandle handle = this.submit(function);
			 
			 /* Wait for it to finish */
			 handle.waitForCompletion();
			 
			 /* Check status and respond */
			 ReplyMessage reply = handle.getFunction().getReply();
			 if(reply.getMessageBody().getReplyStatus().equals(ReplyStatus.MSG_ACCEPTED)) {
				 AcceptedReplyBody accepted = (AcceptedReplyBody) reply.getMessageBody();
				 if(accepted.getAcceptStatus().equals(AcceptStatus.SUCCESS)) {
					 SuccessfulReplyBody success = (SuccessfulReplyBody) accepted;
					 MountResult value = (MountResult) success.getReturnValue();
					 LOG.debug("MOUNT MNT with xid: " + reply.getXid() + " executed successfully");
					 LOG.debug("MOUNT MNT with xid: " + reply.getXid() + " got value: " + value);
					 return value;
				 } 
				 else {
					 LOG.error("MOUNT MNT got accept status as: " + accepted.getAcceptStatus());
					 throw new MountException("MOUNT MNT got accept status as: " + accepted.getAcceptStatus());
				 }
			 } 
			 else {
				 RejectedReplyBody rejected = (RejectedReplyBody) reply.getMessageBody();
				 if(rejected.getRejectStatus().equals(RejectStatus.AUTH_ERROR)) {
					 AuthErrorReplyBody auth = (AuthErrorReplyBody) reply.getMessageBody();
					 LOG.error("MOUNT MNT got a MSG_DENIED error with auth status: " + auth.getAuthStatus());
					 throw new MountException("MOUNT MNT got a MSG_DENIED error with auth status: " + auth.getAuthStatus());
				 }
				 LOG.error("MOUNT MNT got a MSG_DENIED error with reject status: " + rejected.getRejectStatus());
				 throw new MountException("MOUNT MNT got a MSG_DENIED error with reject status: " + rejected.getRejectStatus());
			 }
			 
		 } catch(InterruptedException e) {
			 LOG.error("Got an error while executing the MOUNT MNT function");
			 throw new MountException(e.getCause());
		 }
		 
	 }
	 
	 public static XidSequencer getSequencer() {
		 return sequencer;
	 }
	 
}
