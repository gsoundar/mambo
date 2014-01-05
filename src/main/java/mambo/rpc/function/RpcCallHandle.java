package mambo.rpc.function;

import java.util.concurrent.CountDownLatch;


public class RpcCallHandle {

	final RpcFunction function;
	final CountDownLatch signalDone;
	RpcFunctionState state;
	
	public RpcCallHandle(RpcFunction function) {
		this.function = function;
		this.state = RpcFunctionState.HANDLE_CREATED;
		this.signalDone = new CountDownLatch(1);
	}
	
	public RpcFunction getFunction() {
		return function;
	}
	
	public RpcFunctionState setState(RpcFunctionState state) {
		this.state = state;
		return this.state;
	}
	
	public RpcFunctionState getState() {
		return state;
	}
	
	public void waitForCompletion() throws InterruptedException {
		signalDone.await();
	}
	
	public void signalCompletion() {
		signalDone.countDown();
	}
	
}
