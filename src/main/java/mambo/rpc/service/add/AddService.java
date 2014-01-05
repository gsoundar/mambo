package mambo.rpc.service.add;

import java.io.IOException;

import mambo.rpc.service.MultithreadedRpcService;

public class AddService extends MultithreadedRpcService {

	public static final int RPC_VERSION = 2;
	public static final int PROGRAM_ID = 0x23451111;
	public static final int PROGRAM_VERSION = 1;
	
	public AddService(String hostname, int port) throws IOException {
		super(hostname, port);
	}
}
