package mambo.rpc.function;

import mambo.rpc.msg.CallMessage;
import mambo.rpc.msg.ReplyMessage;

public abstract class BaseRpcFunction implements RpcFunction {

	protected CallParameterFactory callParameterFactory;
	protected ReturnValueFactory returnValueFactory;
	
	protected CallMessage callMessage;
	protected ReplyMessage replyMessage;
	
	public BaseRpcFunction(CallParameterFactory callParameterFactory, ReturnValueFactory returnValueFactory) {
		this.callParameterFactory = callParameterFactory;
		this.returnValueFactory = returnValueFactory;
	}
	
	public Integer getXid() {
		return callMessage.getXid();
	}

	public void setCall(CallMessage call) {
		this.callMessage = call;
	}

	public CallMessage getCall() {
		return callMessage;
	}

	public void setReply(ReplyMessage reply) {
		this.replyMessage = reply;
	}

	public ReplyMessage getReply() {
		return replyMessage;
	}

	public ReturnValueFactory getReturnValueFactory() {
		return returnValueFactory;
	}

	public CallParameterFactory getCallParameterFactory() {
		return callParameterFactory;
	}

}
