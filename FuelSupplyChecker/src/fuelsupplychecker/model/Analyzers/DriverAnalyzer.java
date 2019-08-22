/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fuelsupplychecker.model.Analyzers;

import fuelsupplychecker.model.Supply;
import java.util.ArrayList;

/**
 *
 * @author michi
 */
public class DriverAnalyzer extends BaseRecordAnalyzer  {
    
    private Integer driverId;


    public Integer getDriverId() {
        return driverId;
    }



    public DriverAnalyzer(Integer driverId) {
        this.driverId = driverId;

        
        this.supplies = new ArrayList<>();
    }
    
    public DriverAnalyzer(Supply supply) 
    {       
        this.driverId = supply.getDriverId();
        
        this.supplies = new ArrayList<>();
        this.supplies.add(supply);
    }
    
    
    @Override
    public void addSuppliesList(ArrayList<Supply> supplies) 
    {
        for (Supply supply : supplies)
        {
            
        }
    }
    
    @Override
    public void addSupply(Supply supply) 
    {
    	this.driverId=supply.getDriverId();
        this.supplies.add(supply);
    }

}
