# Welcome to SAGIA Hybris project.

## Repository usage

There are 3 main branches:
- Master - this is the stable version of the project. Must be stable at all times. Merges with development will be performed at the end of each sprint. 
- Development - this is the development branch, that is by definition unstable. Please clone this branch when starting development. Merge requests will only be approved on this branch. 
- QA - branch created specifically for testing. Merges with development will be performed weekly or on request. It is stable enough for testing purposes.

Please create your own branch from development
Request merges into development.
Please assign merge requests to be approved by odai.alokeh@sap.com or cristian.manole@sap.com

Branches should adhere to the following naming convention:
- FEATURE/JIRA_ID for new functionality
- BUGFIX/TICKET_ID for defects
- GENERIC/NAME_INITIALS_#INCREMENT (ie. GENERIC/OA_001) for anything that doesn't fall under feature or bugfix category. 
Best practice should be to create a Jira Task or a Defect and work on that. Use GENERIC branches when it is really unavoidable. 

Merge requests that do not tackle the specific Feature or Bugfix or that do more than that will not be accepted. 
If you find a bug, please report it and create a specific branch for it. 

#### The repository contains only the custom extensions and configuration. 

The hybris distribution name is required. Download the following hybris version:
https://repository.hybris.com/hybris-public-release/de/hybris/platform/hybris-commerce-suite/6.6.0.0/hybris-commerce-suite-6.6.0.0.zip

## Install steps

1. clone the repo to a folder FOLDER_NAME_PLACEHOLDER
2. extract hybris-commerce-suite-2005.01.zip to FOLDER_NAME_PLACEHOLDER and merge the folders when asked
3. configure hybris/config/local.properties using the example one, hybris/config/local.properties.example
4. add 127.0.0.1 sagia.local to /etc/hosts
5. go to /hybris/bin/platform and execute . ./setantenv.sh
6. since the hybris/config folder in the repo doesn’t contain everything a normal config folder would, rename the config folder you cloned, run ant, it will ask you to generate a config folder, accept the defaults and, when ant completes, overwrite the generated config folder with the files from the cloned config folder
7. run ant clean all
8. run ant initialize
9. run ./hybrisserver.sh
10. go to https://sagia.local:9002/sagiastorefront/?site=sagia

