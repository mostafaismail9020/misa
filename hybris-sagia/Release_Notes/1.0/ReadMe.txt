
This build contian below Major changes

#Qeemah determination is based on ISIC activites selection
#Shareholder have option to add delegate information
#Show Terms and condition for apply for new license and for each ISIC activites selection
#Support Temporary License
#Remove Product Section from the Apply for new License flow


Action Item for this relase
============================
Note: You can find all the impex mentioed below in the same release folder

1) Make the below entry in the local.properties file before starting the server
nic.webservice.url = https://rhoysrprd1/NIC_GetService/API.asmx

2) Update the sytem with only "Update running system" option 
   This will update DB with the new model changes

3) Run the sap-formats.impex find in this release folder, 
   This will add Umm-Al-Qura date formate

4) Run the certificate-requirement-data_ar.impex & certificate-requirement-data_en.impex, 
   This will enable a term and condition popup while selecting activites.

5) Run the SagiaCMSParagraphDashBoardNoLicenseParagraphComponent.impex and sync sagia-cms-dashboard-no-license wcms page
	This will update AR version of the no license dashboard and open the terms condition popup on click of apply for new license button

6) Run the applyNewLicenseTermsAndCondition.impex and sync sagia-cms-TandC-applyNewLicense cms page
	This will store T&C content for Apply new License