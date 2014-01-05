package mambo.rpc;

import java.nio.ByteBuffer;

import mambo.rpc.xdr.XdrSerializable;
import mambo.rpc.xdr.Xdr;

public class RpcMessage implements XdrSerializable {

	public static final int MAX_XDR_SIZE = 512 * 1024;
	
	final Integer xid;
	final MessageType type;
	final protected MessageBody body;
	
	public RpcMessage(MessageType type, Integer xid, MessageBody body) {
		this.xid = xid;
		this.type = type;
		this.body = body;
	}
	
	public MessageType getMessageType() {
		return type;
	}
	
	public Integer getXid() {
		return xid;
	}
	
	public MessageBody getMessageBody() {
		return body;
	}
	
	public ByteBuffer serializeToXdr(ByteBuffer buffer) {
		Xdr.encodeInt(buffer, xid);
		Xdr.encodeInt(buffer, type.getValue());
		body.serializeToXdr(buffer);
		return buffer;
	}

}
