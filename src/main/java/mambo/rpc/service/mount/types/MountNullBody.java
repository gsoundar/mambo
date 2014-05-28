package mambo.rpc.service.mount.types;

import mambo.rpc.auth.AuthNone;
import mambo.rpc.msg.CallMessageBody;
import mambo.rpc.service.mount.MountV3;

public class MountNullBody extends CallMessageBody {

	public MountNullBody() {
		super(MountV3.RPC_VERSION,
				MountV3.PROGRAM_ID,
				MountV3.PROGRAM_VERSION,
				MountNull.PROCEDURE_ID,
				new AuthNone(),
				new AuthNone()
				);
	}
	
}
