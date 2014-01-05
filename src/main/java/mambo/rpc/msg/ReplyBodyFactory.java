package mambo.rpc.msg;

import java.nio.ByteBuffer;

import mambo.rpc.function.ReturnValueFactory;

public interface ReplyBodyFactory {

	ReplyMessageBody buildFromXdr(ByteBuffer buffer, ReturnValueFactory factory);
	
}
