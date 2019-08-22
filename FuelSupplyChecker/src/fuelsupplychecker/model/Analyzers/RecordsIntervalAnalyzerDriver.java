/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fuelsupplychecker.model.Analyzers;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;

import fuelsupplychecker.model.Supply;

/**
 *
 * @author michi
 */
public class RecordsIntervalAnalyzerDriver {
    
    private ArrayList<DriverAnalyzer> recordsIntervalAnalysis; 
    private Date startDate;
    private Date endDate;
    
    
    public String getFolderName()
    {
    	String pattern = "yyyy-MM-dd";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

		return simpleDateFormat.format(startDate) + " " + simpleDateFormat.format(endDate);
    }

    private void setData()
    {
    	startDate=endDate=recordsIntervalAnalysis.get(0).supplies.get(0).getStartTime();
    	for(DriverAnalyzer column:recordsIntervalAnalysis)
    	{
    		for(Supply entry:column.supplies)
    		{
    			if(entry.getStartTime().before(startDate))startDate=entry.getStartTime();
    			if(entry.getStartTime().after(endDate))endDate=entry.getStartTime();
    		}
    	}
    	
    }

    public RecordsIntervalAnalyzerDriver()
    {
        
    }
    
    public RecordsIntervalAnalyzerDriver(ArrayList<BaseRecordAnalyzer> recordsIntervalAnalysis) {
        
        this.recordsIntervalAnalysis = new ArrayList<>();
        
        for (BaseRecordAnalyzer rec : recordsIntervalAnalysis)
        {
            this.recordsIntervalAnalysis.add((DriverAnalyzer)rec);
        }
    }

    
    public ArrayList<DriverAnalyzer> getRecordsIntervalAnalysis() {
        return recordsIntervalAnalysis;
    }

    public void setRecordsIntervalAnalysis(ArrayList<DriverAnalyzer> recordsIntervalAnalysis) {
        this.recordsIntervalAnalysis = recordsIntervalAnalysis;
    }
    

    
    public void sortByErrorPercentage()
    {

    	this.recordsIntervalAnalysis.sort(new Comparator<BaseRecordAnalyzer>() {
			@Override
			public int compare(BaseRecordAnalyzer sup1, BaseRecordAnalyzer sup2) {
				if(!sup1.getErrorPercentage().equals(sup2.getErrorPercentage()))
				{
					return (int) Math.floor(sup2.getErrorPercentage()-sup1.getErrorPercentage());
				}
				else
					if (!sup2.getErrorCount().equals(sup1.getErrorCount()))
							{
								return sup2.getErrorCount()-sup1.getErrorCount();
							}
					else 
						return (int) Math.floor(sup2.getProbDetectorErrorPercentage()-sup1.getProbDetectorErrorPercentage());
					
			}
		});
    	
    }
    
    /**
     * Analiza całej listy
     */
    public void analyzeAll()
    {
        for (BaseRecordAnalyzer analyzer : this.recordsIntervalAnalysis)
        {
            analyzer.analyze();
        }
        setData();
        sortByErrorPercentage();
    }
    
    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }
    
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
