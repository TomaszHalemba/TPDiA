
package fuelsupplychecker.main;

import fuelsupplychecker.model.DataAnalyzer;
import fuelsupplychecker.model.FileManager;
import fuelsupplychecker.model.Supply;
import java.util.ArrayList;

/**
 *
 * @author michi
 */
public class Controller {
    
    private FileManager fileManager;
    
    private DataAnalyzer dataAnalyzer;

    /**
     * Constructor
     * 
     */
    public Controller() {
        this.fileManager = new FileManager();
    }
    

	public void execute() {

		// zaczytanie pliku configa
		System.out.println("Rozpoczęcie wczytywanie pliku konfiguracyjnego");
		ConfigManager.readConfigFile("config.xml");
		System.out.println("Zakończenie wczytywanie pliku konfiguracyjnego");
		System.out.println("Rozpoczęcie wczytywanie danych");
		ArrayList<Supply> list = fileManager.getSuppliesFromCsv(ConfigManager.getCsvFilePath());
		System.out.println("Zakończenie wczytywanie danych");
		String outputPath=ConfigManager.getOutputFilePath();

		this.dataAnalyzer = new DataAnalyzer(list);
		System.out.println("Rozpoczęcie usuwania niepełnych rekordów");
		this.dataAnalyzer.deleteIncompleteRecords();
		System.out.println("Zakończenie usuwania niepełnych rekordów");
		System.out.println("Rozpoczęcie wyliczania wartości pomiarowych (różnic)");
		this.dataAnalyzer.calculateVars();
		System.out.println("Zakończenie wyliczania wartości pomiarowych (różnic)");
		System.out.println("Rozpoczęcie usuwania rekordów z ekstremalnymi wartościami pomiarowymi (różnicami)");
		this.dataAnalyzer.deleteExtremeValues();
		System.out.println("Zakończenie usuwania rekordów z ekstremalnymi wartościami pomiarowymi (różnicami)");
		System.out.println("Rozpoczęcie analizy");
		this.dataAnalyzer.makeAnalysis(ConfigManager.getDayInterval());
		System.out.println("Zakończenie analizy");
		System.out.println("Rozpoczęcie Wypisywania danych (tworzenia raportu)");
		this.fileManager.printRecordsIntervalAnalyzerTank(
				this.dataAnalyzer.getAnalysisManager().getTankIntervalAnalysisList(), outputPath+"Zbiornik\\");
		this.fileManager.printRecordsIntervalAnalyzerDriver(
				this.dataAnalyzer.getAnalysisManager().getDriverIntervalAnalysisList(), outputPath+"Kierowca\\");
		this.fileManager.printRecordsIntervalAnalyzerStation(
				this.dataAnalyzer.getAnalysisManager().getStationIntervalAnalysisList(), outputPath+"Stacja\\");
		this.fileManager.printRecordsIntervalAnalyzerTerminal(
				this.dataAnalyzer.getAnalysisManager().getTerminalIntervalAnalysisList(), outputPath+"Terminal\\");
		this.fileManager.printRecordsIntervalAnalyzerTruck(
				this.dataAnalyzer.getAnalysisManager().getTruckIntervalAnalysisList(), outputPath+"Cysterna\\");
		
		this.fileManager.printTankTop(this.dataAnalyzer.getAnalysisManager().getTankTop(), outputPath+"Zbiornik.txt");
		this.fileManager.printDriverTop(this.dataAnalyzer.getAnalysisManager().getDriverTop(), outputPath+"kierowca.txt");
		this.fileManager.printStationTop(this.dataAnalyzer.getAnalysisManager().getStationTop(), outputPath+"Stacja.txt");
		this.fileManager.printTerminalTop(this.dataAnalyzer.getAnalysisManager().getTerminalTop(), outputPath+"Terminal.txt");
		this.fileManager.printTruckTop(this.dataAnalyzer.getAnalysisManager().getTruckTop(), outputPath+"Cysterna.txt");
		System.out.println("Zakończenie Wypisywania danych");
	}

}
    


/**
 * Enum representing error types.
 * @author Michał Podlaski
 */
enum ErrorType {
        WRONG_INPUT, WRONG_OPTION, TOO_SHORT_STRING  
    }
