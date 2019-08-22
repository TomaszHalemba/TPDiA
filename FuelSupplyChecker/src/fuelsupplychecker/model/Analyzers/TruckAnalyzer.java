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
public class TruckAnalyzer extends BaseRecordAnalyzer {
    
    private Integer truckId;
    
     public TruckAnalyzer() {
        this.supplies = new ArrayList<>();
    }
    
    public Integer getTruckId() {
        return truckId;
    }

    public TruckAnalyzer(Supply supply) {
        this.truckId = supply.getTruckId();
        
        this.supplies = new ArrayList<>();
        this.supplies.add(supply);
    }
}
