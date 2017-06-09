package edu.training.hiveudf;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.apache.hadoop.hive.ql.exec.Description;
import org.apache.hadoop.hive.ql.exec.UDF;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.JavaStringObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.PrimitiveObjectInspectorFactory;
import org.apache.hadoop.io.Text;

/***
 *
 * @author siyer
 * @version 1.0
 * @category Hive UDF
 *
 * Decryptor UDF: Decrypts the hive table fields that were encrypted using the speicified charset.
 *
 */

@Description(name = "Decrypt", value = "returns the original decrypted string.", extended = "select decrypt(e_w_legal_name) from test.names limit 10;")
public class Decrypt extends UDF {

	private Text resultString = null;
	private String result = null;

	public Text evaluate(final Text encryptedString) {
		JavaStringObjectInspector stringInspector;
		stringInspector = PrimitiveObjectInspectorFactory.javaStringObjectInspector;
		String input = stringInspector.getPrimitiveJavaObject(encryptedString);
		if (StringUtils.isEmpty(input)) {
			 result = "";
		} else if (StringUtils.isBlank(input)) {
			result = "";
		} else {
		    result = decrypt(input);
		}
		resultString = new Text(result);
		return resultString;
	}

	private String getShiftedCharset( int shiftRightCount, String originalCharSet) {
		StringBuilder shiftedCharsetBuilder = new StringBuilder(originalCharSet.substring(shiftRightCount));
		shiftedCharsetBuilder.append(originalCharSet.substring(0, shiftRightCount));
		return shiftedCharsetBuilder.toString();
	}

	/*
	 * decrypt method takes the encrypted field as parameter,
	 * reads the first character and last character from that string and caluclates the shiftcount.
	 * Takes the characterset from the properties file, generates new charset using the shiftcount which was used for encryption.
	 * Reads each character from the encrypted string, replaces it with the character in the index of newly created charset to get the original string.
	 */

	public String decrypt(String encryptedString) {
		Properties properties = new Properties();
		InputStream inputFile = null;
		StringBuilder originalString = new StringBuilder();
		Character firstCharacter = encryptedString.charAt(0);
		Character lastCharacter = encryptedString.charAt(encryptedString.length()-1);
		Integer shiftCount = Character.getNumericValue(firstCharacter) * 10 + Character.getNumericValue(lastCharacter);
		try {
			inputFile = getClass().getResourceAsStream("/resources/charset.properties");
			properties.load(inputFile);
			String originalCharSet = properties.getProperty("charset");
			if(StringUtils.containsOnly(encryptedString, originalCharSet)) {
				String shiftedCharSet= getShiftedCharset(shiftCount,originalCharSet);
				encryptedString = encryptedString.substring(1, encryptedString.length()-1);
				for(char c : encryptedString.toCharArray()) {
					int index = shiftedCharSet.indexOf(c);
					originalString.append(originalCharSet.toCharArray()[index]);
				}
			}else {
				originalString.append("Invalid string");
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		} catch (NullPointerException npe) {
			npe.printStackTrace();
		}
		finally {
			if (inputFile != null) {
				try {
					inputFile.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return originalString.toString();
	}
}
