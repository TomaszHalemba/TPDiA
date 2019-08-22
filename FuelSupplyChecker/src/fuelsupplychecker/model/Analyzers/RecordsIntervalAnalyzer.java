/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fuelsupplychecker.model.Analyzers;

import java.util.ArrayList;
import java.util.Comparator;

/**
 *
 * @author michi
 */
public class RecordsIntervalAnalyzer {

    public RecordsIntervalAnalyzer(ArrayList<BaseRecordAnalyzer> recordsIntervalAnalysis) {
        this.recordsIntervalAnalysis = recordsIntervalAnalysis;
    }
    

    public void sortByLeakPercentage()
    {

    	recordsIntervalAnalysis.sort(new Comparator<BaseRecordAnalyzer>() {
			@Override
			public int compare(BaseRecordAnalyzer sup1, BaseRecordAnalyzer sup2) {
				return (int) (sup1.getPropLeakPercentage()-sup2.getPropLeakPercentage());
			}
		});
    	
    }
    
    public void sortByDetectorPercentage()
    {

    	recordsIntervalAnalysis.sort(new Comparator<BaseRecordAnalyzer>() {
			@Override
			public int compare(BaseRecordAnalyzer sup1, BaseRecordAnalyzer sup2) {
				return (int) (sup1.getProbDetectorErrorPercentage()-sup2.getProbDetectorErrorPercentage());
			}
		});
    	
    }
    
    private ArrayList<BaseRecordAnalyzer> recordsIntervalAnalysis;

	public ArrayList<BaseRecordAnalyzer> getRecordsIntervalAnalysis() {
		return recordsIntervalAnalysis;
	}

	public void setRecordsIntervalAnalysis(ArrayList<BaseRecordAnalyzer> recordsIntervalAnalysis) {
		this.recordsIntervalAnalysis = recordsIntervalAnalysis;
	} 
	
	
    
}
