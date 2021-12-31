package com.sap.ibso.eservices.sagiaservices.services.legalconsultation;

import com.sap.ibso.eservices.sagiaservices.data.zesrvEnhOData.FinancialStatementHDRData;
import com.sap.ibso.eservices.sagiaservices.services.AbstractSagiaService;
import com.sap.ibso.eservices.sagiaservices.services.legalconsultation.dto.FinancialStatementForm;
import com.sap.ibso.eservices.sagiaservices.utils.QueryOptionsBuilder;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

public class FinancialStatementHDRService extends AbstractSagiaService<FinancialStatementHDRData> {
	
    private FinancialStatementHDRConverter financialStatementHDRConverter;
    /**
     * retrieves FinancialStatementList
     * @return list with all Financial Statement
     */
    public final Collection<FinancialStatementHDRData> getFinancialStatementList() {
        return super.getCollection(FinancialStatementHDRData.class);
    }

    /**
     * return a Financial Statement by id, with all the navigation properties expanded
     * @param srId id of the Financial Statement
     * @return FinancialStatementHDRData
     */
    public final FinancialStatementHDRData getFinancialStatementBy(String srId) {
        return get(FinancialStatementHDRData.class, srId, new QueryOptionsBuilder()
                        .expand(createFinancialStatementExpandQuery())
                        .build());
        }

    /**
     * get all navigation properties of Financial Statement
     * build full expression of $expand parameter
     * @return String
     */
    private String createFinancialStatementExpandQuery() {
        return Arrays.asList(FinancialStatementExpandableEntities.values())
                .stream()
                .map(FinancialStatementExpandableEntities::navEntity)
                .collect(Collectors.joining(","));
    }

    /**
     * create new Financial Statement
     * @param financialStatementForm financialStatementForm
     */

    public final void createFinancialStatement(FinancialStatementForm financialStatementForm){
    	FinancialStatementHDRData financialStatement = financialStatementHDRConverter.fromFormData(financialStatementForm);
    	financialStatement.setTransType("ZS11");
        save(financialStatement);
    }

    public FinancialStatementHDRConverter getFinancialStatementHDRConverter()
    {
        return financialStatementHDRConverter;
    }

    public void setFinancialStatementHDRConverter(final FinancialStatementHDRConverter financialStatementHDRConverter)
    {
        this.financialStatementHDRConverter = financialStatementHDRConverter;
    }

}
