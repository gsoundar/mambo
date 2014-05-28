package mambo.rpc.service.nfsv3.msg;

import java.nio.ByteBuffer;

import mambo.rpc.function.ReturnValue;
import mambo.rpc.function.ReturnValueFactory;

public class Nfs3GetAttrResponseFactory implements ReturnValueFactory {

	@Override
	public ReturnValue buildReturnValueFromXdr(ByteBuffer buffer) {
		return new Nfs3GetAttrResponse(buffer);
	}

}
