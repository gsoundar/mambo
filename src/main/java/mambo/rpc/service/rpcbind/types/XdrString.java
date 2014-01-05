package mambo.rpc.service.rpcbind.types;

import java.nio.ByteBuffer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import mambo.rpc.function.ReturnValue;
import mambo.rpc.xdr.Xdr;

public class XdrString implements ReturnValue {

	final String value;
	
	public static final Logger LOG = LoggerFactory.getLogger(XdrString.class);
	
	public XdrString(String value) {
		this.value = value;
	}

	public XdrString(ByteBuffer buffer) {
		LOG.info("Buffer position is " + buffer.position());
		this.value = Xdr.decodeString(buffer);
	}
	
	public String getValue() {
		return value;
	}
	
	public ByteBuffer serializeToXdr(ByteBuffer buffer) {
		Xdr.encodeString(buffer, value);
		return buffer;
	}
	
}
