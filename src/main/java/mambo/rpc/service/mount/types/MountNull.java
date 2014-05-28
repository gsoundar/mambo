package mambo.rpc.service.mount.types;

import mambo.rpc.function.BaseRpcFunction;
import mambo.rpc.msg.CallMessage;
import mambo.rpc.service.mount.MountV3;

public class MountNull extends BaseRpcFunction {

	public static final int PROCEDURE_ID = 0;
	
	public MountNull() {
		super(new XdrVoidFactory(), new XdrVoidFactory());
		callMessage = new CallMessage(MountV3.getSequencer().getNextXid(),
				new MountNullBody());
	}
	
}
