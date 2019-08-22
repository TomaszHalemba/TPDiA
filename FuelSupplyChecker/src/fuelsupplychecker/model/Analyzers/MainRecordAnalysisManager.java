/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fuelsupplychecker.model.Analyzers;

import fuelsupplychecker.model.DateManager;
import fuelsupplychecker.model.Supply;
import java.util.ArrayList;
import java.util.Comparator;

/**
 *
 * @author michi
 */
public class MainRecordAnalysisManager {
    
    // lista wszystkich dostaw używanych w analizie
    private ArrayList<Supply> suppliesList;
    
      int arraySize=10;
    
    private ArrayList<TankAnalyzer>tankTop =new ArrayList<TankAnalyzer>() ;
    private ArrayList<DriverAnalyzer>driverTop =new ArrayList<DriverAnalyzer>() ;
	private ArrayList<StationAnalyzer>stationTop =new ArrayList<StationAnalyzer>() ;
    private ArrayList<TerminalAnalyzer>terminalTop =new ArrayList<TerminalAnalyzer>() ;
    private ArrayList<TruckAnalyzer>truckTop =new ArrayList<TruckAnalyzer>() ;
    


	// listy analiz dla każdego przedziału czasu
    private ArrayList<RecordsIntervalAnalyzerTank> tankIntervalAnalysisList;
    private ArrayList<RecordsIntervalAnalyzerTruck> truckIntervalAnalysisList;
    private ArrayList<RecordsIntervalAnalyzerDriver> driverIntervalAnalysisList;
    private ArrayList<RecordsIntervalAnalyzerTerminal> terminalIntervalAnalysisList;
    private ArrayList<RecordsIntervalAnalyzerStation> stationIntervalAnalysisList;
    
    private DateManager dateManager;
    

   public class ComparatorClass implements Comparator<BaseRecordAnalyzer> {
		@Override
		public int compare(BaseRecordAnalyzer sup1, BaseRecordAnalyzer sup2) {
			if (!sup1.getErrorPercentage().equals(sup2.getErrorPercentage())) {
				return (int) Math.floor(sup2.getErrorPercentage() - sup1.getErrorPercentage());
			} else if (!sup2.getErrorCount().equals(sup1.getErrorCount())) {
				return sup2.getErrorCount() - sup1.getErrorCount();
			} else
				return (int) Math.floor(sup2.getProbDetectorErrorPercentage() - sup1.getProbDetectorErrorPercentage());

		}
	}
   
   /*
    * ustawienie tabel top najbardziej błędnych obiektów
    */
	public void setArrays() {

		ComparatorClass comparatorClass = new ComparatorClass();
		for (RecordsIntervalAnalyzerTank analzyer : tankIntervalAnalysisList) {			//tank
			for (TankAnalyzer entry : analzyer.getRecordsIntervalAnalysis()) {
				tankTop.add(entry);
			}
		}
		tankTop.sort(comparatorClass);
		
		for (RecordsIntervalAnalyzerTruck analzyer : truckIntervalAnalysisList) {		//truck
			for (TruckAnalyzer entry : analzyer.getRecordsIntervalAnalysis()) {
				truckTop.add(entry);
			}
		}
		truckTop.sort(comparatorClass);
		
		for (RecordsIntervalAnalyzerDriver analzyer : driverIntervalAnalysisList) {		//driver
			for (DriverAnalyzer entry : analzyer.getRecordsIntervalAnalysis()) {
				driverTop.add(entry);
			}
		}
		driverTop.sort(comparatorClass);
		
		for (RecordsIntervalAnalyzerTerminal analzyer : terminalIntervalAnalysisList) {		//driver
			for (TerminalAnalyzer entry : analzyer.getRecordsIntervalAnalysis()) {
				terminalTop.add(entry);
			}
		}
		terminalTop.sort(comparatorClass);
		
		for (RecordsIntervalAnalyzerStation analzyer : stationIntervalAnalysisList) {		//station
			for (StationAnalyzer entry : analzyer.getRecordsIntervalAnalysis()) {
				stationTop.add(entry);
			}
		}
		stationTop.sort(comparatorClass);
		
	}
    
    
    public MainRecordAnalysisManager(ArrayList<Supply> suppliesList)
    {
        this.suppliesList = suppliesList;
        //this.dayInterval = dayInterval;
        this.dateManager = new DateManager();
    }

    
    // GETTERY do używania w klasie wyżej
    public ArrayList<RecordsIntervalAnalyzerTank> getTankIntervalAnalysisList() {
        return tankIntervalAnalysisList;
    }

    public ArrayList<RecordsIntervalAnalyzerTruck> getTruckIntervalAnalysisList() {
        return truckIntervalAnalysisList;
    }

    public ArrayList<RecordsIntervalAnalyzerDriver> getDriverIntervalAnalysisList() {
        return driverIntervalAnalysisList;
    }

    public ArrayList<RecordsIntervalAnalyzerTerminal> getTerminalIntervalAnalysisList() {
        return terminalIntervalAnalysisList;
    }

    public ArrayList<RecordsIntervalAnalyzerStation> getStationIntervalAnalysisList() {
        return stationIntervalAnalysisList;
    }
    
    /**
     * Stworzenie analizy danych dla zbiorników
     * @param dayInterval 
     */
    public void createTankAnalysis(Integer dayInterval)
    {
        this.tankIntervalAnalysisList = new ArrayList<>();
        this.createAnalysis(TankAnalyzer.class, dayInterval);
        
        for (RecordsIntervalAnalyzerTank intervalAnalyzer : tankIntervalAnalysisList)
        {
            intervalAnalyzer.analyzeAll();
        }
       
    }
    
    /**
     * Stworzenie analizy danych dla kierowców
     * @param dayInterval 
     */
    public void createDriverAnalysis(Integer dayInterval)
    {
        this.driverIntervalAnalysisList = new ArrayList<>();
        this.createAnalysis(DriverAnalyzer.class, dayInterval);
        
        for (RecordsIntervalAnalyzerDriver intervalAnalyzer : driverIntervalAnalysisList)
        {
            intervalAnalyzer.analyzeAll();
        }
    }
    
    /**
     * Stworzenie analizy danych dla stacji
     * @param dayInterval 
     */
    public void createStationAnalysis(Integer dayInterval)
    {
        this.stationIntervalAnalysisList = new ArrayList<>();
        this.createAnalysis(StationAnalyzer.class, dayInterval);
        
        for (RecordsIntervalAnalyzerStation intervalAnalyzer : stationIntervalAnalysisList)
        {
            intervalAnalyzer.analyzeAll();
        }
    }
    
    /**
     * Stworzenie analizy danych dla terminali
     * @param dayInterval 
     */
    public void createTerminalAnalysis(Integer dayInterval)
    {
        this.terminalIntervalAnalysisList = new ArrayList<>();
        this.createAnalysis(TerminalAnalyzer.class, dayInterval);
        
        for (RecordsIntervalAnalyzerTerminal intervalAnalyzer : terminalIntervalAnalysisList)
        {
            intervalAnalyzer.analyzeAll();
        }
    }
    
    /**
     * Stworzenie analizy danych dla ciężarówek
     * @param dayInterval 
     */
    public void createTruckAnalysis(Integer dayInterval)
    {
        this.truckIntervalAnalysisList = new ArrayList<>();
        this.createAnalysis(TruckAnalyzer.class, dayInterval);
        
        for (RecordsIntervalAnalyzerTruck intervalAnalyzer : truckIntervalAnalysisList)
        {
            intervalAnalyzer.analyzeAll();
        }
    }
    
    
    /**
     * Tworzenie analiz w zależności od typu analizatora i dni interwału
     * @param typeOfAnalyzer
     * @param dayInterval 
     */
    private void createAnalysis(Class<?> typeOfAnalyzer, Integer dayInterval)
    {
        
        if (this.suppliesList.size() > 0)
        {
            // tutaj jest rozdzielanie wszystkich suplajów na przedzialy czasowe
            ArrayList<ArrayList<Supply>> splitSupplies  = this.dateManager.splitToLists(suppliesList, dayInterval);
            

            for (ArrayList<Supply> suppliesList : splitSupplies)
            {
                if (suppliesList.size() > 0)
                {
                    ArrayList<BaseRecordAnalyzer> analysisList = new ArrayList<>();

                    for (Supply supply : suppliesList)
                    {
                        if (!checkContaining(analysisList, supply))
                        {
                            BaseRecordAnalyzer analyzer;
                            
                            // tworzenie obiektów w zależności od typów
                            if (typeOfAnalyzer == TankAnalyzer.class)
                            {
                                analyzer = new TankAnalyzer(supply);
                            }
                            else if (typeOfAnalyzer == DriverAnalyzer.class)
                            {
                                analyzer = new DriverAnalyzer(supply);
                            }
                            else if (typeOfAnalyzer == StationAnalyzer.class)
                            {
                                analyzer = new StationAnalyzer(supply);
                            }
                            else if (typeOfAnalyzer == TerminalAnalyzer.class)
                            {
                                analyzer = new TerminalAnalyzer(supply);
                            }
                            else if (typeOfAnalyzer == TruckAnalyzer.class)
                            {
                                analyzer = new TruckAnalyzer(supply);
                            }
                            else
                            {
                                analyzer = new BaseRecordAnalyzer();
                            }
                            
                           
                            analysisList.add(analyzer);

                        }
                        else
                        {
                            this.addSupplyToAnalysisList(analysisList, supply);
                            
                        }
                    }
                    
                    this.addAnalysisToIntervalList(analysisList);
                }
            }
        
        
        }
    }
    
    /**
     * Metoda sprawdzająca czy lista Analyzerów zawiera już obiekt dany w dostawie
     * @param tanks
     * @param supply
     * @return 
     */
    private Boolean checkContaining(ArrayList<BaseRecordAnalyzer> records, Supply supply)
    {
        //
        for (BaseRecordAnalyzer rec : records)
            {
                // porównywanie tankAnalyzera
                if (rec instanceof TankAnalyzer)
                {
                    TankAnalyzer tank = (TankAnalyzer)rec; // rzutowanie
                    
                    if (supply.getTankId() == tank.getTankId() &&
                        supply.getStationId() == tank.getStationId())
                    {
                        return true;
                    }
                }
                else if (rec instanceof DriverAnalyzer)
                {
                    DriverAnalyzer driver = (DriverAnalyzer)rec; // rzutowanie
                    if (supply.getDriverId().equals(driver.getDriverId()))
                    {
                        return true;
                    }
                }
                else if (rec instanceof TerminalAnalyzer)
                {
                    TerminalAnalyzer terminal = (TerminalAnalyzer)rec; // rzutowanie
                    
                    if (supply.getTerminalId() == terminal.getTerminalId())
                    {
                        return true;
                    }
                }
                else if (rec instanceof StationAnalyzer)
                {
                	StationAnalyzer terminal = (StationAnalyzer)rec; // rzutowanie
                    
                    if (supply.getStationId() == terminal.getStationId())
                    {
                        return true;
                    }
                }
                else if (rec instanceof TruckAnalyzer)
                {
                	TruckAnalyzer terminal = (TruckAnalyzer)rec; // rzutowanie
                    
                    if (supply.getTruckId() == terminal.getTruckId())
                    {
                        return true;
                    }
                }

                
            }
        return false;
    }
    
    // dodanie dostawy do listy RecordAnalyzerów 
    private void addSupplyToAnalysisList(ArrayList<BaseRecordAnalyzer> records, Supply supply)
    {
        for (BaseRecordAnalyzer rec : records)
        {
            // dodawanie do tankAnalyzera
            if (rec instanceof TankAnalyzer)
            {
                TankAnalyzer tank = (TankAnalyzer)rec;
                if (supply.getTankId() == tank.getTankId() &&
                    supply.getStationId() == tank.getStationId())
                {
                    rec.addSupply(supply);
                }
            }
            else if (rec instanceof DriverAnalyzer)
            {
            	DriverAnalyzer driver = (DriverAnalyzer)rec;
                if (supply.getDriverId() == driver.getDriverId())
                {
                    rec.addSupply(supply);
                }
            }
            else if (rec instanceof StationAnalyzer)
            {
            	StationAnalyzer station = (StationAnalyzer)rec;
                if (supply.getStationId() == station.getStationId())
                {
                    rec.addSupply(supply);
                }
            }
            else if (rec instanceof TerminalAnalyzer)
            {
            	TerminalAnalyzer terminal = (TerminalAnalyzer)rec;
                if (supply.getTerminalId() == terminal.getTerminalId())
                {
                    rec.addSupply(supply);
                }
            }
            else if (rec instanceof TruckAnalyzer)
            {
            	TruckAnalyzer truck = (TruckAnalyzer)rec;
                if (supply.getTruckId() == truck.getTruckId())
                {
                    rec.addSupply(supply);
                }
            }
            

            
        }
    }
    
    /**
     * Dodawanie utworzonej listy Analyzerów z danego przedziału czasu do Listy interwałów czasowych
     * @param analysisList 
     */
    private void addAnalysisToIntervalList(ArrayList<BaseRecordAnalyzer> analysisList)
    {
        if (analysisList.size() > 0)
        {

            
            
            BaseRecordAnalyzer rec = analysisList.get(0);
            
            if (rec instanceof TankAnalyzer)
            {
                RecordsIntervalAnalyzerTank intervalAnalyzer = new RecordsIntervalAnalyzerTank(analysisList);
                this.tankIntervalAnalysisList.add(intervalAnalyzer);
            }
            else if (rec instanceof DriverAnalyzer)
            {
                RecordsIntervalAnalyzerDriver intervalAnalyzer = new RecordsIntervalAnalyzerDriver(analysisList);
            	this.driverIntervalAnalysisList.add(intervalAnalyzer);
            }
            else if (rec instanceof StationAnalyzer)
            {
                RecordsIntervalAnalyzerStation intervalAnalyzer = new RecordsIntervalAnalyzerStation(analysisList);
            	this.stationIntervalAnalysisList.add(intervalAnalyzer);
            }
            else if (rec instanceof TerminalAnalyzer)
            {
                RecordsIntervalAnalyzerTerminal intervalAnalyzer = new RecordsIntervalAnalyzerTerminal(analysisList);
            	this.terminalIntervalAnalysisList.add(intervalAnalyzer);
            }
            else if (rec instanceof TruckAnalyzer)
            {
                RecordsIntervalAnalyzerTruck intervalAnalyzer = new RecordsIntervalAnalyzerTruck(analysisList);
            	this.truckIntervalAnalysisList.add(intervalAnalyzer);
            }
        }
        else
        {
            //nic
        }
    
    }
    public ArrayList<DriverAnalyzer> getDriverTop() {
		return driverTop;
	}


	public ArrayList<StationAnalyzer> getStationTop() {
		return stationTop;
	}


	public ArrayList<TerminalAnalyzer> getTerminalTop() {
		return terminalTop;
	}


	public ArrayList<TruckAnalyzer> getTruckTop() {
		return truckTop;
	}
    
    public ArrayList<TankAnalyzer> getTankTop() {
		return tankTop;
	}
    
}

