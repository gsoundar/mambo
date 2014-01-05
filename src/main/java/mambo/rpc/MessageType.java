package mambo.rpc;

public final class MessageType {
	
	public static final MessageType CALL = new MessageType(0);
	public static final MessageType REPLY = new MessageType(1);
	
	private final int value;
	
	public MessageType(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + value;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MessageType other = (MessageType) obj;
		if (value != other.value)
			return false;
		return true;
	}

	@Override
	public String toString() {
		if(value == CALL.getValue()) {
			return "CALL";
		} else if(value == REPLY.getValue()) {
			return "REPLY";
		} else {
			throw new RuntimeException("No such message type!");
		}
	}
	
}
