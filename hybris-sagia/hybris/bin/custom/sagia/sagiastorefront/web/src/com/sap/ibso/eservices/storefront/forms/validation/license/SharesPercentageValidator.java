package com.sap.ibso.eservices.storefront.forms.validation.license;


import com.sap.ibso.eservices.facades.data.zqeemah.ExistingShareholderInfo;
import com.sap.ibso.eservices.facades.data.zqeemah.ShareholderInfo;
import com.sap.ibso.eservices.facades.data.zqeemah2.Shareholder;
import com.sap.ibso.eservices.storefront.forms.validation.SagiaValidationUtil;
import de.hybris.platform.servicelayer.i18n.I18NService;
import org.apache.commons.lang.StringUtils;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component("sharesPercentageValidator")
public class SharesPercentageValidator {

    @Resource(name = "baseMessageSource")
    private MessageSource messageSource;

    @Resource(name="i18NService")
    private I18NService i18NService;

    public void validateNewShareholderPercentageQM2(Shareholder shareholder, Map<String, String> errors, List<Shareholder> shareholdersList) {
       if (SagiaValidationUtil.isPercentage(shareholder.getSharesPercentage()) && getShareholdersPercentageSumQM2 (shareholder,shareholdersList) > 100) {
            errors.put("sharesPercentage", messageSource.getMessage("validation.shareholder.sharesPercentage.amount", null, i18NService.getCurrentLocale()));
        }
    }

    public boolean validTotalShareholdersPercentageQM2(List<Shareholder> shareholdersList) {
       return getShareholdersPercentageSumQM2(null, shareholdersList) == 100;
    }

    public void validateShareholderPercentageQM1(List<ExistingShareholderInfo> existingShareholders, List<ShareholderInfo> shareholders,Map<String, String> errors, String property){
       if (getShareholdersPercentageSumQM1(existingShareholders,shareholders) > 100) {
           errors.put(property, messageSource.getMessage("validation.shareholder.sharesPercentage.amount", null, i18NService.getCurrentLocale()));
       }
    }

    public boolean validTotalShareholdersPercentageQM1(List<ExistingShareholderInfo> existingShareholders, List<ShareholderInfo> shareholders) {
        return getShareholdersPercentageSumQM1(existingShareholders,shareholders) == 100;
    }

    private Double getShareholdersPercentageSumQM2(Shareholder shareholder, List<Shareholder> shareholderList){
        double shareholdersPercentageSum = (shareholder != null ? Double.parseDouble(shareholder.getSharesPercentage()) : 0);
        shareholdersPercentageSum += shareholderList.stream().mapToDouble(s -> Double.parseDouble(s.getSharesPercentage())).sum();
        return shareholdersPercentageSum;
    }

    private Double getShareholdersPercentageSumQM1(List<ExistingShareholderInfo> existingShareholders, List<ShareholderInfo> shareholders){
        List<ExistingShareholderInfo> validExistingShareholdersList = existingShareholders.stream().filter(eS -> SagiaValidationUtil.isPercentage(eS.getSharesPercentage())).collect(Collectors.toList());
        List<ShareholderInfo> validShareholdersList = shareholders.stream().filter(s -> SagiaValidationUtil.isPercentage(s.getSharesPercentage()!= null ? s.getSharesPercentage() : s.getCompanySharesPercentage())).collect(Collectors.toList());

        Double existingShareholdersPercentageSum = validExistingShareholdersList.stream().mapToDouble(s -> Double.parseDouble(s.getSharesPercentage())).sum();
        Double shareholdersPercentageSum = validShareholdersList.stream().mapToDouble(s -> Double.parseDouble((s.getSharesPercentage()!= null ? s.getSharesPercentage() : s.getCompanySharesPercentage()))).sum();
        return existingShareholdersPercentageSum + shareholdersPercentageSum;
    }
}



