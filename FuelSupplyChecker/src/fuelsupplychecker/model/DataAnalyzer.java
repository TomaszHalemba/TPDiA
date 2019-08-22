/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fuelsupplychecker.model;

import fuelsupplychecker.main.ConfigManager;
import fuelsupplychecker.model.Analyzers.MainRecordAnalysisManager;
import java.util.ArrayList;


/**
 * Główna klasa analizująca dane
 * @author michi
 */
public class DataAnalyzer {
   
   



	/**
     * Obiekt wykonujący operacje filtrowania
     */
    private DataFiltering dataFilterManager;
    
    /**
     * Lista danych, na których odbywa się analiza
     */
    public ArrayList<Supply> suppliesList;
    
    



    /**
     * Konstruktor
     * @param suppliesList lista danych, na których odbywa się analiza
     */
    public DataAnalyzer(ArrayList<Supply> suppliesList)
    {
        this.suppliesList = suppliesList;
        this.dataFilterManager = new DataFiltering();
    }
    
    //w tej funkcji zapisuje dane do map
   
    /**
     * Usuwanie niewpełni wypełnionych rekordów
     */
    public void deleteIncompleteRecords()
    {
        this.dataFilterManager.deleteIncompleteRecords(this.suppliesList);
    }
    
    /**
     * usuwanie ekstremalnych wynik�w;
     */
    public void deleteExtremeValues()
    {
        this.dataFilterManager.deleteExtremeValues(this.suppliesList,ConfigManager.getExtremePercent());
    }
    
    /**
     * Obliczanie varów
     * @return 
     */
    public void calculateVars()
    {
        if (suppliesList == null || suppliesList.isEmpty())   
        {
        }
        else
        {
            // iteracja po liście i obliczanie varów dla każdego rekordu
            for (Supply supply : this.suppliesList)
            {
                supply.calculateVarBasedOnDetected();
                supply.calculateVarBasedOnDetectedNet();
                supply.calculateVarBasedOnHeight();
                supply.calculateVarBasedOnHeightNet();
            }

        }
    }
    
    /**
     * Ustawianie flag wycieków i errorów
     */
    private void setFlags()
    {
    	 for (Supply supply : this.suppliesList)
         {
    		 
    		 if(supply.getVarBasedOnDetected() > ConfigManager.getDetectedVarMaxTolerance() && 
                    supply.getVarBasedOnDetectedNet() > ConfigManager.getDetectedVarNetMaxTolerance())
    		 {
    			 supply.setPossibleLeak(true);
    		 }
    		 else supply.setPossibleLeak(false);
    		 
    		 if(supply.getVarBasedOnDetected() <-1*ConfigManager.getDetectedVarMaxTolerance() && 
                     supply.getVarBasedOnDetectedNet() < -1*ConfigManager.getDetectedVarNetMaxTolerance())
     		 {
     			 supply.setExcess(true);
     		 }
     		 else supply.setExcess(false);
                 

                 Double diff = Math.abs(supply.getVarBasedOnHeight() - supply.getVarBasedOnDetected());
                 Double diffNet = Math.abs(supply.getVarBasedOnHeightNet() - supply.getVarBasedOnDetectedNet());
                 
                 if (diff > ConfigManager.getDetectedAndHeightMaxDiff() && diffNet > ConfigManager.getDetectedAndHeightNetMaxDiff())
                 {
                     supply.setPossibleDetectorError(true);
                 }
                 else supply.setPossibleDetectorError(false);
                 
         }
    }
    

    private MainRecordAnalysisManager analysisManager;
    public MainRecordAnalysisManager getAnalysisManager() {
		return analysisManager;
	}

	/**
     * Główna metoda analizująca
     */
    public void makeAnalysis(int interval)
    {

    	setFlags();
        analysisManager = new MainRecordAnalysisManager(this.suppliesList);

        analysisManager.createTerminalAnalysis(interval);
        analysisManager.createTankAnalysis(interval);
        analysisManager.createDriverAnalysis(interval);
        analysisManager.createStationAnalysis(interval);
        analysisManager.createTruckAnalysis(interval);
        
        analysisManager.setArrays();


    }
    

    
    
}


