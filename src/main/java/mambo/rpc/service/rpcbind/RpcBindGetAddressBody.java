package mambo.rpc.service.rpcbind;

import java.nio.ByteBuffer;

import mambo.rpc.auth.AuthNone;
import mambo.rpc.msg.CallMessageBody;
import mambo.rpc.service.rpcbind.types.RpcBindInfo;

public class RpcBindGetAddressBody extends CallMessageBody {

	final RpcBindInfo info;
	
	public RpcBindGetAddressBody(RpcBindInfo info) {
		super(RpcBindV4.RPC_VERSION,
				RpcBindV4.PROGRAM_ID,
				RpcBindV4.PROGRAM_VERSION,
				RpcBindGetAddress.PROCEDURE_ID,
				new AuthNone(),
				new AuthNone()
				);
		this.info = info;
	}
	
	public ByteBuffer serializeToXdr(ByteBuffer buffer) {
		super.serializeToXdr(buffer);
		info.serializeToXdr(buffer);
		return buffer;
	}
	
}
