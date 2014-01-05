package mambo.rpc.service.add;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import mambo.rpc.XidSequencer;
import mambo.rpc.function.BaseRpcFunction;
import mambo.rpc.msg.CallMessage;

public class AddFunction extends BaseRpcFunction {

	public static final int PROCEDURE_NUM = 1;

	public static final Logger LOG = LoggerFactory.getLogger(AddFunction.class);
	
	public AddFunction(int x, int y) {
		super(new IntPairFactory(), new IntFactory());
		int xid = XidSequencer.DEFAULT_SEQUENCER.getNextXid();
		callMessage = new CallMessage(xid, new AddCallMessageBody(xid, y));
	}

}
