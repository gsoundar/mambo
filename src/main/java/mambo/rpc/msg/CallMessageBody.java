package mambo.rpc.msg;

import java.nio.ByteBuffer;

import mambo.rpc.MessageBody;
import mambo.rpc.auth.AuthFactory;
import mambo.rpc.auth.OpaqueAuth;
import mambo.rpc.xdr.Xdr;


public abstract class CallMessageBody extends MessageBody {

	final int rpcVersion;
	final int program;
	final int version;
	final int procedure;
	final OpaqueAuth credentials;
	final OpaqueAuth verification;
	
	public CallMessageBody(int rpcVersion, int program, int version, int procedure, 
			OpaqueAuth credentials, OpaqueAuth verification) {
		this.rpcVersion = rpcVersion;
		this.program = program;
		this.version = version;
		this.procedure = procedure;
		this.credentials = credentials;
		this.verification = verification;
		assert(rpcVersion == 2);
	}
	
	public CallMessageBody(ByteBuffer buffer) {
		this.rpcVersion = Xdr.decodeInteger(buffer);
		this.program = Xdr.decodeInteger(buffer);
		this.version = Xdr.decodeInteger(buffer);
		this.procedure = Xdr.decodeInteger(buffer);
		this.credentials = AuthFactory.buildFromXdr(buffer);
		this.verification = AuthFactory.buildFromXdr(buffer);
		assert(rpcVersion == 2);
	}
	
	public ByteBuffer serializeToXdr(ByteBuffer buffer) {
		Xdr.encodeInt(buffer, rpcVersion);
		Xdr.encodeInt(buffer, program);
		Xdr.encodeInt(buffer, version);
		Xdr.encodeInt(buffer, procedure);
		credentials.serializeToXdr(buffer);
		verification.serializeToXdr(buffer);
		return buffer;
	}

}
