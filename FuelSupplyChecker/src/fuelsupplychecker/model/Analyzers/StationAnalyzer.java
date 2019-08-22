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
public class StationAnalyzer extends BaseRecordAnalyzer {
    private Integer stationId;

    public Integer getStationId() {
        return stationId;
    }

    public StationAnalyzer(Supply supply) {
        this.stationId = supply.getStationId();
        
        this.supplies = new ArrayList<>();
        this.supplies.add(supply);
    }
    
}
