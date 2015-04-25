package dk.schioler.tools.timeregistration.report.daily;

import java.util.Arrays;

import org.apache.commons.codec.binary.Base64;

public class EncodingCheck {
	public static final String ASCII = "ASCII";
	public static final String UTF8 = "UTF-8";
	public static final String UTF16 = "UTF-16";
	public static final String ISO8859_1 = "ISO8859_1";

	public static void main(String[] args) {
		try {
			String oddChars = "ÆØÅ";
			System.out.println("src=" + oddChars);

			String charSet1 = UTF8;
			String charSet2 = UTF8;
			String charSet3 = ASCII;
			String charSet4 = UTF8;

			byte[] oddBytes = oddChars.getBytes(charSet1);
			System.out.println("oddBytes(SrcString, " + charSet1 + "):"
					+ Arrays.toString(oddBytes));

			byte[] encodeBase64 = Base64.encodeBase64(oddBytes);
			System.out.println("   preTransportBytes  ="
					+ Arrays.toString(encodeBase64));

			String transport = new String(encodeBase64, charSet2);
			System.out.println("      Encoded for transport (" + charSet2
					+ "):" + transport);

			System.out.println("----------");

			System.out.println("      received transport=" + transport);
			byte[] postTrans = transport.getBytes(charSet3);
			System.out.println("   postTransportBytes(" + charSet3 + ")="
					+ Arrays.toString(postTrans));

			byte[] decodeBase64 = Base64.decodeBase64(postTrans);
			System.out.println("decodedBytes("
					+ Arrays.toString(decodeBase64));
			String result = new String(decodeBase64, charSet4);
			System.out.println("result(" + charSet4 + ")='" + result + "'");

		} catch (Exception e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		}

	}
}
