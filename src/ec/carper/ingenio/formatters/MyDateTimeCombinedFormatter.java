package ec.carper.ingenio.formatters;

import java.text.*;

import javax.servlet.http.*;

import org.openxava.formatters.*;
import org.openxava.util.*;


/**
 * Date/Time (combined) formatter with multilocale support. <p>
 *
 * @author Peter Smith
 * @author Javier Paniza
 * @author carper
 */

public class MyDateTimeCombinedFormatter implements IFormatter {

	private static DateFormat extendedDateTimeFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
	
	public String format(HttpServletRequest request, Object date) {
		if (date == null) return "";
		if (Dates.getYear((java.util.Date)date) < 2) return "";
		return extendedDateTimeFormat.format(date);
	}

    public Object parse(HttpServletRequest request, String string) throws ParseException {
		if (Is.emptyString(string)) return null;
		if (string.indexOf('-') >= 0) { // SimpleDateFormat does not work well with -
			string = Strings.change(string, "-", "/");
		}
		
        try {
            //SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            java.util.Date result = extendedDateTimeFormat.parse(string);
            return new java.sql.Timestamp( result.getTime() );
        }
        catch (ParseException ex) { }

		throw new ParseException(XavaResources.getString("bad_date_format",string),-1);
    }

}

