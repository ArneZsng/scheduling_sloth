package de.nak.scheduling_sloth.typeconverters;

import com.opensymphony.xwork2.conversion.TypeConversionException;
import de.nak.scheduling_sloth.model.Room;
import org.apache.struts2.util.StrutsTypeConverter;

import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

/**
 * Created by kevinscholz on 08/11/14.
 */

public class ListConverter extends StrutsTypeConverter {

    @Override
    public Object convertFromString(Map context, String[] values, Class toClass) throws TypeConversionException {
        try{
            return null;
        }catch(Exception e){
            throw new TypeConversionException(e);
        }
    }

    @Override
    public String convertToString(Map context, Object object)  throws TypeConversionException  {
        try{
            String result = "-";
            if(object instanceof List && ((List) object).size() > 0 && ((List) object).get(0) instanceof Room){
                StringJoiner rooms = new StringJoiner(", ");
                for(Room room: ((List<Room>) object)) {
                    rooms.add(room.getName());
                }
                result = rooms.toString();
            }
            return result;
        }catch(Exception e){
            throw new TypeConversionException(e);
        }
    }

}
