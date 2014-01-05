package mambo.rpc.service.add;

import java.nio.ByteBuffer;

import mambo.rpc.auth.AuthNone;
import mambo.rpc.msg.CallMessageBody;

public class AddCallMessageBody extends CallMessageBody {

	final IntPair args;
	
	public AddCallMessageBody(int x, int y) {
		super(AddService.RPC_VERSION, 
				AddService.PROGRAM_ID, 
				AddService.PROGRAM_VERSION, 
				AddFunction.PROCEDURE_NUM,
				new AuthNone(),
				new AuthNone()
				);
		args = new IntPair(x,y);
	}
	
	public ByteBuffer serializeToXdr(ByteBuffer buffer) {
		super.serializeToXdr(buffer);
		args.serializeToXdr(buffer);
		return buffer;
	}
	
}
