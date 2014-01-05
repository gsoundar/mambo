package mambo.rpc.service.add;

import java.nio.ByteBuffer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import mambo.rpc.function.ReturnValue;
import mambo.rpc.function.ReturnValueFactory;
import mambo.rpc.xdr.Xdr;

public class IntFactory implements ReturnValueFactory {

	public static final Logger LOG = LoggerFactory.getLogger(IntFactory.class);
	
	public ReturnValue buildReturnValueFromXdr(ByteBuffer buffer) {
		LOG.info("Buffer position is " + buffer.position());
		//TODO: I can't account for these 4 bytes why??
		//int y = Xdr.decodeInteger(buffer);
		int x = Xdr.decodeInteger(buffer);
		LOG.info("Decoding the integer return value of " + x);
		return new Int(x);
	}

}
