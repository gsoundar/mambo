package mambo.rpc.service.mount.types;

import mambo.rpc.auth.OpaqueAuth;
import mambo.rpc.function.BaseRpcFunction;
import mambo.rpc.msg.CallMessage;
import mambo.rpc.service.mount.MountV3;

public class MountMount extends BaseRpcFunction {

	public static final int PROCEDURE_ID = 1;
	
	public MountMount(OpaqueAuth credentials, String path) {
		super(new XdrStringFactory(), new MountResultFactory());
		this.setCall(new CallMessage(MountV3.getSequencer().getNextXid(), new MountMountBody(credentials, path)));
	}
	
}
