package herramientas;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Operaciones {

	public static List<Date> GetRangeOfDates(String firstDateSt, String secondDateSt) {
		List<Date> result = new ArrayList<Date>();
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date firstDate = null;
		try {
			firstDate = formatter.parse(firstDateSt);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Date secondDate = null;
		try {
			secondDate = formatter.parse(secondDateSt);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Date left;
		Date right;

		// Compare the dates to see which is less
		if (firstDate.compareTo(secondDate) < 0) {
			left = firstDate;
			right = secondDate;
		}
		else {
			right = firstDate;
			left = secondDate;
		}

		cal.clear();
		cal.setTime(left);  // Seed the calendar with the starting date

		for (; left.compareTo(right) <= 0; ) {
			result.add(left);           // Add the date iterator value to the result set
			cal.add(Calendar.DATE, 1);  // Add one day
			left = cal.getTime();       // Update the date iterator to the new date
		}

		return result;
	}
}
