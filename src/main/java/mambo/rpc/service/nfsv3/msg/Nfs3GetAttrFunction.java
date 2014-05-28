package mambo.rpc.service.nfsv3.msg;

import mambo.rpc.auth.OpaqueAuth;
import mambo.rpc.function.BaseRpcFunction;
import mambo.rpc.service.nfsv3.Nfs3CallMessage;
import mambo.rpc.service.nfsv3.types.Nfs3FileHandle;
import mambo.rpc.service.nfsv3.types.Nfs3FileHandleFactory;

public class Nfs3GetAttrFunction extends BaseRpcFunction {
	
	public Nfs3GetAttrFunction(OpaqueAuth credentials, Nfs3FileHandle handle) {
		super(new Nfs3FileHandleFactory(), new Nfs3GetAttrResponseFactory());
		this.setCall(new Nfs3CallMessage(new Nfs3GetAttrRequest(credentials, handle)));
	}
	
}
