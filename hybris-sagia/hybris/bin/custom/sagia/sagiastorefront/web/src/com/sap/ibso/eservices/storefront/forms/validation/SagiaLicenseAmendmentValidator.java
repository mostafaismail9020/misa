package com.sap.ibso.eservices.storefront.forms.validation;

import com.sap.ibso.eservices.facades.data.license.amendment.*;
import org.springframework.util.StringUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SagiaLicenseAmendmentValidator {

    private static final String REQUIRED_FIELD_ENTITY = "Missing required field in entity";
    private static final String REQUIRED_FIELD_BRANCH = "Missing required field in branch";
    private static final String REQUIRED_FIELD_SHAREHOLDER = "Missing required field in shareholder";
    private static final String REQUIRED_FIELD_PRODUCT = "Missing required field in product";
    private static final String REQUIRED_FIELD_ATTACHMENTS = "Missing required attachment";

    // Validation will never pass for all the garbage data already in CRM; Validate only what's added through hybris sagia
    private static final String ADDED_ACTION = "01";
    private static final String SHAREHOLDER_INDIVIDUAL_TYPE = "1";

    public Set<String> validate(LicenseAmendment licenseAmendment) {
        Set<String> errors = new HashSet<>();
        errors.addAll(validateEntity(licenseAmendment.getEntity()));
        errors.addAll(validateShareholders(licenseAmendment.getShareholders()));
        errors.addAll(validateBranches(licenseAmendment.getBranches()));
        errors.addAll(validateProducts(licenseAmendment.getProducts()));
        errors.addAll(validateAttachments(licenseAmendment.getAttachments()));
        return errors;
    }

    private Set<String> validateEntity(Entity entity) {
        Set<String> errors = new HashSet<>();
        validateRequiredField(entity.getName(), errors, REQUIRED_FIELD_ENTITY);
        validateRequiredField(entity.getNameOld(), errors, REQUIRED_FIELD_ENTITY);
        validateRequiredField(entity.getCapital(), errors, REQUIRED_FIELD_ENTITY);
        validateRequiredField(entity.getCapitalOld(), errors, REQUIRED_FIELD_ENTITY);
        validateRequiredField(entity.getLabour(), errors, REQUIRED_FIELD_ENTITY);
        validateRequiredField(entity.getLabourOld(), errors, REQUIRED_FIELD_ENTITY);
        return errors;
    }

    private Set<String> validateShareholders(List<Shareholder> shareholders) {
        Set<String> errors = new HashSet<>();
        if (shareholders == null || shareholders.isEmpty()) {
            return errors;
        }
        for (Shareholder shareholder : shareholders) {
            if (ADDED_ACTION.equals(shareholder.getAction()) && shareholder.getExistingBp() != null && !shareholder.getExistingBp()) {

                validateRequiredField(shareholder.getPercentage(), errors, REQUIRED_FIELD_SHAREHOLDER);
                validateRequiredField(shareholder.getAddress().getCountry(), errors, REQUIRED_FIELD_SHAREHOLDER);
                validateRequiredField(shareholder.getAddress().getStreet(), errors, REQUIRED_FIELD_SHAREHOLDER);
                validateRequiredField(shareholder.getAddress().getNumber(), errors, REQUIRED_FIELD_SHAREHOLDER);
                validateRequiredField(shareholder.getAddress().getCity(), errors, REQUIRED_FIELD_SHAREHOLDER);
                validateRequiredField(shareholder.getAddress().getZipCode(), errors, REQUIRED_FIELD_SHAREHOLDER);
                validateRequiredField(shareholder.getAddress().getTelephone(), errors, REQUIRED_FIELD_SHAREHOLDER);
                validateRequiredField(shareholder.getAddress().getEmail(), errors, REQUIRED_FIELD_SHAREHOLDER);
                if (SHAREHOLDER_INDIVIDUAL_TYPE.equals(shareholder.getBpType())) { // Individual shareholder
                    validateRequiredField(shareholder.getFirstName(), errors, REQUIRED_FIELD_SHAREHOLDER);
                    validateRequiredField(shareholder.getSecondName(), errors, REQUIRED_FIELD_SHAREHOLDER);
                    validateRequiredField(shareholder.getAcademicTitle(), errors, REQUIRED_FIELD_SHAREHOLDER);
                    validateRequiredField(shareholder.getGender(), errors, REQUIRED_FIELD_SHAREHOLDER);
                    validateRequiredField(shareholder.getGenderDescription(), errors, REQUIRED_FIELD_SHAREHOLDER);
                    validateRequiredField(shareholder.getMaritalStatus(), errors, REQUIRED_FIELD_SHAREHOLDER);
                    validateRequiredField(shareholder.getMaritalStatusDescription(), errors, REQUIRED_FIELD_SHAREHOLDER);
                    validateRequiredField(shareholder.getPremiumResident(), errors, REQUIRED_FIELD_SHAREHOLDER);
                    validateRequiredField(shareholder.getPremiumResidentDescription(), errors, REQUIRED_FIELD_SHAREHOLDER);
                    validateRequiredField(shareholder.getNationalityCurrent(), errors, REQUIRED_FIELD_SHAREHOLDER);
                    validateRequiredField(shareholder.getNationalityCurrentDescription(), errors, REQUIRED_FIELD_SHAREHOLDER);
                    validateRequiredField(shareholder.getNationalityPrevious(), errors, REQUIRED_FIELD_SHAREHOLDER);
                    validateRequiredField(shareholder.getNationalityPreviousDescription(), errors, REQUIRED_FIELD_SHAREHOLDER);
                } else { // Entity shareholder
                    validateRequiredField(shareholder.getFirstName(), errors, REQUIRED_FIELD_SHAREHOLDER);
                    validateRequiredField(shareholder.getEnglishName(), errors, REQUIRED_FIELD_SHAREHOLDER);
                    validateRequiredField(shareholder.getIndustry(), errors, REQUIRED_FIELD_SHAREHOLDER);
                    validateRequiredField(shareholder.getIndustryDescription(), errors, REQUIRED_FIELD_SHAREHOLDER);
                    validateRequiredField(shareholder.getMultinationalCompany(), errors, REQUIRED_FIELD_SHAREHOLDER);
                    validateRequiredField(shareholder.getLegalStatus(), errors, REQUIRED_FIELD_SHAREHOLDER);
                    validateRequiredField(shareholder.getLegalStatusDescription(), errors, REQUIRED_FIELD_SHAREHOLDER);
                    validateRequiredField(shareholder.getCapital(), errors, REQUIRED_FIELD_SHAREHOLDER);
                    validateRequiredField(shareholder.getLabourSize(), errors, REQUIRED_FIELD_SHAREHOLDER);
                }

                if (!errors.isEmpty()) {
                    break;
                }
            }
        }

        return errors;
    }

    private Set<String> validateBranches(List<Branch> branches) {
        Set<String> errors = new HashSet<>();
        if (branches == null || branches.isEmpty()) {
            return errors;
        }
        for (Branch branch : branches) {
            if (ADDED_ACTION.equals(branch.getAction())) {
                validateRequiredField(branch.getName(), errors, REQUIRED_FIELD_BRANCH);
                validateRequiredField(branch.getType(), errors, REQUIRED_FIELD_BRANCH);
                validateRequiredField(branch.getTypeDescription(), errors, REQUIRED_FIELD_BRANCH);
                validateRequiredField(branch.getAddress().getStreet(), errors, REQUIRED_FIELD_BRANCH);
                validateRequiredField(branch.getAddress().getNumber(), errors, REQUIRED_FIELD_BRANCH);
                validateRequiredField(branch.getAddress().getRegion(), errors, REQUIRED_FIELD_BRANCH);
                validateRequiredField(branch.getAddress().getRegionDescription(), errors, REQUIRED_FIELD_BRANCH);
                validateRequiredField(branch.getAddress().getCity(), errors, REQUIRED_FIELD_BRANCH);
                validateRequiredField(branch.getAddress().getCityDescription(), errors, REQUIRED_FIELD_BRANCH);
                validateRequiredField(branch.getAddress().getTelephone(), errors, REQUIRED_FIELD_BRANCH);
                validateRequiredField(branch.getAddress().getEmail(), errors, REQUIRED_FIELD_BRANCH);

                if (!errors.isEmpty()) {
                    break;
                }
            }
        }

        return errors;
    }

    private Set<String> validateProducts(List<Product> products) {
        Set<String> errors = new HashSet<>();
        if (products == null || products.isEmpty()) {
            return errors;
        }
        for (Product product : products) {
            if (ADDED_ACTION.equals(product.getAction())) {
                validateRequiredField(product.getId(), errors, REQUIRED_FIELD_PRODUCT);
                validateRequiredField(product.getDescription(), errors, REQUIRED_FIELD_PRODUCT);
                validateRequiredField(product.getQuantity(), errors, REQUIRED_FIELD_PRODUCT);
                validateRequiredField(product.getUnit(), errors, REQUIRED_FIELD_PRODUCT);
                validateRequiredField(product.getUnitDescription(), errors, REQUIRED_FIELD_PRODUCT);

                if (!errors.isEmpty()) {
                    break;
                }
            }
        }

        return errors;
    }

    private Set<String> validateAttachments(List<LicenseAmendmentAttachment> attachments) {
        Set<String> errors = new HashSet<>();
        if (attachments != null && !attachments.isEmpty()) {
            for (LicenseAmendmentAttachment attachment : attachments) {
                validateRequiredField(attachment.getContent(), errors, REQUIRED_FIELD_ATTACHMENTS);
                validateRequiredField(attachment.getDockeyId(), errors, REQUIRED_FIELD_ATTACHMENTS);

                if (!errors.isEmpty()) {
                    break;
                }
            }
        }
        return errors;
    }

    private void validateRequiredField(String field, Set<String> errors, String message) {
        if (!StringUtils.hasText(field)) {
            errors.add(message);
        }
    }
}
