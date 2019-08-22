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
public class TerminalAnalyzer extends BaseRecordAnalyzer {

    public TerminalAnalyzer() {
        this.supplies = new ArrayList<>();
    }
    
    public TerminalAnalyzer(Supply supply) {
        this.terminalId = supply.getTerminalId();
        
        this.supplies = new ArrayList<>();
        this.supplies.add(supply);
    }
    
    private Integer terminalId;

    public Integer getTerminalId() {
        return terminalId;
        
        
    }
    
//    @Override
//    public void addSupply(Supply supply) 
//    {
//        this.terminalId = supply.getTerminalId();
//        this.supplies.add(supply);
//    }
    
}
