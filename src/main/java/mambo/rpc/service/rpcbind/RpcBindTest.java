package mambo.rpc.service.rpcbind;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RpcBindTest {

	public static final Logger LOG = LoggerFactory.getLogger(RpcBindTest.class);
	
	public static void main(String args[]) throws Exception {
		
		String hostname;
		
		if(args.length != 1) {
			System.err.println("USAGE: " + RpcBindTest.class.getSimpleName() + " <hostname>");
			System.exit(-1);
		}
		
		hostname = args[0];
		
		RpcBindV4 service = new RpcBindV4(hostname);
		
		for(int i=0; i < 5; i++) {
			
			String addAddress = service.getAddress(RpcBindV4.PROGRAM_ID, RpcBindV4.PROGRAM_VERSION);
			LOG.info("Add service is running on |" + addAddress + "|");
			
			//int timeOnServer = service.getTime();
			//LOG.info("RPC Bind server time is " + timeOnServer);
			
			Thread.sleep(1000);
		}
		
		service.shutdown();
		
	}
	
}
