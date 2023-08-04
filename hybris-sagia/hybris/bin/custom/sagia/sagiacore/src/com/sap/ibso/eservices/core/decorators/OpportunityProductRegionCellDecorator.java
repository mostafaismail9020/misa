package com.sap.ibso.eservices.core.decorators;

import de.hybris.platform.util.CSVCellDecorator;

import java.util.Map;

public class OpportunityProductRegionCellDecorator implements CSVCellDecorator {
    private static final String DELIMITER="\\*";
    private static final String COMMA=",";

    @Override
    public String decorate(int position, Map<Integer, String> srcLine) {
        String finalResult = "";
        final String csvCell = srcLine.get(Integer.valueOf(position));
        if (csvCell == null || csvCell.length() == 0)
        {
            return csvCell;
        }
        else
        {
            String[] result = csvCell.split(DELIMITER);
            csvCell.split(DELIMITER);
            if(!String.valueOf(result).isEmpty()){
                for(String loop:result)
                {
                    finalResult=finalResult+(loop);
                     finalResult=finalResult+(COMMA);
                }
                finalResult=finalResult.substring(0, finalResult.length() -1);
            }
        }
        return finalResult;
    }
}
