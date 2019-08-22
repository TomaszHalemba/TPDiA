/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fuelsupplychecker.model;

//import com.opencsv.bean.CsvToBeanBuilder;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import fuelsupplychecker.model.Analyzers.DriverAnalyzer;
import fuelsupplychecker.model.Analyzers.RecordsIntervalAnalyzerDriver;
import fuelsupplychecker.model.Analyzers.RecordsIntervalAnalyzerStation;
import fuelsupplychecker.model.Analyzers.RecordsIntervalAnalyzerTank;
import fuelsupplychecker.model.Analyzers.RecordsIntervalAnalyzerTerminal;
import fuelsupplychecker.model.Analyzers.RecordsIntervalAnalyzerTruck;
import fuelsupplychecker.model.Analyzers.StationAnalyzer;
import fuelsupplychecker.model.Analyzers.TankAnalyzer;
import fuelsupplychecker.model.Analyzers.TerminalAnalyzer;
import fuelsupplychecker.model.Analyzers.TruckAnalyzer;

/**
 *
 * @author michi
 */
public class FileManager {

	public ArrayList<Supply> getSuppliesFromCsv(String path) {
		ArrayList<Supply> list = new ArrayList<Supply>();
                
		File file = new File(path);
                Integer suppliesCounter = 0;
		Integer counter = 0;
		Boolean firstLine = true;

		try {
			Scanner inputStream = new Scanner(file);
			inputStream.useDelimiter(";|\\r\\n");
			Supply newSupply = new Supply(suppliesCounter);

			String tmp = new String();

			while (inputStream.hasNext()) {

				if (!firstLine) {
					switch (counter) {
					case 0: // stationId
					{
						newSupply.setStationId(inputStream.nextInt());
					}
						break;

					case 1: // tankId
					{
						String tankid=inputStream.next();
						int tankidInt=tankid.charAt(0)-48;
						newSupply.setTankId(tankidInt);
						
					}
						break;

					// input is string
					case 2: {
						try {
						newSupply.setFuelType(inputStream.next());
					} catch (Exception e) {

					}
					}
						break;

					case 3: // FuelType
					{
						try {
						newSupply.setTankCapacity(inputStream.nextInt());
					} catch (Exception e) {

					}
					}
						break;

					case 4: // StartTime
					{

						DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm");

						Date startTime;
						try {
							startTime = dateFormat.parse(inputStream.next());
							newSupply.setStartTime(startTime);
						} catch (ParseException e) {
							System.out.println("Unparseable using " + dateFormat);
						}

					}
						break;

					case 5: // EndTime
					{
						DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm");
						Date startTime;
						try {
							startTime = dateFormat.parse(inputStream.next());
							newSupply.setEndTime(startTime);
						} catch (ParseException e) {
							System.out.println("Unparseable using " + dateFormat);
						}

					}
						break;

					case 6: // TruckId
					{
						try {
						newSupply.setTruckId(inputStream.nextInt());
					} catch (Exception e) {
					}
					}
						break;

					case 7: // DriverId
					{
						try {
						newSupply.setDriverId(inputStream.nextInt());
					} catch (Exception e) {
					}
					}
						break;

					case 8: // setTerminalId
					{
						try {
						newSupply.setTerminalId(inputStream.nextInt());
					} catch (Exception e) {

					}
					}
						break;

					case 9: // Detected
					{
						try {
						newSupply.setDetectedSupplyCapacity(inputStream.nextInt());
					} catch (Exception e) {
					}
					}
					
						break;

					case 10: // DetectedNet
					{
						try {
						newSupply.setDetectedSupplyCapacityNet(inputStream.nextInt());
					} catch (Exception e) {

					}
					}
						break;

					case 11: // Declared
					{
						try{
						newSupply.setDeclaredSupplyCapacity(inputStream.nextInt());
					} catch (Exception e) {

					}
					}
						break;

					case 12: // DeclaredNet
					{
						try {
						newSupply.setDeclaredSupplyCapacityNet(inputStream.nextInt());
					} catch (Exception e) {
					}
					}
						break;

					case 13: // StartHeight
					{
						try {
							newSupply.setTankStartHeight(inputStream.nextInt());
						} catch (Exception e) {

						}
					}
						break;

					case 14: // end_height_proc
					{
						try {
							newSupply.setTankEndHeight(inputStream.nextInt());
						} catch (Exception e) {

						}
					}
						break;
					}

				
				} 
                                else 
                                {
                                    tmp = inputStream.next();
				}
                                
                                
				counter++;
				if (counter >= 15) {
					if (!firstLine) {
						list.add(newSupply);
						newSupply = new Supply(suppliesCounter);

					} else {
						firstLine = false;
					}
					counter = 0;
                                        suppliesCounter++;
				}

			}
			// after loop, close scanner
			inputStream.close();

		} catch (FileNotFoundException e) {

			e.printStackTrace();
			   new java.util.Scanner(System.in).nextLine();
	            System.exit(-1);
		} catch (java.util.InputMismatchException e) {
			String message = e.getMessage();
			System.out.println(message);
			   new java.util.Scanner(System.in).nextLine();
	            System.exit(-1);
		}
		return list;
	}

	public ArrayList getStringDataFromCsv(String path) {
		ArrayList list = new ArrayList();
		// -File class needed to turn stringName to actual file
		File file = new File(path);

		try {
			// -read from filePooped with Scanner class
			Scanner inputStream = new Scanner(file);
			inputStream.useDelimiter(";");
			// hashNext() loops line-by-line
			while (inputStream.hasNext()) {
				// read single line, put in string
				String data = inputStream.next();
				list.add(data);

			}
			// after loop, close scanner
			inputStream.close();

		} catch (FileNotFoundException e) {

			e.printStackTrace();
			   new java.util.Scanner(System.in).nextLine();
	            System.exit(-1);
		}
		return list;
	}


	
	public static String formating(Object string, int length) {
	    return String.format("%1$"+length+ "s", string);
	}
	
	public void printRecordsIntervalAnalyzerTank(ArrayList<RecordsIntervalAnalyzerTank> list,String path)
	{
		
		for (RecordsIntervalAnalyzerTank column : list) {
			String name = column.getFolderName();

			
			File dir = new File(path);
			dir.mkdir();

			PrintWriter writer;
			try {
				writer = new PrintWriter(path +"\\"+ name+".txt", "UTF-8");

				writer.println("stacja | zbiornik | błędów ogólnych |  procent błędów  | błędy detektora |  procent błędów detektora  | wycieków |  procent wycieków  | nadmiar | procent nadmiarów |");

				for(TankAnalyzer entry:column.getRecordsIntervalAnalysis())
				{
					writer.print(formating(entry.getStationId()+"|",8));
					writer.print(formating(entry.getTankId()+"|",11));
					writer.print(formating(entry.getErrorCount()+"|",18));
					writer.print(formating(entry.getErrorPercentage()+"|",19));
					writer.print(formating(entry.getProbDetectorErrorCount()+"|",18));
					writer.print(formating(entry.getProbDetectorErrorPercentage()+"|",29));
					writer.print(formating(entry.getProbLeakCount()+"|",11));
					writer.print(formating(entry.getPropLeakPercentage()+"|",21));
					writer.print(formating(entry.getExcessCount()+"|",10));
					writer.print(formating(entry.getExcessPercentage()+"|",20));

					writer.println();

				}
				writer.close();

			} catch (FileNotFoundException e) {
				
				// TODO Auto-generated catch block
				e.printStackTrace();
				   new java.util.Scanner(System.in).nextLine();
		            System.exit(-1);
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				   new java.util.Scanner(System.in).nextLine();
		            System.exit(-1);
			}
		}
	}
	
	public void printRecordsIntervalAnalyzerDriver(ArrayList<RecordsIntervalAnalyzerDriver> list,String path)
	{
		
		for (RecordsIntervalAnalyzerDriver column : list) {
			String name = column.getFolderName();

			
			File dir = new File(path);
			dir.mkdir();

			PrintWriter writer;
			try {
				writer = new PrintWriter(path +"\\"+ name+".txt", "UTF-8");

				writer.println("kierowca | błędów ogólnych |  procent błędów  | błędy detektora |  procent błędów detektora  | wycieków |  procent wycieków  | nadmiar | procent nadmiarów |");

				for(DriverAnalyzer entry:column.getRecordsIntervalAnalysis())
				{
					writer.print(formating(entry.getDriverId()+"|",10));
					writer.print(formating(entry.getErrorCount()+"|",18));
					writer.print(formating(entry.getErrorPercentage()+"|",19));
					writer.print(formating(entry.getProbDetectorErrorCount()+"|",18));
					writer.print(formating(entry.getProbDetectorErrorPercentage()+"|",29));
					writer.print(formating(entry.getProbLeakCount()+"|",11));
					writer.print(formating(entry.getPropLeakPercentage()+"|",21));
					writer.print(formating(entry.getExcessCount()+"|",10));
					writer.print(formating(entry.getExcessPercentage()+"|",20));
					writer.println();

				}
				writer.close();

			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				   new java.util.Scanner(System.in).nextLine();
		            System.exit(-1);
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				   new java.util.Scanner(System.in).nextLine();
		            System.exit(-1);
			}
		}
	}

	
	public void printRecordsIntervalAnalyzerTerminal(ArrayList<RecordsIntervalAnalyzerTerminal> list,String path)
	{
		
		for (RecordsIntervalAnalyzerTerminal column : list) {
			String name = column.getFolderName();

			
			File dir = new File(path);
			dir.mkdir();

			PrintWriter writer;
			try {
				writer = new PrintWriter(path +"\\"+ name+".txt", "UTF-8");

				writer.println("terminal | błędów ogólnych |  procent błędów  | błędy detektora |  procent błędów detektora  | wycieków |  procent wycieków  | nadmiar | procent nadmiarów |");

				for(TerminalAnalyzer entry:column.getRecordsIntervalAnalysis())
				{
					writer.print(formating(entry.getTerminalId()+"|",10));
					writer.print(formating(entry.getErrorCount()+"|",18));
					writer.print(formating(entry.getErrorPercentage()+"|",19));
					writer.print(formating(entry.getProbDetectorErrorCount()+"|",18));
					writer.print(formating(entry.getProbDetectorErrorPercentage()+"|",29));
					writer.print(formating(entry.getProbLeakCount()+"|",11));
					writer.print(formating(entry.getPropLeakPercentage()+"|",21));
					writer.print(formating(entry.getExcessCount()+"|",10));
					writer.print(formating(entry.getExcessPercentage()+"|",20));
					writer.println();

				}
				writer.close();

			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				   new java.util.Scanner(System.in).nextLine();
		            System.exit(-1);
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				   new java.util.Scanner(System.in).nextLine();
		            System.exit(-1);
			}
		}
	}
	
	public void printRecordsIntervalAnalyzerTruck(ArrayList<RecordsIntervalAnalyzerTruck> list,String path)
	{
		
		for (RecordsIntervalAnalyzerTruck column : list) {
			String name = column.getFolderName();

			
			File dir = new File(path);
			dir.mkdir();

			PrintWriter writer;
			try {
				writer = new PrintWriter(path +"\\"+ name+".txt", "UTF-8");

				writer.println("cysterna | błędów ogólnych |  procent błędów  | błędy detektora |  procent błędów detektora  | wycieków |  procent wycieków  | nadmiar | procent nadmiarów |");

				for(TruckAnalyzer entry:column.getRecordsIntervalAnalysis())
				{
					writer.print(formating(entry.getTruckId()+"|",10));
					writer.print(formating(entry.getErrorCount()+"|",18));
					writer.print(formating(entry.getErrorPercentage()+"|",19));
					writer.print(formating(entry.getProbDetectorErrorCount()+"|",18));
					writer.print(formating(entry.getProbDetectorErrorPercentage()+"|",29));
					writer.print(formating(entry.getProbLeakCount()+"|",11));
					writer.print(formating(entry.getPropLeakPercentage()+"|",21));
					writer.print(formating(entry.getExcessCount()+"|",10));
					writer.print(formating(entry.getExcessPercentage()+"|",20));
					writer.println();
					
				}
				writer.close();

			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				   new java.util.Scanner(System.in).nextLine();
		            System.exit(-1);
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				   new java.util.Scanner(System.in).nextLine();
		            System.exit(-1);
			}
		}
	}
	
	public void printRecordsIntervalAnalyzerStation(ArrayList<RecordsIntervalAnalyzerStation> list,String path)
	{
		
		for (RecordsIntervalAnalyzerStation column : list) {
			String name = column.getFolderName();

			
			File dir = new File(path);
			dir.mkdir();

			PrintWriter writer;
			try {
				writer = new PrintWriter(path +"\\"+ name+".txt", "UTF-8");

				writer.println("stacja | błędów ogólnych |  procent błędów  | błędy detektora |  procent błędów detektora  | wycieków |  procent wycieków  | nadmiar | procent nadmiarów |");

				for(StationAnalyzer entry:column.getRecordsIntervalAnalysis())
				{
					writer.print(formating(entry.getStationId()+"|",8));
					writer.print(formating(entry.getErrorCount()+"|",18));
					writer.print(formating(entry.getErrorPercentage()+"|",19));
					writer.print(formating(entry.getProbDetectorErrorCount()+"|",18));
					writer.print(formating(entry.getProbDetectorErrorPercentage()+"|",29));
					writer.print(formating(entry.getProbLeakCount()+"|",11));
					writer.print(formating(entry.getPropLeakPercentage()+"|",21));
					writer.print(formating(entry.getExcessCount()+"|",10));
					writer.print(formating(entry.getExcessPercentage()+"|",20));
					writer.println();
					

				}
				writer.close();

			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				   new java.util.Scanner(System.in).nextLine();
		            System.exit(-1);
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				   new java.util.Scanner(System.in).nextLine();
		            System.exit(-1);
			}
		}
	}

	

public void printTankTop(ArrayList<TankAnalyzer>list, String path)
{
	PrintWriter writer;
	try {
		writer = new PrintWriter(path, "UTF-8");
		writer.println("Najbardziej niezgodne obiekty:");
		writer.println("stacja | zbiornik | błędów ogólnych |  procent błędów  | błędy detektora |  procent błędów detektora  | wycieków |  procent wycieków  | nadmiar | procent nadmiarów |   Interwały czasu   |");

		for(TankAnalyzer entry:list)
		{
			writer.print(formating(entry.getStationId()+"|",8));
			writer.print(formating(entry.getTankId()+"|",11));
			writer.print(formating(entry.getErrorCount()+"|",18));
			writer.print(formating(entry.getErrorPercentage()+"|",19));
			writer.print(formating(entry.getProbDetectorErrorCount()+"|",18));
			writer.print(formating(entry.getProbDetectorErrorPercentage()+"|",29));
			writer.print(formating(entry.getProbLeakCount()+"|",11));
			writer.print(formating(entry.getPropLeakPercentage()+"|",21));
			writer.print(formating(entry.getExcessCount()+"|",10));
			writer.print(formating(entry.getExcessPercentage()+"|",20));
			writer.print(formating(entry.getMinMaxDate()+"|",22));
			writer.println();
		}

		writer.close();
	
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		   new java.util.Scanner(System.in).nextLine();
           System.exit(-1);
	} catch (UnsupportedEncodingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		   new java.util.Scanner(System.in).nextLine();
           System.exit(-1);
	}
}

public void printDriverTop(ArrayList<DriverAnalyzer>list, String path)
{
	PrintWriter writer;
	try {
		writer = new PrintWriter(path, "UTF-8");
		writer.println("Najbardziej niezgodne obiekty:");
		writer.println("kierowca | błędów ogólnych |  procent błędów  | błędy detektora |  procent błędów detektora  | wycieków |  procent wycieków  | nadmiar | procent nadmiarów |   Interwały czasu   |");

		for(DriverAnalyzer entry:list)
		{
			writer.print(formating(entry.getDriverId()+"|",10));
			writer.print(formating(entry.getErrorCount()+"|",18));
			writer.print(formating(entry.getErrorPercentage()+"|",19));
			writer.print(formating(entry.getProbDetectorErrorCount()+"|",18));
			writer.print(formating(entry.getProbDetectorErrorPercentage()+"|",29));
			writer.print(formating(entry.getProbLeakCount()+"|",11));
			writer.print(formating(entry.getPropLeakPercentage()+"|",21));
			writer.print(formating(entry.getExcessCount()+"|",10));
			writer.print(formating(entry.getExcessPercentage()+"|",20));
			writer.print(formating(entry.getMinMaxDate()+"|",22));
			writer.println();

		}

		writer.close();
	
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		   new java.util.Scanner(System.in).nextLine();
           System.exit(-1);
	} catch (UnsupportedEncodingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		   new java.util.Scanner(System.in).nextLine();
           System.exit(-1);
	}
	
}

public void printStationTop(ArrayList<StationAnalyzer>list, String path)
{
	PrintWriter writer;
	try {
		writer = new PrintWriter(path, "UTF-8");
		writer.println("Najbardziej niezgodne obiekty:");

			writer.println("stacja | błędów ogólnych |  procent błędów  | błędy detektora |  procent błędów detektora  | wycieków |  procent wycieków  | nadmiar | procent nadmiarów |   Interwały czasu   |");

			for(StationAnalyzer entry:list)
			{
				writer.print(formating(entry.getStationId()+"|",8));
				writer.print(formating(entry.getErrorCount()+"|",18));
				writer.print(formating(entry.getErrorPercentage()+"|",19));
				writer.print(formating(entry.getProbDetectorErrorCount()+"|",18));
				writer.print(formating(entry.getProbDetectorErrorPercentage()+"|",29));
				writer.print(formating(entry.getProbLeakCount()+"|",11));
				writer.print(formating(entry.getPropLeakPercentage()+"|",21));
				writer.print(formating(entry.getExcessCount()+"|",10));
				writer.print(formating(entry.getExcessPercentage()+"|",20));
				writer.print(formating(entry.getMinMaxDate()+"|",22));
				writer.println();
				

			}

		writer.close();
	
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		   new java.util.Scanner(System.in).nextLine();
           System.exit(-1);
	} catch (UnsupportedEncodingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		   new java.util.Scanner(System.in).nextLine();
           System.exit(-1);
	}
}

public void printTerminalTop(ArrayList<TerminalAnalyzer>list, String path)
{
	PrintWriter writer;
	try {
		writer = new PrintWriter(path, "UTF-8");
		writer.println("Najbardziej niezgodne obiekty:");

			writer.println("terminal | błędów ogólnych |  procent błędów  | błędy detektora |  procent błędów detektora  | wycieków |  procent wycieków  | nadmiar | procent nadmiarów |   Interwały czasu   |");

			for(TerminalAnalyzer entry:list)
			{
				writer.print(formating(entry.getTerminalId()+"|",10));
				writer.print(formating(entry.getErrorCount()+"|",18));
				writer.print(formating(entry.getErrorPercentage()+"|",19));
				writer.print(formating(entry.getProbDetectorErrorCount()+"|",18));
				writer.print(formating(entry.getProbDetectorErrorPercentage()+"|",29));
				writer.print(formating(entry.getProbLeakCount()+"|",11));
				writer.print(formating(entry.getPropLeakPercentage()+"|",21));
				writer.print(formating(entry.getExcessCount()+"|",10));
				writer.print(formating(entry.getExcessPercentage()+"|",20));
				writer.print(formating(entry.getMinMaxDate()+"|",22));
				writer.println();

			}

		writer.close();
	
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		   new java.util.Scanner(System.in).nextLine();
           System.exit(-1);
	} catch (UnsupportedEncodingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		   new java.util.Scanner(System.in).nextLine();
           System.exit(-1);
	}
}


public void printTruckTop(ArrayList<TruckAnalyzer>list, String path)
{
	PrintWriter writer;
	try {
		writer = new PrintWriter(path, "UTF-8");
		writer.println("Najbardziej niezgodne obiekty:");

		writer.println("cysterna | błędów ogólnych |  procent błędów  | błędy detektora |  procent błędów detektora  | wycieków |  procent wycieków  | nadmiar | procent nadmiarów |   Interwały czasu   |");

		for(TruckAnalyzer entry:list)
		{
			writer.print(formating(entry.getTruckId()+"|",10));
			writer.print(formating(entry.getErrorCount()+"|",18));
			writer.print(formating(entry.getErrorPercentage()+"|",19));
			writer.print(formating(entry.getProbDetectorErrorCount()+"|",18));
			writer.print(formating(entry.getProbDetectorErrorPercentage()+"|",29));
			writer.print(formating(entry.getProbLeakCount()+"|",11));
			writer.print(formating(entry.getPropLeakPercentage()+"|",21));
			writer.print(formating(entry.getExcessCount()+"|",10));
			writer.print(formating(entry.getExcessPercentage()+"|",20));
			writer.print(formating(entry.getMinMaxDate()+"|",22));
			writer.println();
			
		}

		writer.close();
	
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		   new java.util.Scanner(System.in).nextLine();
           System.exit(-1);
	} catch (UnsupportedEncodingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		   new java.util.Scanner(System.in).nextLine();
           System.exit(-1);
	}
}

}
		

