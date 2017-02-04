package edu.training.hiveudf;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class DecryptTest {

	Decrypt udf;

	@Before
	public void setUp() {
		udf = new Decrypt();
	}

	@Test
	public void testID() {
		assertEquals(new Text("3795"), udf.evaluate(new Text("1@+*`5")));
	}

	@Test
	public void testName() {
		assertEquals(new Text("Robert Larson"), udf.evaluate(new Text("4d.?+57Óí(56.-8")));
	}
}
