package com.sap.ibso.eservices.sagiaservices.services.license.payment;

import com.sap.ibso.eservices.sagiaservices.data.payment.PaymentDetailsData;
import com.sap.ibso.eservices.sagiaservices.data.payment.PaymentDetailsItemData;
import com.sap.ibso.eservices.sagiaservices.data.price.PriceSimulationData;
import com.sap.ibso.eservices.sagiaservices.data.price.PriceSimulationItemData;
import com.sap.ibso.eservices.sagiaservices.price.PriceSimulationService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * LicensePaymentService
 */
public class LicensePaymentService {

    @Autowired
    PriceSimulationService priceSimulationService;

    /**

     * @return - Payment details data containing requested information about license payment.
     */
    /**
     * The service that is interrogated when applying, amending or renewing a license.
     * It returns the payment details of the license.
     * @param serviceType service type for which the interrogation is made.
     * @param qeemah qeemah for which the interrogation is made.
     * @return payment simulation POJO
     */
    public PaymentDetailsData requestPaymentDetails(String serviceType, String qeemah){
        PriceSimulationData priceData;

        if(Strings.isNotEmpty(qeemah)){
            priceData = priceSimulationService.getPriceSimulationData(serviceType,qeemah);
        }
        else{
            priceData = priceSimulationService.getPriceSimulationData(serviceType);
        }

        PaymentDetailsData paymentDetailsData = new PaymentDetailsData();
        List<PaymentDetailsItemData> paymentDetailsItemDataList = new ArrayList<>();
        BigDecimal total = BigDecimal.ZERO;
        for (PriceSimulationItemData item:
             priceData.getPriceSimulationItems()) {
            PaymentDetailsItemData paymentItem = new PaymentDetailsItemData();
            paymentItem.setNetValue(item.getNetValue().toString());
            paymentItem.setServiceName(item.getServiceName());
            paymentDetailsItemDataList.add(paymentItem);
            total = total.add(item.getNetValue());
        }
        paymentDetailsData.setItems(paymentDetailsItemDataList);
        paymentDetailsData.setTotal(total.toString());
        paymentDetailsData.setCurrency(priceData.getPriceSimulationItems().get(0).getCurrencyIso());

//        PaymentDetailsData paymentDetailsData = new PaymentDetailsData();
//        List<PaymentDetailsItemData> paymentDetailsItemDataList = new ArrayList<>();
//        PaymentDetailsItemData p1 = new PaymentDetailsItemData();
//        PaymentDetailsItemData p2 = new PaymentDetailsItemData();
//        p1.setServiceName("test1");p1.setNetValue("100");
//        p2.setServiceName("test2");p2.setNetValue("200");
//        paymentDetailsItemDataList.add(p1);
//        paymentDetailsItemDataList.add(p2);
//
//        paymentDetailsData.setItems(paymentDetailsItemDataList);
//        paymentDetailsData.setTotal("300");
//        paymentDetailsData.setCurrency("SAR");

        return paymentDetailsData;
    }
}
