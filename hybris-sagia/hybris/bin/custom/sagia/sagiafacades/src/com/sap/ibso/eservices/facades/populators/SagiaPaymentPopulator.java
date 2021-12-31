package com.sap.ibso.eservices.facades.populators;

import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Arrays;
import java.util.Locale;

import org.apache.commons.lang.StringUtils;

import com.sap.ibso.eservices.core.sagia.services.SagiaFormatProvider;
import com.sap.ibso.eservices.facades.data.payment.PaymentData;
import com.sap.ibso.eservices.sagiaservices.data.payment.SalesOrderPayment;
import com.sap.ibso.eservices.sagiaservices.i18n.I18NMessageTranslatorService;

import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

/*
 * Suppress sonar warning (squid:RedundantThrowsDeclarationCheck | "throws" declarations should not be superfluous
 * Marker exception needed even if is a RuntimeException
 */
@SuppressWarnings("squid:RedundantThrowsDeclarationCheck")
public class SagiaPaymentPopulator implements Populator<SalesOrderPayment, PaymentData> {

    private String[] errorStatuses = {"E0021", "E0022", "E0030", "E0037", "E0042", "E0047", "E0052", "E0059", "E0063", "E0064"};
    private String[] pendingStatuses = {"E0001", "E0002", "E0003", "E0004", "E0005", "E0020", "E0023", "E0024", "E0025", "E0026",
            "E0028", "E0029", "E0035", "E0036", "E0038", "E0039", "E0040", "E0041", "E0043", "E0044", "E0045", "E0046", "E0048", "E0049", "E0050",
            "E0051", "E0053", "E0054", "E0055", "E0056"};
    private String[] doneStatuses = {"E0031", "E0032", "E0034", "E0058"};

    public static final String ICON_ERROR = "ERROR";
    public static final String ICON_PENDING = "PENDING";
    public static final String ICON_DONE = "DONE";

    private SagiaFormatProvider sagiaFormatProvider;
    private I18NMessageTranslatorService i18NMessageTranslatorService;

    @Override
    public void populate(SalesOrderPayment salesOrderPayment, PaymentData paymentData) throws ConversionException {
        paymentData.setName(salesOrderPayment.getServiceDesc());
        paymentData.setAmount(salesOrderPayment.getNetValue());
        paymentData.setNumberOfDays(salesOrderPayment.getNumOfDays());
        paymentData.setSagiaPaymentDate(sagiaFormatProvider.getLocalizedDateTimeData(salesOrderPayment.getPaymentDate()));
        paymentData.setStatus(salesOrderPayment.getPaymentStatus());
        paymentData.setStatusDescription(salesOrderPayment.getPaymentStatusDesc());
        paymentData.setStatusDescriptionKey(getStatusDescriptionKeyFor(salesOrderPayment.getPaymentStatusDesc()));
        paymentData.setPaymentMonth(salesOrderPayment.getPaymentDate().getMonth().getDisplayName(TextStyle.SHORT, Locale.getDefault()));
        paymentData.setPaymentDay(String.valueOf(salesOrderPayment.getPaymentDate().getDayOfMonth()));
        paymentData.setServiceId(salesOrderPayment.getServiceId());
        paymentData.setEntityAddress(buildEntityAddress(salesOrderPayment));
        paymentData.setEmployeeResponse(salesOrderPayment.getEmpFirstName() + " " + salesOrderPayment.getEmpLastName());
        paymentData.setCurrency(salesOrderPayment.getCurrency());
        paymentData.setServiceDescription(salesOrderPayment.getServiceDesc());
        paymentData.setJavaPaymentDate(salesOrderPayment.getPaymentDate());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        paymentData.setFormattedPaymentDate(salesOrderPayment.getPaymentDate().format(formatter));

        paymentData.setSadadBillStatus(salesOrderPayment.getSadadBillStatus());
        paymentData.setSadadTransNum(salesOrderPayment.getSadadTransNum());
        paymentData.setSadadTransDate(sagiaFormatProvider.getShortDateFromUTC(salesOrderPayment.getSadadTransDate()));
        paymentData.setSadadBillAccount(salesOrderPayment.getSadadBillAccount());
        paymentData.setSadadBankId(salesOrderPayment.getSadadBankId());
        paymentData.setSadadInvestorId(salesOrderPayment.getSadadInvestorId());
        paymentData.setSadadAmountPaid(salesOrderPayment.getSadadAmountPaid());
        paymentData.setSadadPaymentMethod(salesOrderPayment.getSadadPaymentMethod());
        paymentData.setSadadPaymentNum(salesOrderPayment.getSadadPaymentNum());
        paymentData.setSadadBillNum(salesOrderPayment.getSadadBillNum());
        paymentData.setSadadPaymentChannel(salesOrderPayment.getSadadPaymentChannel());
        
        if (Arrays.stream(errorStatuses).anyMatch(s -> s.equals(salesOrderPayment.getPaymentStatus()))) {
            paymentData.setStatusIcon(ICON_ERROR);
            return;
        }

        if (Arrays.stream(pendingStatuses).anyMatch(s -> s.equals(salesOrderPayment.getPaymentStatus()))) {
            paymentData.setStatusIcon(ICON_PENDING);
            return;
        }

        if (Arrays.stream(doneStatuses).anyMatch(s -> s.equals(salesOrderPayment.getPaymentStatus()))) {
            paymentData.setStatusIcon(ICON_DONE);
        }
    }

    /**
     * @param salesOrderPayment
     * @return
     */
    public String buildEntityAddress(SalesOrderPayment salesOrderPayment) {
        StringBuilder stringBuilder = new StringBuilder();
        appendNotEmpty(stringBuilder, salesOrderPayment.getHouseNum(), false);
        appendNotEmpty(stringBuilder, salesOrderPayment.getStreet(), false);
        appendNotEmpty(stringBuilder, salesOrderPayment.getCity(), false);
        appendNotEmpty(stringBuilder, salesOrderPayment.getPostlCod1(), true);

        return stringBuilder.toString();
    }
    
    /**
     * @param currentStatus
     * @return status for each payment data, in the predefined keys
     */
    private String getStatusDescriptionKeyFor(String currentStatus) {
    		String paidStatusKey = "Paid";
    		String awaitingPaymentKey = "Awaiting Payment";
    		String completedStatusKey = "Completed";
    		if(StringUtils.equalsIgnoreCase(currentStatus, getMessageValue("payments.status.paid"))) {
    			return paidStatusKey;
    		}
    		if(StringUtils.equalsIgnoreCase(currentStatus, getMessageValue("payments.status.awaiting"))) {
    			return awaitingPaymentKey;
    		}
    		if(StringUtils.equalsIgnoreCase(currentStatus, getMessageValue("payments.status.completed"))) {
    			return completedStatusKey;
    		}
    		return currentStatus;
    }
    
    private String getMessageValue(String messageKey) {
    		return i18NMessageTranslatorService.getLocalizedMessageValue(messageKey);
    }
    
    /**
     * @param stringBuilder
     * @param value
     * @param last
     */
    public void appendNotEmpty(StringBuilder stringBuilder, String value, boolean last) {
        stringBuilder.append(!StringUtils.isEmpty(value) ? last ? value : value + ", " : "");
    }

    /**
     * @param sagiaFormatProvider
     */
    public void setSagiaFormatProvider(SagiaFormatProvider sagiaFormatProvider) {
        this.sagiaFormatProvider = sagiaFormatProvider;
    }

    /**
     * @return
     */
    public SagiaFormatProvider getSagiaFormatProvider() {
        return sagiaFormatProvider;
    }
    
    public void setI18NMessageTranslatorService(I18NMessageTranslatorService i18NMessageTranslatorService) {
        this.i18NMessageTranslatorService = i18NMessageTranslatorService;
    }
}
