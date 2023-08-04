package com.sap.ibso.eservices.core.decorators;

import de.hybris.platform.util.CSVCellDecorator;

import java.util.Map;

public class OpportunityProductCellDecorator implements CSVCellDecorator {
    private static final String DELIMITER="\\*";
    private static final String BULLET_LIST="<ul class='bulled-list mt-3'>";
    private static final String LIST_ITEM_OPENING="<li>";
    private static final String SPAN_OPENING="<span>";
    private static final String LIST_ITEM_CLOSING="</li>";
    private static final String SPAN_CLOSING="</span>";
    private static final String UL_ITEM_CLOSING="</ul>";

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
            finalResult=finalResult+(BULLET_LIST);
            if(!String.valueOf(result).isEmpty()){
                for(String loop:result)
                {
                     finalResult=finalResult+(LIST_ITEM_OPENING);
                     finalResult=finalResult+(SPAN_OPENING);
                     finalResult=finalResult+(loop);
                     finalResult=finalResult+(SPAN_CLOSING);
                     finalResult=finalResult+(LIST_ITEM_CLOSING);
                }
            }
            finalResult=finalResult+(UL_ITEM_CLOSING);
        }
        return finalResult;
    }
}
