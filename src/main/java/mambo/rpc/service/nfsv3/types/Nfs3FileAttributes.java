package mambo.rpc.service.nfsv3.types;

import java.nio.ByteBuffer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import mambo.rpc.function.CallParameter;
import mambo.rpc.xdr.Xdr;

public class Nfs3FileAttributes implements CallParameter {

	Nfs3FileType type;
	int mode;
	int nlink;
	int uid;
	int gid;
	long size;
	long used;
	Nfs3SpecData rdev;
	long fsid;
	long fileid;
	Nfs3Time atime;
	Nfs3Time mtime;
	Nfs3Time ctime;
	
	public static final Logger LOG = LoggerFactory.getLogger(Nfs3FileAttributes.class);
	
	public Nfs3FileAttributes(ByteBuffer buffer) {
		type = new Nfs3FileType(Xdr.decodeInteger(buffer));
		mode = Xdr.decodeInteger(buffer);
		nlink = Xdr.decodeInteger(buffer);
		uid = Xdr.decodeInteger(buffer);
		gid = Xdr.decodeInteger(buffer);
		size = Xdr.decodeLong(buffer);
		used = Xdr.decodeLong(buffer);
		rdev = new Nfs3SpecData(buffer);
		fsid = Xdr.decodeLong(buffer);
		fileid = Xdr.decodeLong(buffer);
		atime = new Nfs3Time(buffer);
		mtime = new Nfs3Time(buffer);
		ctime = new Nfs3Time(buffer);
	}

	public Nfs3FileType getType() {
		return type;
	}

	public int getMode() {
		return mode;
	}
	
	public int getNLink() {
		return nlink;
	}
	
	public int getUid() {
		return uid;
	}
	
	public int getGid() {
		return gid;
	}
	
	public long getSize() {
		return size;
	}
	
	public long getUsed() {
		return used;
	}
	
	public Nfs3SpecData getRDev() {
		return rdev;
	}
	
	public long getFsId() {
		return fsid;
	}
	
	public long getFileId() {
		return fileid;
	}
	
	public Nfs3Time getATime() {
		return atime;
	}
	
	public Nfs3Time getMTime() {
		return mtime;
	}
	
	public Nfs3Time getCTime() {
		return ctime;
	}
	
	@Override
	public ByteBuffer serializeToXdr(ByteBuffer buffer) {
		buffer = Xdr.encodeInt(buffer, type.getValue());
		Xdr.encodeInt(buffer, nlink);
		Xdr.encodeInt(buffer, uid);
		Xdr.encodeInt(buffer, gid);
		Xdr.encodeLong(buffer, size);
		Xdr.encodeLong(buffer, used);
		rdev.serializeToXdr(buffer);
		Xdr.encodeLong(buffer, fsid);
		Xdr.encodeLong(buffer, fileid);
		atime.serializeToXdr(buffer);
		mtime.serializeToXdr(buffer);
		ctime.serializeToXdr(buffer);
		return buffer;
	}
	
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("FileAttributes: ").append("\n");
		buffer.append("type=").append(type.getValue()).append(" mode=").append(mode).append("\n");
		buffer.append("uid=").append(uid).append(" gid=").append(gid).append("\n");
		buffer.append("size=").append(size).append(" used=").append(used).append("\n");
		buffer.append("fsid=").append(fsid).append(" fileid=").append(fileid).append("\n");
		buffer.append("mtime=").append(mtime).append(" atime=").append(atime).append(" ctime=").append(ctime).append("\n");
		return buffer.toString();
	}
	
}
