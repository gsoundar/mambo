package mambo.rpc.service.rpcbind;

import java.nio.ByteBuffer;

import mambo.rpc.auth.AuthNone;
import mambo.rpc.msg.CallMessageBody;

public class RpcBindGetTimeBody extends CallMessageBody {
	
	public RpcBindGetTimeBody() {
		super(RpcBindV4.RPC_VERSION,
				RpcBindV4.PROGRAM_ID,
				RpcBindV4.PROGRAM_VERSION,
				RpcBindGetTime.PROCEDURE_ID,
				new AuthNone(),
				new AuthNone()
				);
	}
	
	public ByteBuffer serializeToXdr(ByteBuffer buffer) {
		super.serializeToXdr(buffer);
		return buffer;
	}
	
}
