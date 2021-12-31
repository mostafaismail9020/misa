package com.sap.ibso.eservices.storefront.forms.validation.license.contact;

import com.sap.ibso.eservices.core.sagia.services.SagiaFormatProvider;
import com.sap.ibso.eservices.facades.data.ContactPersonsData;

import com.sap.ibso.eservices.storefront.util.SagiaLicenseFieldsUtils;
import de.hybris.platform.acceleratorstorefrontcommons.util.XSSFilterUtil;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.chrono.HijrahChronology;
import java.util.Map;

import static com.sap.ibso.eservices.storefront.forms.validation.SagiaValidationUtil.isEmail;

@Component("contactPersonValidator")
public class ContactPersonValidator implements Validator {

    @Resource(name = "sagiaFormatProvider")
    SagiaFormatProvider sagiaFormatProvider;

    @Resource(name= "sagiaLicenseFieldsUtils")
    SagiaLicenseFieldsUtils sagiaLicenseFieldsUtils;
    
    @Override
    public boolean supports(Class<?> aClass) {
        return ContactPersonsData.class.equals(aClass);
    }

    @Override
    public void validate(Object object, Errors errors) {
        
        final ContactPersonsData contactPersonsData = (ContactPersonsData)object;

        contactPersonsData.setTitle(XSSFilterUtil.filter(contactPersonsData.getTitle()));
        if(StringUtils.isEmpty(contactPersonsData.getTitle())){
            errors.rejectValue("title","validation.contactPerson.title");
        }

        contactPersonsData.setFirstName(XSSFilterUtil.filter(contactPersonsData.getFirstName()));
        String firstName = contactPersonsData.getFirstName();
        if(StringUtils.isEmpty(firstName) || !sagiaLicenseFieldsUtils.firstNameCheck(firstName)){
            errors.rejectValue("firstName","validation.contactPerson.firstName");
        }
        
        contactPersonsData.setLastName(XSSFilterUtil.filter(contactPersonsData.getLastName()));
        String lastName = contactPersonsData.getLastName();
        if(StringUtils.isEmpty(lastName) || !sagiaLicenseFieldsUtils.lastNameCheck(lastName)){
            errors.rejectValue("lastName","validation.contactPerson.lastName");
        }
        
        contactPersonsData.setRole(XSSFilterUtil.filter(contactPersonsData.getRole()));
        if(StringUtils.isEmpty(contactPersonsData.getRole())){
            errors.rejectValue("role","validation.contactPerson.role");
        }
        
        contactPersonsData.setEducation(XSSFilterUtil.filter(contactPersonsData.getEducation()));
        if(StringUtils.isEmpty(contactPersonsData.getEducation())){
            errors.rejectValue("education","validation.contactPerson.education");
        }

        contactPersonsData.setPassportNumber(XSSFilterUtil.filter(contactPersonsData.getPassportNumber()));
        String passportNumber = contactPersonsData.getPassportNumber();
        if(StringUtils.isEmpty(passportNumber) || !sagiaLicenseFieldsUtils.passportNumberCheck(passportNumber)){
            errors.rejectValue("passportNumber","validation.contactPerson.passportNumber");
        }
        
        boolean isUMQDate = false ;
        if ("OTHER".equalsIgnoreCase(contactPersonsData.getContacts()) &&  "1".equals(contactPersonsData.getIdType())){
        	isUMQDate = true;
        }

        contactPersonsData.setDateOfBirth(XSSFilterUtil.filter(contactPersonsData.getDateOfBirth()));
        if (StringUtils.isEmpty(contactPersonsData.getDateOfBirth())) {
            errors.rejectValue("dateOfBirth", "validation.contactPerson.dateOfBirth");
        } else if (!isDateInThePast(contactPersonsData.getDateOfBirth(),isUMQDate)){
            errors.rejectValue("dateOfBirth", "validation.contactPerson.dateOfBirth");
        }

        contactPersonsData.setPassportIssueDate(XSSFilterUtil.filter(contactPersonsData.getPassportIssueDate()));
        if (StringUtils.isEmpty(contactPersonsData.getPassportIssueDate())) {
            errors.rejectValue("passportIssueDate", "validation.contactPerson.passportIssueDate");
        } else if (!isDateInThePast(contactPersonsData.getPassportIssueDate(),false)){
            errors.rejectValue("passportIssueDate", "validation.contactPerson.passportIssueDate");
        }

        contactPersonsData.setPassportExpiryDate(XSSFilterUtil.filter(contactPersonsData.getPassportExpiryDate()));
        if (StringUtils.isEmpty(contactPersonsData.getPassportExpiryDate())) {
            errors.rejectValue("passportExpiryDate", "validation.contactPerson.passportExpiryDate");
        } else if (!isDateInTheFuture(contactPersonsData.getPassportExpiryDate(),false)){
            errors.rejectValue("passportExpiryDate", "validation.contactPerson.passportExpiryDate");
        }

        contactPersonsData.setCountry(XSSFilterUtil.filter(contactPersonsData.getCountry()));                             
        if(StringUtils.isEmpty(contactPersonsData.getCountry())){
            errors.rejectValue("country","validation.contactPerson.country");
        }

        contactPersonsData.setCity(XSSFilterUtil.filter(contactPersonsData.getCity()));                                     
        String city = contactPersonsData.getCity();
        if(StringUtils.isEmpty(city) || !sagiaLicenseFieldsUtils.cityCheck(city)){
            errors.rejectValue("city","validation.contactPerson.city");
        }
        
        contactPersonsData.setAddress(XSSFilterUtil.filter(contactPersonsData.getAddress()));                                             
        String address = contactPersonsData.getAddress();
        if(StringUtils.isEmpty(address) || !sagiaLicenseFieldsUtils.addressCheck(address)){
            errors.rejectValue("address","validation.contactPerson.address");
        }
                                                         
        String poBox = contactPersonsData.getPoBox() == null ? "" : contactPersonsData.getPoBox().toString();
        if(StringUtils.isEmpty(poBox) || !sagiaLicenseFieldsUtils.poBoxCheck(poBox)){
            errors.rejectValue("poBox","validation.contactPerson.poBox");
        }
        
        contactPersonsData.setPostalCode(XSSFilterUtil.filter(contactPersonsData.getPostalCode())); 
        String postalCode = contactPersonsData.getPostalCode();
        if(StringUtils.isEmpty(postalCode) || !sagiaLicenseFieldsUtils.postalCodeCheck(postalCode)) {
            errors.rejectValue("postalCode","validation.contactPerson.postalCode");
        }
        
        contactPersonsData.setTelephoneNumber(XSSFilterUtil.filter(contactPersonsData.getTelephoneNumber()));        
        String telephone = contactPersonsData.getTelephoneNumber();
        if(StringUtils.isEmpty(telephone) || !sagiaLicenseFieldsUtils.telePhoneNumberCheck(telephone)){
            errors.rejectValue("telephoneNumber","validation.contactPerson.telephone");
        }
        
        contactPersonsData.setMobileNumber(XSSFilterUtil.filter(contactPersonsData.getMobileNumber()));               
        String mobileNumber = contactPersonsData.getMobileNumber();
        if(StringUtils.isEmpty(mobileNumber) || !sagiaLicenseFieldsUtils.mobilePhoneNumberCheck(mobileNumber)){
            errors.rejectValue("mobileNumber","validation.contactPerson.mobileNumber");
        }
        
        contactPersonsData.setEmail(XSSFilterUtil.filter(contactPersonsData.getEmail()));                       
        String email = contactPersonsData.getEmail();
        if(StringUtils.isEmpty(email) || !isEmail(email)){
            errors.rejectValue("email","validation.contactPerson.email");
        }
    }

    private boolean isDateInThePast(String value, boolean isUAQDate) {
    
        return dateValidator(value, isUAQDate,  true);
    }
    private boolean isDateInTheFuture(String value, boolean isUAQDate) {
    	
    	 return dateValidator(value, isUAQDate,  false);
    }
    
    
    final boolean dateValidator(String date, boolean isUAQDate, boolean isAfter)
	{
		try
		{
			if(isUAQDate)
			{
				if(isAfter)
				{
					if(sagiaFormatProvider.getLocalDateFromuiUAQDateString(date, HijrahChronology.INSTANCE).isAfter(LocalDate.now()))
					{
						return false;
					}
				}
				else if (sagiaFormatProvider.getLocalDateFromuiUAQDateString(date, HijrahChronology.INSTANCE).isBefore(LocalDate.now()))
				{
					return false;
				}
			}
			else
			{ 
				if(isAfter)
				{
					LocalDate currenDate = sagiaFormatProvider.getDateTimeFromUIDateString(date).toLocalDate();

					if(currenDate.isAfter(LocalDate.now())) {
						return false;
					}
				}
				else if(sagiaFormatProvider.getDateTimeFromUIDateString(date).toLocalDate().isBefore(LocalDate.now())) {
					return false;
				}	
			}
		} catch (Exception e) {
			return false;
		}
		return true;
	}

}
