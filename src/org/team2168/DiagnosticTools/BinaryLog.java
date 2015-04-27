package org.team2168.DiagnosticTools;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;

import javax.xml.bind.DatatypeConverter;

public class BinaryLog {
	final protected static char[] hexArray = "0123456789ABCDEF".toCharArray();
	
	/**
	 * Converts a loaded log into binary format for advanced visual feedback
	 * @param mainLog Log file loaded
	 * @param name Name of the log file 
	 */
	public static void textLogToBinaryLog(LogFileData mainLog, String name) {
		System.out.println("Converting " + name);
		
		
			OutputStream out;
			try {
				out = new FileOutputStream("binary/" + name + ".dbin");
				DataOutputStream os = new DataOutputStream(out);
				out.write(name.getBytes());
				out.write((byte) 00);
				
				for (DataPoint p : mainLog.PointData) {
					// Flush data to file
					os.writeFloat((float) p.X);
					os.writeFloat((float) p.Y);
					os.writeFloat((float) p.Heading);
					
					os.writeFloat((float) p.LeftSpeed);
					os.writeFloat((float) p.RightSpeed);
					
					os.writeFloat((float) p.RightVoltage[0]);
					os.writeFloat((float) p.RightVoltage[1]);
					os.writeFloat((float) p.LeftVoltage[0]);
					os.writeFloat((float) p.LeftVoltage[1]);
					
					os.writeFloat((float) p.LiftVoltage);
					os.writeFloat((float) p.LiftCurrent);
					os.writeFloat((float) p.LiftPosition);
					os.close();
					out.close();	
				}
			}catch (Exception ex) { }
	}
	
	public static byte[] hexStringToByteArray(String s) {
	    return DatatypeConverter.parseHexBinary(s);
	}
	
	public static String bytesToHex(byte[] bytes) {
	    char[] hexChars = new char[bytes.length * 2];
	    for ( int j = 0; j < bytes.length; j++ ) {
	        int v = bytes[j] & 0xFF;
	        hexChars[j * 2] = hexArray[v >>> 4];
	        hexChars[j * 2 + 1] = hexArray[v & 0x0F];
	    }
	    return new String(hexChars);
	}
	
	//public static byte[] hexStringToByteArray(String s) {
	//	byte[] b = new BigInteger(s,16).toByteArray();
	//	return b;
	//}
	
	
}
