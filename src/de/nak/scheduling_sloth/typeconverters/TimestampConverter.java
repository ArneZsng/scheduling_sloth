package de.nak.scheduling_sloth.typeconverters;

import com.opensymphony.xwork2.conversion.TypeConversionException;
import org.apache.struts2.util.StrutsTypeConverter;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * Created by kevinscholz on 08/11/14.
 */

public class TimestampConverter extends StrutsTypeConverter {
    private static final SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm");

    @Override
    public Object convertFromString(Map context, String[] values, Class toClass) throws TypeConversionException {
        try {
            if (values == null || values.length == 0) {
                return null;
            }
            return new java.sql.Timestamp(sdf.parse(values[0]).getTime());
        } catch (Exception e) {
            throw new TypeConversionException(e);
        }
    }

    @Override
    public String convertToString(Map context, Object object)  throws TypeConversionException  {
        try {
            if (object instanceof Timestamp) {
                return sdf.format(new Date(((Timestamp)object).getTime()));
            }
            return "";
        } catch(Exception e) {
            throw new TypeConversionException(e);
        }
    }

}
