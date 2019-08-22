/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fuelsupplychecker.model.Analyzers;

import fuelsupplychecker.model.Supply;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Bazowa klasa dla klas analizatorów
 * @author michi
 */
public class BaseRecordAnalyzer {
    
    public Integer getExcessCount() {
		return excessCount;
	}
	public Double getExcessPercentage() {
		return excessPercentage;
	}
	public Integer getErrorCount() {
		return errorCount;
	}
	public Double getErrorPercentage() {
		return errorPercentage;
	}

	protected ArrayList<Supply> supplies;
    protected Integer probLeakCount;
    protected Integer probDetectorErrorCount;
    protected Integer errorCount;
    protected Integer excessCount;
    protected Double propLeakPercentage;
    protected Double probDetectorErrorPercentage;
    protected Double errorPercentage;
    protected Double excessPercentage;
    
    // metody wirtualne, do przeładowania
    public void addSuppliesList(ArrayList<Supply> supplies) {}
    public void addSupply(Supply supply)
    {
        if (this.supplies != null)
        {
            this.supplies.add(supply);
        }
    }
    



    public String getMinMaxDate()
    {
    	Date startDate;
    	Date endDate;
    	startDate=endDate=supplies.get(0).getStartTime();

    		for(Supply entry:supplies)
    		{
    			if(entry.getStartTime().before(startDate))startDate=entry.getStartTime();
    			if(entry.getStartTime().after(endDate))endDate=entry.getStartTime();
    		}
    	
    		String pattern = "yy-MM-dd";
    		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

    		return simpleDateFormat.format(startDate) + " " + simpleDateFormat.format(endDate);
    }


    public void analyze()
    {
    	excessCount=errorCount=probLeakCount=probDetectorErrorCount=0;

    	for(Supply entry:supplies)
    	{
    		if(entry.isPossibleDetectorError() || entry.isPossibleLeak() || entry.getExcess())
    		{
    			errorCount++;
    		if(entry.isPossibleDetectorError())probDetectorErrorCount++;
    		else
    			{
    			if(entry.isPossibleLeak())probLeakCount++;
    			if(entry.getExcess())excessCount++;
    			}
    			
    		
    		}
    		
    	}
    	errorPercentage= errorCount/(double) supplies.size()*100;
    	propLeakPercentage=probLeakCount/(double)supplies.size()*100;
    	probDetectorErrorPercentage=probDetectorErrorCount/(double)supplies.size()*100;    
    	excessPercentage= excessCount/(double) supplies.size()*100;
    	
    }
    
    /**
     * Ilość wszystkich rekordów w analizie
     * @return 
     */
    public Integer getCount() {
        if (supplies != null)
            return supplies.size();
        else
            return 0;
    }

    /**
     * Ilość prawdopodobnych wycieków
     * @return 
     */
    public Integer getProbLeakCount() {
        return probLeakCount;
    }

    /**
     * Ilość prawdopodnych uszkodzeń czujników
     * @return 
     */
    public Integer getProbDetectorErrorCount() {
        return probDetectorErrorCount;
    }

    /**
     * Procentowa ilość prawdopodobnych wycieków
     * @return 
     */
    public Double getPropLeakPercentage() {
        return propLeakPercentage;
    }

    /**
     * Procentowa ilość prawdopodobnych błędów czujników
     * @return 
     */
    public Double getProbDetectorErrorPercentage() {
        return probDetectorErrorPercentage;
    }
    
    
    
    
}
