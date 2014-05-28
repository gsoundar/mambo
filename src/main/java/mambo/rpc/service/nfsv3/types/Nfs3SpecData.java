package mambo.rpc.service.nfsv3.types;

import java.nio.ByteBuffer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import mambo.rpc.function.CallParameter;
import mambo.rpc.function.ReturnValue;
import mambo.rpc.xdr.Xdr;

public class Nfs3SpecData implements CallParameter, ReturnValue {

	int specdata1;
	int specdata2;
	
	public static final Logger LOG = LoggerFactory.getLogger(Nfs3SpecData.class);
	
	public Nfs3SpecData(ByteBuffer buffer) {
		specdata1 = Xdr.decodeInteger(buffer);
		specdata2 = Xdr.decodeInteger(buffer);
	}
	
	public int getSpecData1() {
		return specdata1;
	}

	public int getSpecData2() {
		return specdata2;
	}
	
	@Override
	public ByteBuffer serializeToXdr(ByteBuffer buffer) {
		Xdr.encodeInt(buffer, specdata1);
		Xdr.encodeInt(buffer, specdata2);
		return buffer;
	}
	
}
