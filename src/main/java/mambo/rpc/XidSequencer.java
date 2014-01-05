package mambo.rpc;

import java.util.concurrent.atomic.AtomicInteger;

public final class XidSequencer {

	public static final XidSequencer DEFAULT_SEQUENCER = new XidSequencer();
	
	public final AtomicInteger sequencer;
	
	public XidSequencer() {
		sequencer = new AtomicInteger();
	}
	
	public int getNextXid() {
		return sequencer.incrementAndGet();
	}
	
}
