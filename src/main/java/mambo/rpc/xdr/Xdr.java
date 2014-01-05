package mambo.rpc.xdr;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Xdr {

	public static final int SIZE_OF_LONG = Long.SIZE / 8;
	public static final int SIZE_OF_INT = Integer.SIZE / 8;
	public static final int MAX_XDR_SIZE = 4 *1024 * 1024;
	
	private static final byte [] paddingZeros = { 0, 0, 0, 0 };
	
	public static final Logger LOG = LoggerFactory.getLogger(Xdr.class);
	
	public static ByteBuffer getBuffer() {
		ByteBuffer buffer = ByteBuffer.allocate(MAX_XDR_SIZE);
		buffer.clear();
		buffer.order(ByteOrder.BIG_ENDIAN);
		return buffer;
	}
	
	public static ByteBuffer encodeInt(ByteBuffer buffer, int number) {
		if(hasCapacity(buffer, SIZE_OF_INT)) {
			buffer.putInt(number);
			return buffer;
		} else {
			throw new RuntimeException("Out of space for integer");
		}
	}
	
	public static ByteBuffer encodeIntArray(ByteBuffer buffer, int[] numbers) {
		if(numbers != null) {
			int needed = SIZE_OF_INT + SIZE_OF_INT * numbers.length;
			if(hasCapacity(buffer, needed)) {
				buffer.putInt(numbers.length);
				for(int v : numbers) {
					buffer.putInt(v);
				}
				return buffer;
			} else {
				throw new RuntimeException("Out of space for integer");
			}
		} else {
			throw new RuntimeException("Integer array is null");
		}
	}
	
	public static ByteBuffer encodeLong(ByteBuffer buffer, long number) {
		if(hasCapacity(buffer, SIZE_OF_LONG)) {
			buffer.putLong(number);
			return buffer;
		} else {
			throw new RuntimeException("Out of space for long");
		}
	}
	
	public static ByteBuffer encodeLongArray(ByteBuffer buffer, long[] numbers) {
		if(numbers != null) {
			int needed = SIZE_OF_INT + SIZE_OF_LONG * numbers.length;
			if(hasCapacity(buffer, needed)) {
				buffer.putInt(numbers.length);
				for(long v : numbers) {
					buffer.putLong(v);
				}
				return buffer;
			} else {
				throw new RuntimeException("Out of space for long");
			}
		} else {
			throw new RuntimeException("Long array is null");
		}
	}
	
	public static ByteBuffer encodeString(ByteBuffer buffer, String string) {
		if(string != null) {
			return encodeDynamicOpaque(buffer, string.getBytes());
		} else {
			throw new RuntimeException("String is null");
		}
	}
	
	public static ByteBuffer encodeOpaque(ByteBuffer buffer, byte[] bytes, int offset, int length) {
		if(bytes != null) {
			int padding = (4 - (length & 3)) & 3;
			if(hasCapacity(buffer, length + padding)) {
				buffer.put(bytes, offset, length);
				buffer.put(paddingZeros, 0, padding);
				return buffer;
			} else {
				throw new RuntimeException("Out of space for byte array");
			}
		} else {
			throw new RuntimeException("Byte array is null");
		}
	}
	
	public static ByteBuffer encodeDynamicOpaque(ByteBuffer buffer, byte[] bytes) {
		if(bytes != null) {
			buffer.putInt(bytes.length);
			return encodeOpaque(buffer, bytes, 0, bytes.length);
		} else {
			throw new RuntimeException("Byte array is null");
		}
	}
	
	public static int decodeInteger(ByteBuffer buffer) {
		int value = buffer.getInt();
		return value;
	}
	
	public static int[] decodeIntegerArray(ByteBuffer buffer) {
		int length = buffer.getInt();
		int array[] = new int[length];
		for(int i = 0; i < length; i++) {
			array[i] = buffer.getInt();
		}
		return array;
	}
	
	public static long decodeLong(ByteBuffer buffer) {
		long value = buffer.getLong();
		return value;
	}
	
	public static long[] decodeLongArray(ByteBuffer buffer) {
		int length = buffer.getInt();
		long array[] = new long[length];
		for(int i = 0; i < length; i++) {
			array[i] = buffer.getLong();
		}
		return array;
	}
	
	public static String decodeString(ByteBuffer buffer) {
		int length = buffer.getInt();
		byte[] bytes = new byte[length];
		LOG.info("Encoded string is supposed to be " + length + " bytes long");
		decodeOpaque(buffer, bytes, 0, length);
		return new String(bytes);
	}
	
	public static byte[] decodeOpaque(ByteBuffer buffer, byte[] bytes, int offset, int length) {
		int padding = (4 - (length & 3)) & 3;
		buffer.get(bytes, offset, length);
		buffer.position(buffer.position() + padding);
		return bytes;
	}
	
	private static boolean hasCapacity(ByteBuffer buffer, int size) {
		if(buffer.remaining() >= size) {
			return true;
		}
		return false;
	}
	
}
