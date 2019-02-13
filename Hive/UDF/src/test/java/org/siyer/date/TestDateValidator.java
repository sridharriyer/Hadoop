package org.siyer.date;

import org.apache.hadoop.hive.ql.metadata.HiveException;
import org.apache.hadoop.io.Text;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class TestDateValidator {

    private DateValidator validateDate;

    @Before
    public void setUp() {
        validateDate = new DateValidator();
    }

    @Test
    public void testValidDate() throws HiveException {
        assertEquals(new Text("02/28/2019"), validateDate.evaluate(new Text("02/28/2019"), "MM/dd/yyyy"));
    }

    @Test(expected = HiveException.class)
    public void testInValidDate() throws HiveException {
        validateDate.evaluate(new Text("02/29/2019"), "MM/dd/yyyy");
    }

}
