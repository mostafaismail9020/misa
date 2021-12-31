package com.sap.ibso.eservices.bol.price;

/**
 * Defines license amendment backend parameters for a price simulation.
 */
public class AmendmentBackendParam
{
    private boolean shareholderShareRedistribution;
    private boolean shareholderRemoval;
    private boolean shareholderNameChange;
    private boolean shareholderAddition;
    private boolean shareholderPropertyInheritance;
    private boolean branchOpening;
    private boolean branchClosing;
    private boolean productChange;
    private boolean entityNameChange;
    private boolean entityCapitalReduction;
    private boolean entityLaborIncreasing;
    private boolean entityCapitalIncreasing;
    private boolean entityActivityChange;
    private boolean entityConversionToLimitedLiabilityCompany;
    private boolean entityConversionToIndividualLimitedLiabilityCompany;
    private boolean entityMainBranchLocationChange;
    private int branchNumberOfNewBranches;

    /**
     * Checks whether a shareholder amendment scenario is about redistributing shares.
     *
     * @return <code>true</code> if the shareholder amendment scenario is redistributing shares, <code>false</code> otherwise
     */
    public boolean isShareholderShareRedistribution()
    {
        return shareholderShareRedistribution;
    }

    /**
     * Sets a flag whether a shareholder amendment scenario is about redistributing shares.
     *
     * @param shareholderShareRedistribution <code>true</code> if the shareholder amendment scenario is redistributing shares, <code>false</code> otherwise
     */
    public void setShareholderShareRedistribution(boolean shareholderShareRedistribution)
    {
        this.shareholderShareRedistribution = shareholderShareRedistribution;
    }

    /**
     * Checks whether a shareholder amendment scenario is about removing a shareholder.
     *
     * @return <code>true</code> if the shareholder amendment scenario is removing a shareholder, <code>false</code> otherwise
     */
    public boolean isShareholderRemoval()
    {
        return shareholderRemoval;
    }

    /**
     * Sets a flag whether a shareholder amendment scenario is about removing a shareholder.
     *
     * @param shareholderRemoval <code>true</code> if the shareholder amendment scenario is removing a shareholder, <code>false</code> otherwise
     */
    public void setShareholderRemoval(boolean shareholderRemoval)
    {
        this.shareholderRemoval = shareholderRemoval;
    }

    /**
     * Checks whether a shareholder amendment scenario is about adding a shareholder.
     *
     * @return <code>true</code> if the shareholder amendment scenario is adding a shareholder, <code>false</code> otherwise
     */
    public boolean isShareholderAddition()
    {
        return shareholderAddition;
    }

    /**
     * Sets a flag whether a shareholder amendment scenario is about adding a shareholder.
     *
     * @param shareholderAddition <code>true</code> if the shareholder amendment scenario is adding a shareholder, <code>false</code> otherwise
     */
    public void setShareholderAddition(boolean shareholderAddition)
    {
        this.shareholderAddition = shareholderAddition;
    }

    /**
     * Checks whether a shareholder amendment scenario is about shareholder property inheritance.
     *
     * @return <code>true</code> if the shareholder amendment scenario is shareholder property inheritance, <code>false</code> otherwise
     */
    public boolean isShareholderPropertyInheritance()
    {
        return shareholderPropertyInheritance;
    }

    /**
     * Sets a flag whether a shareholder amendment scenario is about shareholder property inheritance.
     *
     * @param shareholderPropertyInheritance <code>true</code> if the shareholder amendment scenario is shareholder property inheritance, <code>false</code> otherwise
     */
    public void setShareholderPropertyInheritance(boolean shareholderPropertyInheritance)
    {
        this.shareholderPropertyInheritance = shareholderPropertyInheritance;
    }

    /**
     * Checks whether a branch amendment scenario is about opening a branch.
     *
     * @return <code>true</code> if the branch amendment scenario is opening a branch, <code>false</code> otherwise
     */
    public boolean isBranchOpening()
    {
        return branchOpening;
    }

    /**
     * Sets a flag whether a branch amendment scenario is about opening a branch.
     *
     * @param branchOpening <code>true</code> if the branch amendment scenario is opening a branch, <code>false</code> otherwise
     */
    public void setBranchOpening(boolean branchOpening)
    {
        this.branchOpening = branchOpening;
    }

    /**
     * Checks whether an entity amendment scenario is about changing the entity name.
     *
     * @return <code>true</code> if the entity amendment scenario is changing the entity name, <code>false</code> otherwise
     */
    public boolean isEntityNameChange()
    {
        return entityNameChange;
    }

    /**
     * Sets a flag whether an entity amendment scenario is about changing the entity name.
     *
     * @param entityNameChange <code>true</code> if the entity amendment scenario is changing the entity name, <code>false</code> otherwise
     */
    public void setEntityNameChange(boolean entityNameChange)
    {
        this.entityNameChange = entityNameChange;
    }

    /**
     * Checks whether an entity amendment scenario is about reducing capital.
     *
     * @return <code>true</code> if the entity amendment scenario is reducing capital, <code>false</code> otherwise
     */
    public boolean isEntityCapitalReduction()
    {
        return entityCapitalReduction;
    }

    /**
     * Sets a flag whether an entity amendment scenario is about reducing capital.
     *
     * @param entityCapitalReduction <code>true</code> if the entity amendment scenario is reducing capital, <code>false</code> otherwise
     */
    public void setEntityCapitalReduction(boolean entityCapitalReduction)
    {
        this.entityCapitalReduction = entityCapitalReduction;
    }

    /**
     * Checks whether an entity amendment scenario is about increasing work force.
     *
     * @return <code>true</code> if the entity amendment scenario is increasing work force, <code>false</code> otherwise
     */
    public boolean isEntityLaborIncreasing()
    {
        return entityLaborIncreasing;
    }

    /**
     * Sets a flag whether an entity amendment scenario is about increasing work force.
     *
     * @param entityLaborIncreasing <code>true</code> if the entity amendment scenario is increasing work force, <code>false</code> otherwise
     */
    public void setEntityLaborIncreasing(boolean entityLaborIncreasing)
    {
        this.entityLaborIncreasing = entityLaborIncreasing;
    }

    /**
     * Checks whether an entity amendment scenario is about increasing capital.
     *
     * @return <code>true</code> if the entity amendment scenario is increasing capital, <code>false</code> otherwise
     */
    public boolean isEntityCapitalIncreasing()
    {
        return entityCapitalIncreasing;
    }

    /**
     * Sets a flag whether an entity amendment scenario is about increasing capital.
     *
     * @param entityCapitalIncreasing <code>true</code> if the entity amendment scenario is increasing capital, <code>false</code> otherwise
     */
    public void setEntityCapitalIncreasing(boolean entityCapitalIncreasing)
    {
        this.entityCapitalIncreasing = entityCapitalIncreasing;
    }

    /**
     * Checks whether an entity amendment scenario is about converting an establishment into a limited liability company.
     *
     * @return <code>true</code> if the entity amendment scenario is converting an establishment into a limited liability company, <code>false</code> otherwise
     */
    public boolean isEntityConversionToLimitedLiabilityCompany()
    {
        return entityConversionToLimitedLiabilityCompany;
    }

    /**
     * Sets a flag whether an entity amendment scenario is about converting an establishment into a limited liability company.
     *
     * @param entityConversionToLimitedLiabilityCompany <code>true</code> if the entity amendment scenario is converting an establishment into a limited liability company, <code>false</code> otherwise
     */
    public void setEntityConversionToLimitedLiabilityCompany(boolean entityConversionToLimitedLiabilityCompany)
    {
        this.entityConversionToLimitedLiabilityCompany = entityConversionToLimitedLiabilityCompany;
    }

    /**
     * Checks whether an entity amendment scenario is about converting an establishment into an individual limited liability company.
     *
     * @return <code>true</code> if the entity amendment scenario is converting an establishment into an individual limited liability company, <code>false</code> otherwise
     */
    public boolean isEntityConversionToIndividualLimitedLiabilityCompany()
    {
        return entityConversionToIndividualLimitedLiabilityCompany;
    }

    /**
     * Sets a flag whether an entity amendment scenario is about converting an establishment into an individual limited liability company.
     *
     * @param entityConversionToIndividualLimitedLiabilityCompany <code>true</code> if the entity amendment scenario is converting an establishment into an individual limited liability company, <code>false</code> otherwise
     */
    public void setEntityConversionToIndividualLimitedLiabilityCompany(boolean entityConversionToIndividualLimitedLiabilityCompany)
    {
        this.entityConversionToIndividualLimitedLiabilityCompany = entityConversionToIndividualLimitedLiabilityCompany;
    }

    /**
     * Gets the number of new branches for a branch amendment scenario.
     *
     * @return the number of new branches
     */
    public int getBranchNumberOfNewBranches()
    {
        return branchNumberOfNewBranches;
    }

    /**
     * Sets the number of new branches for a branch amendment scenario.
     *
     * @param branchNumberOfNewBranches the number of new branches
     */
    public void setBranchNumberOfNewBranches(int branchNumberOfNewBranches)
    {
        this.branchNumberOfNewBranches = branchNumberOfNewBranches;
    }

    /**
     * Checks whether a shareholder amendment scenario is about changing a shareholder name.
     *
     * @return <code>true</code> if the shareholder amendment scenario is changing a shareholder name, <code>false</code> otherwise
     */
    public boolean isShareholderNameChange()
    {
        return shareholderNameChange;
    }

    /**
     * Sets a flag whether a shareholder amendment scenario is about changing a shareholder name.
     *
     * @param shareholderNameChange <code>true</code> if the shareholder amendment scenario is changing a shareholder name, <code>false</code> otherwise
     */
    public void setShareholderNameChange(boolean shareholderNameChange)
    {
        this.shareholderNameChange = shareholderNameChange;
    }

    /**
     * Checks whether a branch amendment scenario is about closing a branch.
     *
     * @return <code>true</code> if the branch amendment scenario is closing a branch, <code>false</code> otherwise
     */
    public boolean isBranchClosing()
    {
        return branchClosing;
    }

    /**
     * Sets a flag whether a branch amendment scenario is about closing a branch.
     *
     * @param branchClosing <code>true</code> if the branch amendment scenario is closing a branch, <code>false</code> otherwise
     */
    public void setBranchClosing(boolean branchClosing)
    {
        this.branchClosing = branchClosing;
    }

    /**
     * Checks whether a product amendment scenario is about adding or modifying products.
     *
     * @return <code>true</code> if the product amendment scenario is adding or modifying products, <code>false</code> otherwise
     */
    public boolean isProductChange()
    {
        return productChange;
    }

    /**
     * Sets a flag whether a product amendment scenario is about adding or modifying products.
     *
     * @param productChange <code>true</code> if the product amendment scenario is adding or modifying products, <code>false</code> otherwise
     */
    public void setProductChange(boolean productChange)
    {
        this.productChange = productChange;
    }

    /**
     * Checks whether an entity amendment scenario is about adding or modifying activities of a company.
     *
     * @return <code>true</code> if the entity amendment scenario is adding or modifying activities of a company, <code>false</code> otherwise
     */
    public boolean isEntityActivityChange()
    {
        return entityActivityChange;
    }

    /**
     * Sets a flag whether an entity amendment scenario is about adding or modifying activities of a company.
     *
     * @param entityActivityChange <code>true</code> if the entity amendment scenario is adding or modifying activities of a company, <code>false</code> otherwise
     */
    public void setEntityActivityChange(boolean entityActivityChange)
    {
        this.entityActivityChange = entityActivityChange;
    }

    /**
     * Checks whether an entity amendment scenario is about changing the main branch location.
     *
     * @return <code>true</code> if the entity amendment scenario is changing the main branch location, <code>false</code> otherwise
     */
    public boolean isEntityMainBranchLocationChange()
    {
        return entityMainBranchLocationChange;
    }

    /**
     * Sets a flag whether an entity amendment scenario is about changing the main branch location.
     *
     * @param entityMainBranchLocationChange <code>true</code> if the entity amendment scenario is changing the main branch location, <code>false</code> otherwise
     */
    public void setEntityMainBranchLocationChange(boolean entityMainBranchLocationChange)
    {
        this.entityMainBranchLocationChange = entityMainBranchLocationChange;
    }
}
