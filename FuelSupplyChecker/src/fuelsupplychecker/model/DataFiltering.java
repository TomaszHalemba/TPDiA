package fuelsupplychecker.model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

public class DataFiltering {


	/**
	 * Metoda usuwająca niekompletne rekordy.
	 * 
	 * @param list
	 */
	public void deleteIncompleteRecords(ArrayList<Supply> list) {
		for (Iterator<Supply> iter = list.listIterator(); iter.hasNext();) {
			Supply a = iter.next();
			if ((a.getTankEndHeight() == null || a.getTankStartHeight() == null || a.getDeclaredSupplyCapacity() == null
					|| a.getDeclaredSupplyCapacityNet() == null || a.getDetectedSupplyCapacity() == null
					|| a.getDetectedSupplyCapacityNet() == null || a.getDriverId() == null || a.getEndTime() == null
					|| a.getFuelType() == null || a.getId() == null || a.getStartTime() == null
					|| a.getStationId() == null || a.getTankCapacity() == null || a.getTankId() == null
					|| a.getTankCapacity() == null || a.getTruckId() == null || a.getTruckId()==0 || a.getDriverId()==0 || a.getTerminalId()==0 || a.getDeclaredSupplyCapacityNet()==0)) {
				iter.remove();
			}

		}
	}

	// usuwa jaki� procent najbardziej ekstremalnych pomiar�w
	public void deleteExtremeValues(ArrayList<Supply> list,double extremePercent) {
		int percent = (int) Math.floor(list.size() *(double)(extremePercent/400.0));
		// sortowanie po getVarBasedOnDetected
		list.sort(new Comparator<Supply>() {
			@Override
			public int compare(Supply sup1, Supply sup2) {
				return (int) (Math.abs(sup2.getVarBasedOnDetected()) - Math.abs(sup1.getVarBasedOnDetected()));
			}
		});

		for (int a = 0; a < percent; a++) {
			list.remove(a);

		}

		list.sort(new Comparator<Supply>() {
			@Override
			public int compare(Supply sup1, Supply sup2) {
				return (int) (Math.abs(sup2.getVarBasedOnDetectedNet()) - Math.abs(sup1.getVarBasedOnDetectedNet()));

			}
		});

		for (int a = 0; a < percent; a++) {
			list.remove(a);
		}

		list.sort(new Comparator<Supply>() {
			@Override
			public int compare(Supply sup1, Supply sup2) {
				return (int) (Math.abs(sup2.getVarBasedOnHeightNet()) - Math.abs(sup1.getVarBasedOnHeightNet()));
			}
		});

		for (int a = 0; a < percent; a++) {
			list.remove(a);
		}

		list.sort(new Comparator<Supply>() {
			@Override
			public int compare(Supply sup1, Supply sup2) {
				return (int) (Math.abs(sup2.getVarBasedOnHeight()) - Math.abs(sup1.getVarBasedOnHeight()));
			}
		});

		for (int a = 0; a < percent; a++) {
			list.remove(a);
		}

	}

}
