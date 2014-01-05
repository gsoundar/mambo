package mambo.rpc.service.rpcbind;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import mambo.rpc.function.BaseRpcFunction;
import mambo.rpc.msg.CallMessage;
import mambo.rpc.service.rpcbind.types.RpcBindInfo;
import mambo.rpc.service.rpcbind.types.RpcBindInfoFactory;
import mambo.rpc.service.rpcbind.types.XdrStringFactory;

public class RpcBindGetAddress extends BaseRpcFunction {

	public static final int PROCEDURE_ID = 3;
	
	public static final Logger LOG = LoggerFactory.getLogger(RpcBindGetAddress.class);
	
	public RpcBindGetAddress(RpcBindInfo info) {
		super(new RpcBindInfoFactory(), new XdrStringFactory());
		callMessage = new CallMessage(RpcBindV4.getXidSequencer().getNextXid(), new RpcBindGetAddressBody(info));
		
	}
	
}
