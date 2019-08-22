/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fuelsupplychecker.model;

import java.util.Date;

/**
 *
 * @author michi
 */
public class Supply {

    

	private Integer id;
    private Integer stationId;
    private Integer tankId;
    private String fuelType;
    private Integer tankCapacity;
    private Date startTime;
    private Date endTime;
    private Integer truckId;
    private Integer driverId;
    private Integer terminalId;
    
    private Integer detectedSupplyCapacity;
    private Integer detectedSupplyCapacityNet;
    
    private Integer declaredSupplyCapacity;
    private Integer declaredSupplyCapacityNet;
    
    private Integer tankStartHeight;
    private Integer tankEndHeight;
    

    private double VarBasedOnDetected;
    private double VarBasedOnDetectedNet;
    private double VarBasedOnHeight;
    private double VarBasedOnHeightNet;
    
    private boolean possibleLeak;
    private boolean possibleDetectorError;
    private boolean excess;
    
    
    final Double tolerance = 0.05;
   
    
    /**
     * Default constructor
     */
    public Supply(Integer id)
    {
        this.id = id;
    }
    
    

    
    
    /**
     * Obliczanie var na podstawie wykrytej objętości dostawy
     */
    public void calculateVarBasedOnDetected()
    {
    	try 
        {
            
            VarBasedOnDetected = declaredSupplyCapacity - detectedSupplyCapacity;
                
    	}
    	catch(Exception e) 
        {
    		
    	}
    }
    
    /**
     * Obliczanie var na podstawie wykrytej objętości dostawy przeskalowanej do temperatury referencyjnej
     */
    public void calculateVarBasedOnDetectedNet()
    {
    	try 
        {
            VarBasedOnDetectedNet = declaredSupplyCapacityNet - detectedSupplyCapacityNet;
                
    	}
    	catch(Exception e) 
        {
    		
    	}
    }
    
    /**
     * Obliczanie var na podstawie objętości dostawy obliczonej za pomocą pomiarów wysokości
     */
    public void calculateVarBasedOnHeight()//co� na stacji sie dzia�o
    {
    	try
        {
            // stare
            //VarBasedOnHeight = (tankCapacity * tankEndHeight / 100.0) - (tankCapacity * tankStartHeight / 100.0) - detectedSupplyCapacity;
            
            // obliczenie objetosci dostawy na podstawie otrzymanych wysokości
            double supplyCapacityBasedOnHeight = (tankCapacity * tankEndHeight / 100.0) - (tankCapacity * tankStartHeight / 100.0);
            
            // obliczenie varu
            VarBasedOnHeight = this.declaredSupplyCapacity - supplyCapacityBasedOnHeight;
        

    		
	}
	catch(Exception e) 
        {
		
	}
    }
    
    /**
     * Obliczanie var na podstawie objętości dostawy obliczonej za pomocą pomiarów wysokości do zadeklarowanej przeskalowanej do temperatury referencyjnej
     */
    public void calculateVarBasedOnHeightNet()//co� na stacji sie dzia�o
    {
    	try
        {
            // stare
            //VarBasedOnHeight = (tankCapacity * tankEndHeight / 100.0) - (tankCapacity * tankStartHeight / 100.0) - detectedSupplyCapacity;
            
            // obliczenie objetosci dostawy na podstawie otrzymanych wysokości
            double supplyCapacityBasedOnHeight = (tankCapacity * tankEndHeight / 100.0) - (tankCapacity * tankStartHeight / 100.0);
            
            // obliczenie varu
            VarBasedOnHeightNet = this.declaredSupplyCapacityNet - supplyCapacityBasedOnHeight;
        

    		
	}
	catch(Exception e) 
        {
		
	}
    }
    
    public Supply(String inputStreamLine)
    {
        this.decodeTextLine(inputStreamLine);
    }
    
    // <editor-fold desc="Getters and setters" defaultstate="collapsed">
    
    public boolean isPossibleLeak() {
		return possibleLeak;
	}


	public void setPossibleLeak(boolean possibleLeak) {
		this.possibleLeak = possibleLeak;
	}


	public boolean isPossibleDetectorError() {
		return possibleDetectorError;
	}


	public void setPossibleDetectorError(boolean possibleDetectorError) {
		this.possibleDetectorError = possibleDetectorError;
	}





	public double getVarBasedOnDetected() {
		return VarBasedOnDetected;
	}


	public void setVarBasedOnDetected(double varBasedOnDetected) {
		VarBasedOnDetected = varBasedOnDetected;
	}


	public double getVarBasedOnDetectedNet() {
		return VarBasedOnDetectedNet;
	}


	public void setVarBasedOnDetectedNet(double varBasedOnDetectedNet) {
		VarBasedOnDetectedNet = varBasedOnDetectedNet;
	}


	public double getVarBasedOnHeightNet() {
		return VarBasedOnHeightNet;
	}


	public void setVarBasedOnHeightNet(double varBasedOnHeightNet) {
		VarBasedOnHeightNet = varBasedOnHeightNet;
	}


	public void setVarBasedOnHeight(double varBasedOnHeight) {
		VarBasedOnHeight = varBasedOnHeight;
	}
    
    public Integer getId() {
        return id;
    }
    
    public Integer getStationId() {
        return stationId;
    }

    public void setStationId(Integer stationId) {
        this.stationId = stationId;
    }

    public Integer getTankId() {
        return tankId;
    }

    public void setTankId(Integer tankId) {
        this.tankId = tankId;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public Integer getTankCapacity() {
        return tankCapacity;
    }

    public void setTankCapacity(Integer tankCapacity) {
        this.tankCapacity = tankCapacity;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getTruckId() {
        return truckId;
    }

    public void setTruckId(Integer truckId) {
        this.truckId = truckId;
    }

    public Integer getDriverId() {
        return driverId;
    }

    public void setDriverId(Integer driverId) {
        this.driverId = driverId;
    }

    public Integer getTerminalId() {
        return terminalId;
    }

    public void setTerminalId(Integer terminalId) {
        this.terminalId = terminalId;
    }

    public Integer getDetectedSupplyCapacity() {
        return detectedSupplyCapacity;
    }

    public void setDetectedSupplyCapacity(Integer detectedSupplyCapacity) {
        this.detectedSupplyCapacity = detectedSupplyCapacity;
    }

    public Integer getDetectedSupplyCapacityNet() {
        return detectedSupplyCapacityNet;
    }

    public void setDetectedSupplyCapacityNet(Integer detectedSupplyCapacityNet) {
        this.detectedSupplyCapacityNet = detectedSupplyCapacityNet;
    }

    public Integer getDeclaredSupplyCapacity() {
        return declaredSupplyCapacity;
    }

    public void setDeclaredSupplyCapacity(Integer declaredSupplyCapacity) {
        this.declaredSupplyCapacity = declaredSupplyCapacity;
    }

    public Integer getDeclaredSupplyCapacityNet() {
        return declaredSupplyCapacityNet;
    }

    public void setDeclaredSupplyCapacityNet(Integer declaredSupplyCapacityNet) {
        this.declaredSupplyCapacityNet = declaredSupplyCapacityNet;
    }

    public Integer getTankStartHeight() {
        return tankStartHeight;
    }

    public void setTankStartHeight(Integer tankStartHeight) {
        this.tankStartHeight = tankStartHeight;
    }

    public Integer getTankEndHeight() {
        return tankEndHeight;
    }

    public void setTankEndHeight(Integer tankEndHeight) {
        this.tankEndHeight = tankEndHeight;
    }
    


    public Double getVarBasedOnMeasurment() {
	return VarBasedOnDetected;
    }

    public void setVarBasedOnMeasurment(Double var) {
	VarBasedOnDetected = var;
    }


    public Double getVarBasedOnHeight() {
	return VarBasedOnHeight;
    }


    public void setVarBasedOnHeight(Double varTransport) {
    	VarBasedOnHeight = varTransport;
    }
    
    // </editor-fold>
    

    
    private void decodeTextLine(String inputStreamLine)
    {
        
    }





	public boolean getExcess() {
		return excess;
	}





	public void setExcess(boolean excess) {
		this.excess = excess;
	}

    
}
