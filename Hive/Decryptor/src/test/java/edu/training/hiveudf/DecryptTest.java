package edu.training.hiveudf;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class DecryptTest {

	@Test
	public void testDecrypt() {
		assertEquals("Taylour Locke",new Decrypt().decrypt("70<QDGMJY6G{C[5"));
	}
}
