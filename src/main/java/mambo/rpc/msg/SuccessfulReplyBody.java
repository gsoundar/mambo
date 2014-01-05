package mambo.rpc.msg;

import java.nio.ByteBuffer;

import mambo.rpc.AcceptStatus;
import mambo.rpc.auth.OpaqueAuth;
import mambo.rpc.function.ReturnValue;
import mambo.rpc.function.ReturnValueFactory;

public class SuccessfulReplyBody extends AcceptedReplyBody {
	
	final ReturnValue returnValue;
	
	public SuccessfulReplyBody(OpaqueAuth verf, ReturnValue returnValue) {
		super(AcceptStatus.SUCCESS, verf);
		this.returnValue = returnValue;
	}
	
	public SuccessfulReplyBody(ByteBuffer buffer, OpaqueAuth verf, ReturnValueFactory returnValuesBuilder) {
		super(AcceptStatus.SUCCESS, verf);
		this.returnValue = returnValuesBuilder.buildReturnValueFromXdr(buffer);
	}
	
	public ReturnValue getReturnValue() {
		return returnValue;
	}
	
	public ByteBuffer serializeToXdr(ByteBuffer buffer) {
		super.serializeToXdr(buffer);
		returnValue.serializeToXdr(buffer);
		return buffer;
	}

}
