package mambo.rpc.service.mount;

import java.net.InetAddress;
import java.net.InetSocketAddress;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import mambo.rpc.auth.AuthNone;
import mambo.rpc.auth.AuthUnix;
import mambo.rpc.service.mount.types.MountResult;
import mambo.rpc.service.rpcbind.RpcBindV4;

public class MountTest {

	public static final Logger LOG = LoggerFactory.getLogger(MountTest.class);
	
	public static void main(String args[]) throws Exception {
		
		String hostname;
		
		if(args.length != 1) {
			System.err.println("USAGE: " + MountTest.class.getSimpleName() + " <hostname>");
			System.exit(-1);
		}
		
		hostname = args[0];
		
		/* Talk to RPCBIND and find MOUNT port */
		RpcBindV4 rpcbind = new RpcBindV4(hostname);
		InetSocketAddress mountAddress = rpcbind.convertToSocketAddress(rpcbind.getAddress(MountV3.PROGRAM_ID, MountV3.PROGRAM_VERSION));
		LOG.info("MOUNT is running on host: " + mountAddress.getHostName() + " port:" + mountAddress.getPort());
		for(int i = 0; i < 100; i++) {
			mountAddress = rpcbind.convertToSocketAddress(rpcbind.getAddress(MountV3.PROGRAM_ID, MountV3.PROGRAM_VERSION));
			LOG.info("MOUNT is running on host: " + mountAddress.getHostName() + " port:" + mountAddress.getPort());
		}
		rpcbind.shutdown();
		
		/* Connect to MOUNT */
		MountV3 mount = new MountV3(mountAddress.getHostName(), mountAddress.getPort());
		
		/* Null command */
		mount.doNull();
		
		/* Make up a UNIX authentication */
		String localhost = InetAddress.getLocalHost().getHostAddress();
		int ts = new Long(System.currentTimeMillis()).intValue();
		int gids[] = {0};
		AuthUnix credentials = new AuthUnix(ts, localhost, 0, 0, gids);
		
		
		/* Do a mount command */
		MountResult result = mount.mount(credentials, "/export/hadoop");
		LOG.info("Mount returned handle of " + result.getHandleAsString());
		
		/* Shutdown MOUNT */
		mount.shutdown();
		
	}
	
}
