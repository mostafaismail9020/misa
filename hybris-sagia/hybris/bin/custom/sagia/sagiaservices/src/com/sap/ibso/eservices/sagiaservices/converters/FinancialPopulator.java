package com.sap.ibso.eservices.sagiaservices.converters;

import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.*;
import com.sap.ibso.eservices.sagiaservices.services.ODataModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import org.apache.olingo.odata2.api.ep.entry.ODataEntry;
import org.apache.olingo.odata2.api.ep.feed.ODataFeed;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/*
 * Suppress sonar warning (squid:RedundantThrowsDeclarationCheck | "throws" declarations should not be superfluous
 * Marker exception needed even if is a RuntimeException
 */
@SuppressWarnings("squid:RedundantThrowsDeclarationCheck")
public class FinancialPopulator extends ODataPopulator<FinanceHDRS>
{
    private FinanceContentPopulator financeContentPopulator;
    private ChangeEquityPopulator changeEquityPopulator;
    private IncomeStatPopulator incomeStatPopulator;
    private BalanceSheetPopulator balanceSheetPopulator;

    @Override
    public void populate(ODataModel model, FinanceHDRS financeHDRS) throws ConversionException {
        super.populate(model, financeHDRS,SagiaPropertyNamingStrategy.PASCAL_SNAKE_CASE);

        Map<String, Object> map = model.get();
        ODataEntry incomeStatNav = (ODataEntry) map.get("FinanceHDRToUI5IncomeStatNav");

        if(incomeStatNav != null) {
            IncomeStat incomeStat = new IncomeStat();
            incomeStatPopulator.populate(new ODataModel(incomeStatNav),incomeStat);
            incomeStat.setFINANCE_HDR(financeHDRS);
            financeHDRS.setIncomeStat(incomeStat);
        }

        ODataEntry balanceSheetNav = (ODataEntry) map.get("FinanceHDRToUI5BalanceSheetNav");

        if(balanceSheetNav != null) {
            BalanceSheet balanceSheet = new BalanceSheet();
            balanceSheetPopulator.populate(new ODataModel(balanceSheetNav),balanceSheet);
            balanceSheet.setFINANCE_HDR(financeHDRS);
            financeHDRS.setBalanceSheet(balanceSheet);
        }

        ODataEntry changeEquityNav = (ODataEntry) map.get("FinanceHDRToUI5ChangeEquityNav");
        if(changeEquityNav != null) {
            ChangeEquity changeEquity = new ChangeEquity();
            changeEquityPopulator.populate(new ODataModel(changeEquityNav),changeEquity);
            changeEquity.setFINANCE_HDR(financeHDRS);
            financeHDRS.setChangeEquity(changeEquity);
        }


        ODataFeed financeContentFeed = (ODataFeed) map.get("FinanceHDRToContentNav");
        if(financeContentFeed != null && financeContentFeed.getEntries() != null && !financeContentFeed.getEntries().isEmpty()) {
            List<FinanceContent> financeContents = new ArrayList<>();
            for(ODataEntry oDataEntry : financeContentFeed.getEntries()) {
                FinanceContent financeContent = new FinanceContent();
                financeContentPopulator.populate(new ODataModel(oDataEntry), financeContent);
                financeContent.setFINANCE_HDR(financeHDRS);
                financeContents.add(financeContent);
            }
            financeHDRS.setFinanceContents(financeContents);
        }

    }

    /**
     * @return
     */
    public FinanceContentPopulator getFinanceContentPopulator() {
        return financeContentPopulator;
    }

    /**
     * @param financeContentPopulator
     */
    public void setFinanceContentPopulator(FinanceContentPopulator financeContentPopulator) {
        this.financeContentPopulator = financeContentPopulator;
    }

    /**
     * @return
     */
    public ChangeEquityPopulator getChangeEquityPopulator() {
        return changeEquityPopulator;
    }

    /**
     * @param changeEquityPopulator
     */
    public void setChangeEquityPopulator(ChangeEquityPopulator changeEquityPopulator) {
        this.changeEquityPopulator = changeEquityPopulator;
    }

    /**
     * @return
     */
    public IncomeStatPopulator getIncomeStatPopulator() {
        return incomeStatPopulator;
    }

    /**
     * @param incomeStatPopulator
     */
    public void setIncomeStatPopulator(IncomeStatPopulator incomeStatPopulator) {
        this.incomeStatPopulator = incomeStatPopulator;
    }

    /**
     * @return
     */
    public BalanceSheetPopulator getBalanceSheetPopulator() {
        return balanceSheetPopulator;
    }

    /**
     * @param balanceSheetPopulator
     */
    public void setBalanceSheetPopulator(BalanceSheetPopulator balanceSheetPopulator) {
        this.balanceSheetPopulator = balanceSheetPopulator;
    }
}
