package mambo.rpc.service.rpcbind.types;

import java.nio.ByteBuffer;

import mambo.rpc.function.CallParameter;
import mambo.rpc.function.ReturnValue;
import mambo.rpc.xdr.Xdr;

public class RpcBindInfo implements CallParameter, ReturnValue {

	final int programNumber;
	final int versionNumber;
	final String networkId;
	final String address;
	final String owner;
	
	public RpcBindInfo(int programNumber, int versionNumber, String networkId, String address, String owner) {
		this.programNumber = programNumber;
		this.versionNumber = versionNumber;
		this.networkId = networkId;
		this.address = address;
		this.owner = owner;
	}
	
	public RpcBindInfo(ByteBuffer buffer) {
		programNumber = Xdr.decodeInteger(buffer);
		versionNumber = Xdr.decodeInteger(buffer);
		networkId = Xdr.decodeString(buffer);
		address = Xdr.decodeString(buffer);
		owner = Xdr.decodeString(buffer);
	}

	public ByteBuffer serializeToXdr(ByteBuffer buffer) {
		Xdr.encodeInt(buffer, programNumber);
		Xdr.encodeInt(buffer, versionNumber);
		Xdr.encodeString(buffer, networkId);
		Xdr.encodeString(buffer, address);
		Xdr.encodeString(buffer, owner);
		return buffer;
	}
	
	
	
}
