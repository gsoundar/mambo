package mambo.rpc.function;

import mambo.rpc.msg.CallMessage;
import mambo.rpc.msg.ReplyMessage;

public interface RpcFunction{

	Integer getXid();
	
	void setCall(CallMessage call);
	CallMessage getCall();
	void setReply(ReplyMessage reply);
	ReplyMessage getReply();
	
	ReturnValueFactory getReturnValueFactory();
	CallParameterFactory getCallParameterFactory();
	
}
