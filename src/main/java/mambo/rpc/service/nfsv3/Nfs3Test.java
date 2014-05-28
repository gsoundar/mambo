package mambo.rpc.service.nfsv3;

import java.net.InetAddress;
import java.net.InetSocketAddress;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import mambo.rpc.auth.AuthUnix;
import mambo.rpc.service.mount.MountV3;
import mambo.rpc.service.mount.types.MountResult;
import mambo.rpc.service.nfsv3.msg.Nfs3GetAttrResponse;
import mambo.rpc.service.nfsv3.msg.Nfs3LookupResponse;
import mambo.rpc.service.nfsv3.types.Nfs3DirectoryOperationArgs;
import mambo.rpc.service.nfsv3.types.Nfs3FileAttributes;
import mambo.rpc.service.nfsv3.types.Nfs3FileHandle;
import mambo.rpc.service.rpcbind.RpcBindV4;

public class Nfs3Test {

	public static final Logger LOG = LoggerFactory.getLogger(Nfs3Test.class);
	
	public static void main(String args[]) throws Exception {
		
		String hostname;
		
		if(args.length != 1) {
			System.err.println("USAGE: " + Nfs3Test.class.getSimpleName() + " <hostname>");
			System.exit(-1);
		}
		
		hostname = args[0];
		
		/* Talk to RPCBIND and find MOUNT port */
		RpcBindV4 rpcbind = new RpcBindV4(hostname);
		InetSocketAddress mountAddress = rpcbind.convertToSocketAddress(rpcbind.getAddress(MountV3.PROGRAM_ID, MountV3.PROGRAM_VERSION));
		LOG.info("MOUNT is running on host: " + mountAddress.getHostName() + " port:" + mountAddress.getPort());
		
		InetSocketAddress nfsAddress = rpcbind.convertToSocketAddress(rpcbind.getAddress(Nfs3.PROGRAM_ID, Nfs3.PROGRAM_VERSION));
		LOG.info("NFSv3 is running on host: " + nfsAddress.getHostName() + " port:" + nfsAddress.getPort());
		rpcbind.shutdown();
		
		/* Connect to MOUNT */
		MountV3 mount = new MountV3(mountAddress.getHostName(), mountAddress.getPort());
		
		/* Make up a UNIX authentication */
		String localhost = "localhost"; //InetAddress.getLocalHost().getHostAddress();
		int ts = new Long(System.currentTimeMillis()).intValue();
		int gids[] = {0};
		AuthUnix credentials = new AuthUnix(ts, localhost, 0, 0, gids);
		
		/* Do a mount command */
		MountResult result = mount.mount(credentials, "/export/hadoop/");
		LOG.info("Mount returned handle of " + result.getHandleAsString());
		
		/* Connect to NFS */
		Nfs3 nfs = new Nfs3(nfsAddress.getHostName(), nfsAddress.getPort());
		
		/* Null operation */
		//nfs.doNull();
		
		/* GetAttr of root */
		Nfs3FileAttributes attributes = nfs.getFileAttributes(credentials, new Nfs3FileHandle(result.getHandle()));
		System.out.println(attributes);
		
		/* Lookup a file */
		Nfs3LookupResponse blah = nfs.lookup(credentials, new Nfs3DirectoryOperationArgs(new Nfs3FileHandle(result.getHandle()), "pg5000.txt"));
		System.out.println(blah);
		
		/* Shutdown */
		nfs.shutdown();
		mount.shutdown();
		
	}
	
}
