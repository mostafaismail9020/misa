package com.sap.ibso.eservices.facades.populators;

import com.sap.ibso.eservices.core.sagia.services.SagiaFormatProvider;
import com.sap.ibso.eservices.sagiaservices.data.SagiaGovtServiceInfo;
import com.sap.ibso.eservices.sagiaservices.data.SagiaGovtServiceInfoDataCRM;
import de.hybris.platform.converters.Populator;
import org.apache.commons.lang.StringUtils;

/*
 * Suppress sonar warning (squid:RedundantThrowsDeclarationCheck | "throws" declarations should not be superfluous
 * Marker exception needed even if is a RuntimeException
 */
@SuppressWarnings("squid:RedundantThrowsDeclarationCheck")
public class SagiaGovtInfoDataPopulator implements Populator<SagiaGovtServiceInfoDataCRM, SagiaGovtServiceInfo> {

    private SagiaFormatProvider sagiaFormatProvider;

    /**
     * Populate from SagiaGovtServiceInfoDataCRM to SagiaGovtServiceInfo.
     *
     * @param source the source object
     * @param target the target to populate
     */
    @Override
    public void populate(SagiaGovtServiceInfoDataCRM source, SagiaGovtServiceInfo target) {
        target.setBpID(source.getBpID());
        target.setCrNumber(source.getCrNumber());
        target.setCrIssueDate(formatDate(source.getCrIssueDate()));
        target.setCrExpiryDate(formatDate(source.getCrExpiryDate()));
        target.setZakatNumber(source.getZakatNumber());
        target.setZakatIssueDate(formatDate(source.getZakatIssueDate()));
        target.setZakatExpiryDate(formatDate(source.getZakatExpiryDate()));
        target.setMolNumber(source.getMolNumber());
        target.setMolSize(source.getMolSize());
        target.setMolColor(source.getMolColor());
        target.setSaudiPercent(source.getSaudiPercent());
        target.setCocNumber(source.getCocNumber());
        target.setCocIssue(source.getCocIssue());
        target.setCocExpiry(source.getCocExpiry());
        target.setGosiNumber(source.getGosiNumber());
        target.setGosiIssue(source.getGosiIssue());
        target.setGosiExpiry(source.getGosiExpiry());
        target.setZakatTax(source.getZakatTax());
        target.setZakatAccurrKey(source.getZakatAccurrKey());
        target.setZakatAccPer(source.getZakatAccPer());
        target.setMaAmanah(source.getMaAmanah());
        target.setMaBladiya(source.getMaBladiya());
        target.setMaLicIssueDate(formatDate(source.getMaLicIssueDate()));
        target.setMaLicExpiryDate(formatDate(source.getMaLicExpiryDate()));
        target.setSevenHundredNumber(source.getSevenHundredNumber());
    }

    /**
     * @param date
     * @return
     */
    private String formatDate(String date) {
        return StringUtils.isNotBlank(date) ? sagiaFormatProvider.getLocalizedDateTimeData(Long.parseLong(date)).getDateFormatted() : "";
    }


    /**
     * @param sagiaFormatProvider
     */
    public void setSagiaFormatProvider(SagiaFormatProvider sagiaFormatProvider) {
        this.sagiaFormatProvider = sagiaFormatProvider;
    }
}
