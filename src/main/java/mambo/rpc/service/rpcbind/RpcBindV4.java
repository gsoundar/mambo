package mambo.rpc.service.rpcbind;

import java.io.IOException;
import java.net.InetSocketAddress;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import mambo.rpc.AcceptStatus;
import mambo.rpc.ReplyStatus;
import mambo.rpc.XidSequencer;
import mambo.rpc.function.RpcCallHandle;
import mambo.rpc.msg.AcceptedReplyBody;
import mambo.rpc.msg.ReplyMessage;
import mambo.rpc.msg.SuccessfulReplyBody;
import mambo.rpc.service.MultithreadedRpcService;
import mambo.rpc.service.rpcbind.types.RpcBindInfo;
import mambo.rpc.service.rpcbind.types.XdrInt;
import mambo.rpc.service.rpcbind.types.XdrString;

public class RpcBindV4 extends MultithreadedRpcService {

	public static final int RPC_VERSION = 2;
	public static final int PROGRAM_ID = 100000;
	public static final int PROGRAM_VERSION = 4;
	
	final static Logger LOG = LoggerFactory.getLogger(RpcBindV4.class);
	
	final static XidSequencer sequencer = new XidSequencer();
	
	public RpcBindV4(String hostname) throws IOException {
		super(hostname, RpcBind.RPCBIND_PORT);
	}
	
	public String getAddress(int program, int version) {
		
		try {
			/* Make the request and submit */
			RpcBindInfo info = new RpcBindInfo(program, version, "", "", "");
			RpcCallHandle handle = this.submit(new RpcBindGetAddress(info));
			handle.waitForCompletion();
			
			/* Process reply */
			ReplyMessage reply = handle.getFunction().getReply();
			LOG.info("Got a reply message with status of " + reply.getMessageBody().getReplyStatus());
			
			if(reply.getMessageBody().getReplyStatus().equals(ReplyStatus.MSG_ACCEPTED)) {
				AcceptedReplyBody accepted = (AcceptedReplyBody) reply.getMessageBody();
				LOG.info("Reply accept status is " + accepted.getAcceptStatus());
				if(accepted.getAcceptStatus().equals(AcceptStatus.SUCCESS)) {
					SuccessfulReplyBody success = (SuccessfulReplyBody) accepted;
					XdrString ret = (XdrString) success.getReturnValue();
					LOG.info("Got a reply of |" + ret.getValue() + "|");
					return ret.getValue();
				}
			}
			
			return null;
			
		} catch(Exception e) {
			LOG.error("Could not get address of program=" + program + " version=" + version);
			throw new RuntimeException(e.getCause());
		}
		
	}
	
	public int getTime() {
		try {
			
			/* Make the request and submit */
			RpcCallHandle handle = this.submit(new RpcBindGetTime());
			handle.waitForCompletion();
			
			/* Process reply */
			ReplyMessage reply = handle.getFunction().getReply();
			LOG.info("Got a reply message with status of " + reply.getMessageBody().getReplyStatus());
			
			if(reply.getMessageBody().getReplyStatus().equals(ReplyStatus.MSG_ACCEPTED)) {
				AcceptedReplyBody accepted = (AcceptedReplyBody) reply.getMessageBody();
				LOG.info("Reply accept status is " + accepted.getAcceptStatus());
				if(accepted.getAcceptStatus().equals(AcceptStatus.SUCCESS)) {
					SuccessfulReplyBody success = (SuccessfulReplyBody) accepted;
					XdrInt ret = (XdrInt) success.getReturnValue();
					LOG.info("Got a reply of |" + ret.getValue() + "|");
					return ret.getValue();
				}
			}
			
			return 0;
			
		} catch(Exception e) {
			LOG.error("Could not get time from RPC Bind");
			e.printStackTrace();
			throw new RuntimeException(e.getCause());
		}
	}
	
	public InetSocketAddress convertToSocketAddress(String address) {
		
		//From: http://fxr.watson.org/fxr/source/rpc/rpc_generic.c#L288
		String hostname;
		int port;
		
		/* Parse the universal address */
		int idx = 0, idx1 = -1, idx2 = 2;
		while((idx = address.indexOf(".", idx+1)) != -1) {
			LOG.info("Idx=" + idx);
			idx1 = idx2;
			idx2 = idx;
		}
		LOG.info("Idx1=" + idx2 + " Idx2=" + idx2);
		
		hostname = address.substring(0,idx1);
		port = (Integer.parseInt(address.substring(idx1+1,idx2)) << 8) | 
				(Integer.parseInt(address.substring(idx2+1)) & 0xFF);
		
		LOG.debug("Got hostname=" + hostname + " port=" + port);
		
		return new InetSocketAddress(hostname, port);
		
	}
	
	public static XidSequencer getXidSequencer() {
		return sequencer;
	}
	
}
