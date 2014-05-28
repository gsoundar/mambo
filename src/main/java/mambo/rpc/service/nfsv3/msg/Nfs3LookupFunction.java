package mambo.rpc.service.nfsv3.msg;

import mambo.rpc.auth.OpaqueAuth;
import mambo.rpc.function.BaseRpcFunction;
import mambo.rpc.service.nfsv3.Nfs3CallMessage;
import mambo.rpc.service.nfsv3.types.Nfs3DirectoryOperationArgs;
import mambo.rpc.service.nfsv3.types.Nfs3DirectoryOperationsArgsFactory;

public class Nfs3LookupFunction extends BaseRpcFunction {
	
	public Nfs3LookupFunction(OpaqueAuth credentials, Nfs3DirectoryOperationArgs what) {
		super(new Nfs3DirectoryOperationsArgsFactory(), new Nfs3LookupResponseFactory());
		this.setCall(new Nfs3CallMessage(new Nfs3LookupRequest(credentials, what)));
	}
	
}
