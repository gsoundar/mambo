package mambo.rpc.auth;

import java.nio.ByteBuffer;

import mambo.rpc.xdr.Xdr;


public class AuthUnix extends OpaqueAuth {

	private final int timestamp;
	private final String machine;
	private final int uid;
	private final int gid;
	private final int gids[];
	private final int length;
	
	public AuthUnix() {
		timestamp = 0;
		machine = null;
		uid = 0;
		gid = 0;
		gids = null;
		length = 0;
	}
	
	public AuthUnix(int timestamp, String machine, int uid, int gid, int gids[]) {
		super(AuthFlavor.AUTH_SYS);
		this.timestamp = timestamp;
		this.machine = machine;
		this.uid = uid;
		this.gid = gid;
		this.gids = gids;
		this.length = 4 + /* uid */
						4 + /* gid */
						4 + /* len of gids */
						4*gids.length +  /* gids */
						4 + /* len of machine */
						machine.length() + /* machine */
						((4 - (machine.length() & 3)) & 3) + /* padding for machine */
						4; /* timestamp */
	}
	
	public AuthUnix(ByteBuffer buffer) {
		
		//Use this to validate the data structure
		int msgLength = Xdr.decodeInteger(buffer);
		
		this.timestamp = Xdr.decodeInteger(buffer);
		this.machine = Xdr.decodeString(buffer);
		this.uid = Xdr.decodeInteger(buffer);
		this.gid = Xdr.decodeInteger(buffer);
		this.gids = Xdr.decodeIntegerArray(buffer);
		
		// Crosscheck computed length with length specified in message
		this.length = 4 + /* uid */
				4 + /* gid */
				4 + /* len of gids */
				4*gids.length +  /* gids */
				4 + /* len of machine */
				machine.length() + /* machine */
				((4 - (machine.length() & 3)) & 3) + /* padding for machine */
				4; /* timestamp */
		assert(msgLength == length);
		
		return;
	}

	public ByteBuffer serializeToXdr(ByteBuffer buffer) {
		Xdr.encodeInt(buffer, getAuthFlavor().getValue());
		Xdr.encodeInt(buffer, length);
		Xdr.encodeInt(buffer, timestamp);
		Xdr.encodeString(buffer, machine);
		Xdr.encodeInt(buffer, uid);
		Xdr.encodeInt(buffer, gid);
		Xdr.encodeIntArray(buffer, gids);		
		return buffer;
	}

}
