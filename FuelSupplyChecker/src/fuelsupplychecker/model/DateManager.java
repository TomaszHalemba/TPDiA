package fuelsupplychecker.model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class DateManager {

	public ArrayList<ArrayList<Supply>> splitToLists(ArrayList<Supply> list, Integer interval) {
		ArrayList<ArrayList<Supply>> returnList = new ArrayList<ArrayList<Supply>>();

		ArrayList<Supply> tmp = new ArrayList<Supply>();

		Date lastDate = this.getMinDate(list);


		int listSize = list.size();
		int arraySize = 1;
		Calendar c = Calendar.getInstance();
		c.setTime(lastDate);

		c.add(Calendar.DATE, interval);

		while (listSize >= arraySize) {
			for (Supply entry : list) {
				if (c.getTime().getTime()> entry.getStartTime().getTime() && entry.getStartTime().getTime()>=lastDate.getTime()) {
					tmp.add(entry);
					arraySize++;
				}

			}
			if (!tmp.isEmpty()) {
				lastDate = getMaxDate(tmp);
				returnList.add(tmp);
				c.setTime(lastDate);
				c.add(Calendar.DATE, interval);
				tmp = new ArrayList<Supply>();
			}

		}


		return returnList;
	}

	private Date getMaxDate(ArrayList<Supply> list) {
		Date max = list.get(0).getStartTime();

		for (Supply entry : list) {
			if (entry.getStartTime().after(max))
				max = entry.getStartTime();
		}

		return max;
	}

	private Date getMinDate(ArrayList<Supply> list) {
		Date min = list.get(0).getStartTime();

		for (Supply entry : list) {
			if (entry.getStartTime().before(min))
				min = entry.getStartTime();
		}

		return min;
	}

	public String getTime(ArrayList<Supply> list) {
		String pattern = "yyyy-MM-dd";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

		return simpleDateFormat.format(this.getMinDate(list)) + " " + simpleDateFormat.format(this.getMaxDate(list));
	}

}