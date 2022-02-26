package com.sap.ibso.eservices.facades.sagia.impl;

import com.sap.ibso.eservices.core.model.IsicMasterModel;
import com.sap.ibso.eservices.core.model.IsicTextsModel;
import com.sap.ibso.eservices.facades.data.zesrvEnhOData.*;
import com.sap.ibso.eservices.facades.data.zqeemah2.ISICDetails;
import com.sap.ibso.eservices.facades.populators.isic.*;
import com.sap.ibso.eservices.facades.sagia.SagiaIsicFacade;
import com.sap.ibso.eservices.sagiaservices.exception.SagiaODataException;
import com.sap.ibso.eservices.sagiaservices.services.isic.*;
import de.hybris.platform.servicelayer.i18n.I18NService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * DefaultSagiaIsicFacade
 */
public class DefaultSagiaIsicFacade implements SagiaIsicFacade {

    private static final Logger LOG = Logger.getLogger(DefaultSagiaIsicFacade.class);

    private SectionService sectionService;
    private DivisionService divisionService;
    private GroupService groupService;
    private ClassService classService;
    private BranchService branchService;
    private ActivityService activityService;

    private IsicMasterDataService isicMasterDataService;

    private SectionPopulator sectionPopulator;
    private DivisionPopulator divisionPopulator;
    private GroupPopulator groupPopulator;
    private ClassPopulator classPopulator;
    private BranchPopulator branchPopulator;
    private ActivityPopulator activityPopulator;

    private I18NService i18NService;


    @Override
    public Map<String, List> getIsic(String licenseType) {

        Collection<IsicMasterModel> isicMasterModels =  isicMasterDataService.getAllIsicMasterByLicenseType(licenseType);

        return getIsicDetails(isicMasterModels);
    }

    @Override
    public Map<String, List> getAllIsic() {

        Collection<IsicMasterModel> isicMasterModels =  isicMasterDataService.getAllIsicMasterForAllLicenseType();

        return getIsicDetails(isicMasterModels);
    }


    @Override
	public Map<String, List> getIsicDetails(Collection<IsicMasterModel> isicMasterModels) {
		Map<String, List> result = new HashMap<>();
    	if(CollectionUtils.isNotEmpty(isicMasterModels))
    	{
			addSections(result, isicMasterModels);
	        addDivisions(result, isicMasterModels);
	        addGroups(result, isicMasterModels);
	        addClasses(result, isicMasterModels);
	        addBranches(result, isicMasterModels);
	        addActivities(result, isicMasterModels);
    	}
		return result;
	}

    @Override
	public List<ISICDetails> getActiveISICSection() {
    	List<IsicTextsModel> sections = isicMasterDataService.getActiveISICSection();
        List<ISICDetails> sectionsView = new ArrayList<>();
        if (sections != null && !sections.isEmpty()) {
            sections.forEach(s -> {
                ISICDetails isicView = new ISICDetails();
                isicView.setSectionNumber(s.getCode());
                isicView.setSectionDescription(s.getDescription());
                sectionsView.add(isicView);
            });
        }
    	return sectionsView;
    }

    @Override
 	public List<ISICDetails> getActiveISICDivision(String sectionID) {
     	List<IsicTextsModel> divisions = isicMasterDataService.getActiveISICDivision(sectionID);
        List<ISICDetails> divisionsView = new ArrayList<>();
        if (divisions != null && !divisions.isEmpty()) {
            divisions.forEach(s -> {
                ISICDetails isicView = new ISICDetails();
                isicView.setDivisionNumber(s.getCode());
                isicView.setDivisionDescription(s.getDescription());
                divisionsView.add(isicView);
            });
        }
     	return divisionsView;
     }

    private void addSections(Map<String, List> result, Collection<IsicMasterModel> sectionsData) {
        try {

        	List<String> sectionCodes = new ArrayList<>();
            List<IsicMasterModel> filteredData = sectionsData.stream().filter(distinctByKey(p -> p.getIsicSection())).collect(Collectors.toList());

            List<IsicSection> sections = new ArrayList<>();

            filteredData.forEach(sectionData -> {
                IsicSection section = new IsicSection();
                sectionPopulator.populate(sectionData, section);
                sections.add(section);
                sectionCodes.add(section.getSectionId());
            });

            List<IsicTextsModel> isicTextsList = isicMasterDataService.getTextsDataFromType("SECTION", sectionCodes);

            //IntStream.range(0,sections.size()).forEach(i -> sections.get(i).setDescription(isicTextsList.get(i).getDescription()));

            sections.forEach(section -> {
            	String description = isicTextsList.stream()
                        .filter(x -> section.getSectionId().equals(x.getCode()))
                        .map(IsicTextsModel::getDescription)                        //convert stream to String
                        .findAny()
                        .orElse("");
            	section.setDescription(description);
            });

            result.put("sections", sections);

        } catch (SagiaODataException e) {
            LOG.error(e.getMessage(), e);
        }
    }


    private void addDivisions(Map<String, List> result, Collection<IsicMasterModel> divisionsData) {
        try {
        	List<String> divisionCodes = new ArrayList<>();
            List<IsicMasterModel> filteredData = divisionsData.stream().filter(distinctByKey(p -> p.getIsicDivision())).collect(Collectors.toList());
            List<IsicDivision> divisions = new ArrayList<>();


            filteredData.forEach(divisionData -> {
                IsicDivision division = new IsicDivision();
                divisionPopulator.populate(divisionData, division);
                divisions.add(division);
                divisionCodes.add(divisionData.getIsicDivision());
            });


            List<IsicTextsModel> isicTextsList = isicMasterDataService.getTextsDataFromType("DIVISION", divisionCodes);

            //IntStream.range(0,divisions.size()).forEach(i -> divisions.get(i).setDescription(isicTextsList.get(i).getDescription()));

            divisions.forEach(division -> {
            	String description = isicTextsList.stream()
                        .filter(x -> division.getDivisionId().equals(x.getCode()))
                        .map(IsicTextsModel::getDescription)                        //convert stream to String
                        .findAny()
                        .orElse("");
            	division.setDescription(description);
            });

            result.put("divisions", divisions);

        } catch (SagiaODataException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    private void addGroups(Map<String, List> result, Collection<IsicMasterModel> groupsData) {

        try {
        	List<String> groupCodes = new ArrayList<>();
            List<IsicMasterModel> filteredData = groupsData.stream().filter(distinctByKey(p -> p.getIsicGroup())).collect(Collectors.toList());

            List<IsicGroup> groups = new ArrayList<>();
            filteredData.forEach(groupData -> {
                IsicGroup group = new IsicGroup();
                groupPopulator.populate(groupData, group);
                groups.add(group);
                groupCodes.add(groupData.getIsicGroup());
            });

            List<IsicTextsModel> isicTextsList = isicMasterDataService.getTextsDataFromType("GROUP", groupCodes);

            groups.forEach(group -> {
            	String description = isicTextsList.stream()
                        .filter(x -> group.getGroupId().equals(x.getCode()))
                        .map(IsicTextsModel::getDescription)                        //convert stream to String
                        .findAny()
                        .orElse("");
            	group.setDescription(description);
            });
            //IntStream.range(0,groups.size()).forEach(i -> groups.get(i).setDescription(isicTextsList.get(i).getDescription()));

            result.put("groups", groups);
        } catch (SagiaODataException e) {
            LOG.error(e.getMessage(), e);
        }
    }


    private void addClasses(Map<String, List> result, Collection<IsicMasterModel> classesData) {
        try {
        	List<String> classCodes = new ArrayList<>();
            List<IsicMasterModel> filteredData = classesData.stream().filter(distinctByKey(p -> p.getIsicClass())).collect(Collectors.toList());
            List<IsicClass> classes = new ArrayList<>();

            filteredData.forEach(classData -> {
                IsicClass classs = new IsicClass();
                classPopulator.populate(classData, classs);
                classes.add(classs);
                classCodes.add(classData.getIsicClass());
            });


            List<IsicTextsModel> isicTextsList = isicMasterDataService.getTextsDataFromType("CLASS", classCodes);

            classes.forEach(classobj -> {
            	String description = isicTextsList.stream()
                        .filter(x -> classobj.getClassId().equals(x.getCode()))
                        .map(IsicTextsModel::getDescription)                        //convert stream to String
                        .findAny()
                        .orElse("");
            	classobj.setDescription(description);
            });

            //IntStream.range(0,classes.size()).forEach(i -> classes.get(i).setDescription(isicTextsList.get(i).getDescription()));

            result.put("classes", classes);
        } catch (SagiaODataException e) {
            LOG.error(e.getMessage(), e);
        }
    }


    private void addBranches(Map<String, List> result, Collection<IsicMasterModel> branchesDataFound) {
    	List<String> branchCodes = new ArrayList<>();
        List<IsicMasterModel> filteredData = branchesDataFound.stream().filter(distinctByKey(p -> p.getIsicBranch())).collect(Collectors.toList());

        try {
            List<IsicBranch> branches = new ArrayList<>();
            filteredData.forEach(branchData -> {
                IsicBranch branch = new IsicBranch();
                branchPopulator.populate(branchData, branch);
                branches.add(branch);
                branchCodes.add(branchData.getIsicBranch());
            });


            List<IsicTextsModel> isicTextsList = isicMasterDataService.getTextsDataFromType("BRANCH", branchCodes);

            //IntStream.range(0,branches.size()).forEach(i -> branches.get(i).setDescription(isicTextsList.get(i).getDescription()));

            branches.forEach(branch -> {
            	String description = isicTextsList.stream()
                        .filter(x -> branch.getBranchId().equals(x.getCode()))
                        .map(IsicTextsModel::getDescription)                        //convert stream to String
                        .findAny()
                        .orElse("");
            	branch.setDescription(description);
            });

            result.put("branches", branches);

        } catch (SagiaODataException e) {
            LOG.error(e.getMessage(), e);

        }
    }

    private void addActivities(Map<String, List> result, Collection<IsicMasterModel> filteredData) {

    	List<String> activityCodes = new ArrayList<>();
        try {
            List<IsicActivity> activities = new ArrayList<>();
            filteredData.forEach(activityData -> {
                IsicActivity activity = new IsicActivity();
                activityPopulator.populate(activityData, activity);
                activities.add(activity);
                activityCodes.add(activityData.getIsicActivity());
            });

            List<IsicTextsModel> isicTextsList = isicMasterDataService.getTextsDataFromType("ACTIVITY", activityCodes);

            activities.forEach(activity -> {
            	String description = isicTextsList.stream()
                        .filter(x -> activity.getActivityId().equals(x.getCode()))
                        .map(IsicTextsModel::getDescription)                        //convert stream to String
                        .findAny()
                        .orElse("");
            	activity.setDescription(description);
            });

            result.put("activities", activities);

        } catch (SagiaODataException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    public static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor)
    {
        Map<Object, Boolean> map = new ConcurrentHashMap<>();
        return t -> map.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }


    /**
     * @param sectionService
     */
    public void setSectionService(SectionService sectionService) {
        this.sectionService = sectionService;
    }

    /**
     * @param divisionService
     */
    public void setDivisionService(DivisionService divisionService) {
        this.divisionService = divisionService;
    }

    /**
     * @param groupService
     */
    public void setGroupService(GroupService groupService) {
        this.groupService = groupService;
    }

    /**
     * @param classService
     */
    public void setClassService(ClassService classService) {
        this.classService = classService;
    }

    /**
     * @param branchService
     */
    public void setBranchService(BranchService branchService) {
        this.branchService = branchService;
    }

    /**
     * @param activityService
     */
    public void setActivityService(ActivityService activityService) {
        this.activityService = activityService;
    }

    /**
     * @param sectionPopulator
     */
    public void setSectionPopulator(SectionPopulator sectionPopulator) {
        this.sectionPopulator = sectionPopulator;
    }

    /**
     * @param divisionPopulator
     */
    public void setDivisionPopulator(DivisionPopulator divisionPopulator) {
        this.divisionPopulator = divisionPopulator;
    }

    /**
     * @param groupPopulator
     */
    public void setGroupPopulator(GroupPopulator groupPopulator) {
        this.groupPopulator = groupPopulator;
    }

    /**
     * @param classPopulator
     */
    public void setClassPopulator(ClassPopulator classPopulator) {
        this.classPopulator = classPopulator;
    }

    /**
     * @param branchPopulator
     */
    public void setBranchPopulator(BranchPopulator branchPopulator) {
        this.branchPopulator = branchPopulator;
    }

    /**
     * @param activityPopulator
     */
    public void setActivityPopulator(ActivityPopulator activityPopulator) {
        this.activityPopulator = activityPopulator;
    }

    public void setI18NService(I18NService i18NService) {
        this.i18NService = i18NService;
    }

    public IsicMasterDataService getIsicMasterDataService() {
        return isicMasterDataService;
    }

    public void setIsicMasterDataService(IsicMasterDataService isicMasterDataService) {
        this.isicMasterDataService = isicMasterDataService;
    }
}
