package org.siyer.date;

import org.apache.hadoop.hive.ql.metadata.HiveException;
import org.apache.hadoop.io.Text;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.apache.hadoop.hive.ql.exec.UDF;

public class DateValidator extends UDF {

    public Text evaluate(final Text dateString, final String dateFormat) throws HiveException  {
        DateTime date = null;
        try {
            DateTimeFormatter fmt = DateTimeFormat.forPattern(dateFormat);
            date =  fmt.parseDateTime(dateString.toString());
        }catch (Exception e) {
            e.printStackTrace();
            throw new HiveException();
        }
        return dateString;
    }
}
