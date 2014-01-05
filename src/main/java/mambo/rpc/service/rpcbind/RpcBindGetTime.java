package mambo.rpc.service.rpcbind;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import mambo.rpc.function.BaseRpcFunction;
import mambo.rpc.msg.CallMessage;
import mambo.rpc.service.rpcbind.types.XdrIntFactory;
import mambo.rpc.service.rpcbind.types.XdrVoidFactory;

public class RpcBindGetTime extends BaseRpcFunction {

	public static final int PROCEDURE_ID = 6;
	
	public static final Logger LOG = LoggerFactory.getLogger(RpcBindGetTime.class);
	
	public RpcBindGetTime() {
		super(new XdrVoidFactory(), new XdrIntFactory());
		callMessage = new CallMessage(RpcBindV4.getXidSequencer().getNextXid(), new RpcBindGetTimeBody());
		
	}
	
}
