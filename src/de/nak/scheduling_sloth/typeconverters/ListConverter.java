package de.nak.scheduling_sloth.typeconverters;

import com.opensymphony.xwork2.conversion.TypeConversionException;
import de.nak.scheduling_sloth.model.Room;
import org.apache.struts2.util.StrutsTypeConverter;

import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

/**
 * List converter for rooms, etc.
 *
 * @author      Kevin Scholz <kevin.scholz@nordakademie.de>
 * @version     1.0
 * @since       2014-11-08
 */
public class ListConverter extends StrutsTypeConverter {

    /**
     * Converts from string.
     *
     * @param context
     * @param values
     * @param toClass
     * @return Object null
     * @throws TypeConversionException
     */
    @Override
    public Object convertFromString(Map context, String[] values, Class toClass) throws TypeConversionException {
        try {
            return null;
        } catch (Exception e){
            throw new TypeConversionException(e);
        }
    }

    /**
     * Converts a list to a string representation.
     *
     * @param context
     * @param object
     * @return String list representation
     * @throws TypeConversionException
     */
    @Override
    public String convertToString(Map context, Object object) throws TypeConversionException  {
        try {
            String result = "-";

            if (object instanceof List) {
                List list = (List) object;

                StringJoiner rooms = new StringJoiner(", ");

                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i) instanceof Room) {
                        rooms.add(((Room) list.get(i)).getName());
                    }
                }

                if (rooms.length() > 0) {
                    result = rooms.toString();
                }
            }
            return result;
        } catch (Exception e) {
            throw new TypeConversionException(e);
        }
    }

}
