package de.nak.scheduling_sloth.typeconverters;

import com.opensymphony.xwork2.conversion.TypeConversionException;
import org.apache.struts2.util.StrutsTypeConverter;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * Timestamp converter for start and end dates, etc.
 *
 * @author      Kevin Scholz <kevin.scholz@nordakademie.de>
 * @version     1.0
 * @since       2014-11-08
 */
public class TimestampConverter extends StrutsTypeConverter {
    private static final SimpleDateFormat SDF = new SimpleDateFormat("dd.MM.yyyy HH:mm");

    /**
     * Converts from string.
     *
     * @param context
     * @param values
     * @param toClass
     * @return Timestamp of string
     * @throws TypeConversionException
     */
    @Override
    public Object convertFromString(Map context, String[] values, Class toClass) throws TypeConversionException {
        try {
            if (values == null || values.length == 0) {
                return null;
            }
            return new java.sql.Timestamp(SDF.parse(values[0]).getTime());
        } catch (Exception e) {
            throw new TypeConversionException(e);
        }
    }

    /**
     * Converts a timestamp to a string representation.
     *
     * @param context
     * @param object
     * @return String time representation
     * @throws TypeConversionException
     */
    @Override
    public String convertToString(Map context, Object object)  throws TypeConversionException  {
        try {
            if (object instanceof Timestamp) {
                return SDF.format(new Date(((Timestamp) object).getTime()));
            }
            return "";
        } catch(Exception e) {
            throw new TypeConversionException(e);
        }
    }

}
