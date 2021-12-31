package com.sap.ibso.eservices.facades.populators;

import com.sap.ibso.eservices.core.sagia.services.SagiaFormatProvider;
import com.sap.ibso.eservices.facades.data.financial.*;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.FinanceContent;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.FinanceHDRS;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/*
 * Suppress sonar warning (squid:RedundantThrowsDeclarationCheck | "throws" declarations should not be superfluous
 * Marker exception needed even if is a RuntimeException
 */
@SuppressWarnings("squid:RedundantThrowsDeclarationCheck")
public class FinancialPopulator implements Populator<FinanceHDRS,FinancialData>{

    private SagiaFormatProvider sagiaFormatProvider;

    @Override
    public void populate(FinanceHDRS financeHDRS, FinancialData financialData) throws ConversionException {
        financialData.setId(financeHDRS.getBpID());
        financialData.setYear(financeHDRS.getYear());
        financialData.setSrGuid(financeHDRS.getSr_Guid());
        financialData.setSrId(financeHDRS.getSrID());
        financialData.setTransationType(financeHDRS.getTrans_Type());
        financialData.setCrDateData(sagiaFormatProvider.getLocalizedDateData(financeHDRS.getSr_Cr_Date()));

        financialData.setSrStatusCode(financeHDRS.getSr_St_Code());
        financialData.setSrStatusDescription(financeHDRS.getSr_St_Desc());

        if(financeHDRS.getIncomeStat() != null) {
            IncomeStatData incomeStatData = new IncomeStatData();
            incomeStatData.setPartner(financeHDRS.getIncomeStat().getPARTNER());
            incomeStatData.setFinancialYear(financeHDRS.getIncomeStat().getFINANCIAL_YEAR());
            incomeStatData.setRevenue(createBigDecimalIfNotNull(financeHDRS.getIncomeStat().getREVENUE()));
            incomeStatData.setSalesCost(createBigDecimalIfNotNull(financeHDRS.getIncomeStat().getSALES_COST()));
            incomeStatData.setGrossIncome(createBigDecimalIfNotNull(financeHDRS.getIncomeStat().getGROSS_INCOME()));
            incomeStatData.setTotalOperatingCost(createBigDecimalIfNotNull(financeHDRS.getIncomeStat().getTOTAL_OPERATING_COST()));
            incomeStatData.setMainOperatingIncome(createBigDecimalIfNotNull(financeHDRS.getIncomeStat().getMAIN_OPERATION_INCOME()));
            incomeStatData.setTotalOtherIncome(createBigDecimalIfNotNull(financeHDRS.getIncomeStat().getTOTAL_OTHER_INCOME()));
            incomeStatData.setIncomeBeforeZakat(createBigDecimalIfNotNull(financeHDRS.getIncomeStat().getINCOME_BEFORE_ZAKAT()));
            incomeStatData.setZakatAmount(createBigDecimalIfNotNull(financeHDRS.getIncomeStat().getZAKAT_AMOUNT()));
            incomeStatData.setTaxAmount(createBigDecimalIfNotNull(financeHDRS.getIncomeStat().getTAX_AMOUNT()));
            incomeStatData.setNetIncome(createBigDecimalIfNotNull(financeHDRS.getIncomeStat().getNET_INCOME()));
            financialData.setIncomeStat(incomeStatData);
        }

        if(financeHDRS.getBalanceSheet() != null){
            BalanceSheetData balanceSheetData = new BalanceSheetData();
            balanceSheetData.setPartner(financeHDRS.getBalanceSheet().getPARTNER());
            balanceSheetData.setFinancialYear(financeHDRS.getBalanceSheet().getFINANCIAL_YEAR());
            balanceSheetData.setCurrentAssets(createBigDecimalIfNotNull(financeHDRS.getBalanceSheet().getCURRENT_ASSETS()));
            balanceSheetData.setNonCurrentAssets(createBigDecimalIfNotNull(financeHDRS.getBalanceSheet().getNON_CURR_ASSETS()));
            balanceSheetData.setOtherAssets(createBigDecimalIfNotNull(financeHDRS.getBalanceSheet().getOTHER_ASSETS()));
            balanceSheetData.setTotalAssets(createBigDecimalIfNotNull(financeHDRS.getBalanceSheet().getTOTAL_ASSETS()));
            balanceSheetData.setCurrentLiabilities(createBigDecimalIfNotNull(financeHDRS.getBalanceSheet().getCURRENT_LIABILITES()));
            balanceSheetData.setNonCurrentLiabilities(createBigDecimalIfNotNull(financeHDRS.getBalanceSheet().getNON_CURR_LIABILITIES()));
            balanceSheetData.setOtherLiabilities(createBigDecimalIfNotNull(financeHDRS.getBalanceSheet().getOTHER_LIABILITES()));
            balanceSheetData.setTotalLiabilities(createBigDecimalIfNotNull(financeHDRS.getBalanceSheet().getTOTAL_LIABILITIES()));
            balanceSheetData.setCapital(createBigDecimalIfNotNull(financeHDRS.getBalanceSheet().getCAPITAL()));
            balanceSheetData.setPartnersDrawAccs(financeHDRS.getBalanceSheet().getPARTNERS_DRAW_ACCS());
            balanceSheetData.setRetainedEarnings(createBigDecimalIfNotNull(financeHDRS.getBalanceSheet().getRETAINED_EARNINGS()));
            balanceSheetData.setOthers(financeHDRS.getBalanceSheet().getOTHERS());
            balanceSheetData.setShareholdersEquity(financeHDRS.getBalanceSheet().getSHAREHOLDERS_EQUITY());
            financialData.setBalanceSheet(balanceSheetData);
        }

        if(financeHDRS.getChangeEquity() != null){
            ChangeEquityData changeEquityData = new ChangeEquityData();
            changeEquityData.setPartner(financeHDRS.getChangeEquity().getPARTNER());
            changeEquityData.setFinancialYear(financeHDRS.getChangeEquity().getFINANCIAL_YEAR());
            changeEquityData.setRetainedEarningsBf(createBigDecimalIfNotNull(financeHDRS.getChangeEquity().getRET_EARNINGS_BF()));
            changeEquityData.setRetainedEarningsAf(createBigDecimalIfNotNull(financeHDRS.getChangeEquity().getRET_EARNINGS_AF()));
            changeEquityData.setTotalBalanceBf(createBigDecimalIfNotNull(financeHDRS.getChangeEquity().getTOT_BALANCE_BF()));
            changeEquityData.setTotalBalanceAf(createBigDecimalIfNotNull(financeHDRS.getChangeEquity().getTOT_BALANCE_AF()));
            financialData.setChangeEquity(changeEquityData);
        }

        if(financeHDRS.getFinanceContents() != null){
            List<FinanceContentData> financeContentDatas = new ArrayList<>();
            for(FinanceContent financeContent : financeHDRS.getFinanceContents()){
                FinanceContentData financeContentData = new FinanceContentData();
                financeContentData.setContentType(financeContent.getContentType());
                financeContentData.setObjectGuid(financeContent.getObjectGuid());
                financeContentData.setFilename(financeContent.getFilename());
                financeContentData.setFilesize(financeContent.getFilesize());
                financeContentData.setMimetype(financeContent.getMimetype());
                financeContentData.setDocumentId(financeContent.getDocumentID());
                financeContentData.setCreatedBy(financeContent.getCrtdby());
                financeContentData.setCreatedOn(sagiaFormatProvider.getLocalizedDateData(financeContent.getCrtdon()));
                financeContentData.setDocKeyId(financeContent.getDocKeyID());
                financeContentData.setShDocumentId(financeContent.getShDocID());
                financeContentData.setStage(financeContent.getStage());
                financeContentDatas.add(financeContentData);
            }
            financialData.setFinanceContents(financeContentDatas);
            
        }
    }

    /**
     * creates BigDecimalIfNotNull
     * @param bigDecimal bigDecimal
     * @return
     */
    public BigDecimal createBigDecimalIfNotNull(String bigDecimal) {
        if(bigDecimal!= null && !bigDecimal.isEmpty()){
            return new BigDecimal(bigDecimal);
        }

        return null;
    }

    /**
     * @return SagiaFormatProvider
     */
    public SagiaFormatProvider getSagiaFormatProvider()
    {
        return sagiaFormatProvider;
    }

    /**
     * sets SagiaFormatProvider
     * @param sagiaFormatProvider sagiaFormatProvider
     */
    public void setSagiaFormatProvider(final SagiaFormatProvider sagiaFormatProvider)
    {
        this.sagiaFormatProvider = sagiaFormatProvider;
    }
}
