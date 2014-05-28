package mambo.rpc.service.nfsv3;

import java.nio.ByteBuffer;

import mambo.rpc.auth.AuthNone;
import mambo.rpc.auth.OpaqueAuth;
import mambo.rpc.msg.CallMessageBody;
import mambo.rpc.service.nfsv3.types.Nfs3OperationType;

public class Nfs3CallMessageBody extends CallMessageBody {
	
	public Nfs3CallMessageBody(Nfs3OperationType operationType, OpaqueAuth credentials) {
		super(Nfs3.RPC_VERSION, Nfs3.PROGRAM_ID, Nfs3.PROGRAM_VERSION, operationType.getValue(), credentials, new AuthNone());
	}
	
	public Nfs3CallMessageBody(ByteBuffer buffer) {
		super(buffer);
	}
	
}
