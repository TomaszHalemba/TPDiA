/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fuelsupplychecker.main;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.DOMException;
import org.xml.sax.SAXException;

/**
 * Klasa czytajaca plik konfiguracyjny i wystawiająca globalne wlasciwosci na
 * jego podstawie
 *
 * @author michi
 */
public final class ConfigManager {
    
    private static String csvFilePath;                  //sciezka do pliku csv
    private static Double detectedVarMaxTolerance;      //maksymalna tolerancja dopuszczająca brak wycieku dla varDetected
    private static Double detectedVarNetMaxTolerance;   //maksymalna tolerancja dopuszczająca brak wycieku dla varDetectedNet
    private static Double detectedAndHeightMaxDiff;     //maksymalna dopusczalna roznica pomiedzy varem detected a height
    private static Double detectedAndHeightNetMaxDiff;  //maksymalna dopusczalna roznica pomiedzy varem detected a height (Net)
    private static String outputFilePath;				//scieżka folderu do którego mają trafiać wyniki
    private static Double extremePercent;				//procent rekordów usuwanych z ekstremalnymi pomiarami
    private static int dayInterval;						//interwał dni




	// priwatny kontruktor, żeby nie dało się utworzyć instancji klasy
    private ConfigManager() { }
    
    /**
     * Czytanie pliku konfiguracyjnego i zapisywanie wlasciwosci
     *
     * @param path
     */
    public static void readConfigFile(String path) {
        
    	try
    	{
        try 
        {
            File fXmlFile = new File(path);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);
            doc.getDocumentElement().normalize();


            
            // pobieranie poszczególnych wartości z pliku configa
            csvFilePath = doc.getElementsByTagName("csvFilePath").item(0).getTextContent();
            detectedVarMaxTolerance = Double.parseDouble(doc.getElementsByTagName("detectedSupplyTolerance").item(0).getTextContent());
            detectedVarNetMaxTolerance = Double.parseDouble(doc.getElementsByTagName("detectedSupplyNetTolerance").item(0).getTextContent());
            detectedAndHeightMaxDiff = Double.parseDouble(doc.getElementsByTagName("detectedHeightMaxDiff").item(0).getTextContent());
            detectedAndHeightNetMaxDiff = Double.parseDouble(doc.getElementsByTagName("detectedNetHeightMaxDiff").item(0).getTextContent());
            outputFilePath = doc.getElementsByTagName("outputFilePath").item(0).getTextContent();
            extremePercent = Double.parseDouble(doc.getElementsByTagName("extremePercent").item(0).getTextContent());
            dayInterval = Integer.parseInt(doc.getElementsByTagName("dayInterval").item(0).getTextContent());
            if(extremePercent<0 || extremePercent>80.0) extremePercent=5.0;//jak ktoś wpisze za dużą wartość
            if(dayInterval<5 || dayInterval>5000) dayInterval=30;//jak ktoś wpisze za dużą wartość
            if(detectedVarMaxTolerance<0)detectedVarMaxTolerance=200.0;
            if(detectedVarNetMaxTolerance<0)detectedVarNetMaxTolerance=200.0;
            if(detectedAndHeightMaxDiff<0)detectedAndHeightMaxDiff=200.0;
            if(detectedAndHeightNetMaxDiff<0)detectedAndHeightNetMaxDiff=200.0;
            Path path1 =Paths.get(outputFilePath);

            if (!Files.exists(path1)) {
            	System.out.println("Miejsce pola wyjściowego nie istnieje! Pliki będą utworzone w folderze projektu");  
            	outputFilePath="";
            }
            
        }
        catch (IOException | NumberFormatException | ParserConfigurationException | DOMException | SAXException e) {
        	outputFilePath="";
            extremePercent=5.0;
            dayInterval=30;
            detectedVarMaxTolerance=200.0;
            detectedVarNetMaxTolerance=200.0;
            detectedAndHeightMaxDiff=500.0;
            detectedAndHeightNetMaxDiff=500.0;
            System.out.println("błąd wczytywania pliku konfiguracyjnego! ");
            e.printStackTrace();
            new java.util.Scanner(System.in).nextLine();
            System.exit(0);
        }
	}
	catch (Exception e)
    {
		outputFilePath="";
       extremePercent=5.0;
       dayInterval=30;
       detectedVarMaxTolerance=200.0;
       detectedVarNetMaxTolerance=200.0;
       detectedAndHeightMaxDiff=500.0;
       detectedAndHeightNetMaxDiff=500.0;
       System.out.println("błąd wczytywania pliku konfiguracyjnego! ");
       new java.util.Scanner(System.in).nextLine();
        System.exit(0);
        
    }
    }

    
    // - - - Gettery - - - 
    public static String getCsvFilePath() {
        return csvFilePath;
    }

    public static Double getDetectedVarMaxTolerance() {
        return detectedVarMaxTolerance;
    }

    public static Double getDetectedVarNetMaxTolerance() {
        return detectedVarNetMaxTolerance;
    }

    public static Double getDetectedAndHeightMaxDiff() {
        return detectedAndHeightMaxDiff;
    }

    public static Double getDetectedAndHeightNetMaxDiff() {
        return detectedAndHeightNetMaxDiff;
    }
    
    public static String getOutputFilePath() {
		return outputFilePath;
	}
    
	public static Double getExtremePercent() {
		return extremePercent;
	}

	public static int getDayInterval() {
		return dayInterval;
	}

}
