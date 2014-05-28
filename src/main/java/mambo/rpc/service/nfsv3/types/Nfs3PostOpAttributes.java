package mambo.rpc.service.nfsv3.types;

import java.nio.ByteBuffer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import mambo.rpc.function.CallParameter;
import mambo.rpc.function.ReturnValue;
import mambo.rpc.xdr.Xdr;

public class Nfs3PostOpAttributes implements CallParameter, ReturnValue {

	final boolean hasAttributes;
	final Nfs3FileAttributes attributes;
	
	public static final Logger LOG = LoggerFactory.getLogger(Nfs3PostOpAttributes.class);
	
	public Nfs3PostOpAttributes(Nfs3FileAttributes attributes) {
		this.attributes = attributes;
		if(attributes != null) {
			hasAttributes = true;
		} else {
			hasAttributes = false;
		}
	}
	
	public Nfs3PostOpAttributes(ByteBuffer buffer) {
		if(Xdr.decodeInteger(buffer) != 0) {
			hasAttributes = true;
			attributes = new Nfs3FileAttributes(buffer);
		} else {
			hasAttributes = false;
			attributes = null;
		}
	}

	public boolean hasAttributes() {
		return hasAttributes;
	}

	public Nfs3FileAttributes getAttributes() {
		return attributes;
	}
	
	@Override
	public ByteBuffer serializeToXdr(ByteBuffer buffer) {
		if(hasAttributes) {
			Xdr.encodeInt(buffer, 1);
			attributes.serializeToXdr(buffer);
		} else {
			Xdr.encodeInt(buffer, 0);
		}
		return buffer;
	}
	
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("PostOp Attributes:").append(hasAttributes).append("\n");
		buffer.append(attributes);
		return buffer.toString();
	}
	
}
