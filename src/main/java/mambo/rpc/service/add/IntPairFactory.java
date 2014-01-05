package mambo.rpc.service.add;

import java.nio.ByteBuffer;

import mambo.rpc.function.CallParameter;
import mambo.rpc.function.CallParameterFactory;
import mambo.rpc.xdr.Xdr;

public class IntPairFactory implements CallParameterFactory {

	public CallParameter buildCallParameterFromXdr(ByteBuffer buffer) {
		int x = Xdr.decodeInteger(buffer);
		int y = Xdr.decodeInteger(buffer);
		return new IntPair(x,y);
	}

}
