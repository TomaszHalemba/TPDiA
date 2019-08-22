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
public class TankAnalyzer extends BaseRecordAnalyzer  {
    
    private Integer tankId;
    private Integer stationId;

    public Integer getTankId() {
        return tankId;
    }

    public Integer getStationId() {
        return stationId;
    }

    public TankAnalyzer(Integer tankId, Integer stationId) {
        this.tankId = tankId;
        this.stationId = stationId;
        
        this.supplies = new ArrayList<>();
    }
    
    public TankAnalyzer(Supply supply) 
    {       
        this.tankId = supply.getTankId();
        this.stationId = supply.getStationId();
        
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
        this.tankId = supply.getTankId();
        this.stationId = supply.getStationId();
        
        this.supplies.add(supply);
    }
    

    
    

    
}
