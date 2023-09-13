package com.sap.ibso.eservices.storefront.util;

import com.investsaudi.portal.core.model.*;
import de.hybris.platform.catalog.CatalogVersionService;
import de.hybris.platform.category.CategoryService;
import de.hybris.platform.category.model.CategoryModel;
import de.hybris.platform.core.model.media.MediaModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.config.ConfigurationService;
import de.hybris.platform.servicelayer.media.MediaService;
import de.hybris.platform.util.Config;
import org.apache.commons.io.FileUtils;
import org.apache.pdfbox.multipdf.PDFMergerUtility;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.StandardBarPainter;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.*;
@Component("sagiaPDFChartGenerator")
public class SagiaPDFChartGenerator {

	private static final Logger LOG = LoggerFactory.getLogger(SagiaPDFChartGenerator.class);

	private static final String SAGIA_PRODUCT_CATALOG = "sagiaProductCatalog";

	private static final String CATALOG_VERSION_ONLINE = "Online";

	private static final String CATEGORY_SECTOR_OPPORTUNITY = "sector-opportunities";

	private static final String MEDIA_PDF_FILE_NAME = "opportunity-pdf";

	private static final String CODB_IMAGE = "CODB_image";

	private static final String DATE_TIME_PATTERN = "E, dd MMM yyyy HH:mm:ss z";

	private static final String KSA_TIME_ZONE = "Asia/Riyadh";

	private String title = "Invesment Opportunity Title";
	private String desc = "Opportunity brief description";
	private String sector = "Opportunity Sector";
	private String segment = "Opportunity segment";
	private String electricityTariffs = "48";
	private String productivityAdjustedWages = "3.3";
	private String logisticsPerformanceIndex = "3.2";
	private String constructionCosts = "74";
	private String tags = "List of key words linked to the investment opportunity";
	private String investmentHighlights = "Expected Investment size, Plant capacity, Expected IRR, Payback period, Job Creation, GDP Impact, Location (Region):";
	private String incentivesAndEnablers = "Factors that enable investment in the underlying opportunity such as General Incentive and financing";
	private String valueProposition = "Summary of key differentiators that position KSA as a strategic choice over other regional/global peers";
	private String keyStakeholders = "Government institutions, organizations, and/or authorities that participate or influence the market for the underlying product/service Logos";
	private String rawMaterials = "Raw Materials";
	private String globalTrends = "Latest business developments within the sector/product category";
	private String keyDemandDrivers = "Selected number of factors that will influence future demand for the related product/service";
	private String scalabilityAndLocalization = "Ease of scaling the business across the value chain or into new adjacent products or geographies that would maximize the opportunity’s investment returns and the ability and potential to locally manufacture the product and its components";
	private String importDependency = "An overview of the countries from which Saudi Arabia is importing the product and their value/volume and share in total import";
	private boolean isPrimaryPDF = false;

	private boolean page1 = false;

	@Resource
	private CatalogVersionService catalogVersionService;

	@Resource
	private CategoryService categoryService;

	@Resource(name = "mediaService")
	private MediaService mediaService;

	@Resource(name = "configurationService")
	private ConfigurationService configurationService;

	public File generatePdfFile(ProductModel productModel, List<File> secFiles) throws IOException {
		File fileMerged;
		fileMerged = File.createTempFile("merged_file", ".pdf");
		File outputFile;
		outputFile = File.createTempFile("output_file", ".pdf");

		if (productModel instanceof OpportunityProductModel) {
			OpportunityProductModel opportunity = (OpportunityProductModel) productModel;
			File secondaryConcatenatedFile = null;
			if(Objects.nonNull(secFiles) && !secFiles.isEmpty()) {
				secondaryConcatenatedFile = concatenatePDFs(secFiles, outputFile);
			}

			// loading the primary pdf template
			File primaryPdf = getMedia(Config.getString("opportunity.media.pdf.name", MEDIA_PDF_FILE_NAME), "pdf");

			if(primaryPdf == null && secondaryConcatenatedFile == null) {
				LOG.info("No Primary & Secondary PDF's, Unable to process.");
				return fileMerged;
			}

			mergeFiles(primaryPdf, secondaryConcatenatedFile, fileMerged);
			addContentsToPdf(fileMerged, opportunity);

		}
		return fileMerged;
	}

	private File getMedia(String mediaText, String mediaFormat) throws IOException {
		File templateFile = null;
		LOG.info("Fetching medias from Category " + CATEGORY_SECTOR_OPPORTUNITY);
		List<MediaModel> medias = categoryService.getCategoryForCode(catalogVersionService
						.getCatalogVersion(SAGIA_PRODUCT_CATALOG, CATALOG_VERSION_ONLINE), CATEGORY_SECTOR_OPPORTUNITY)
				.getMedias();

		if(Objects.nonNull(medias)) {
			for(MediaModel media: medias) {
				if(Objects.nonNull(media.getCode()) && media.getCode().equalsIgnoreCase(mediaText)) {
					templateFile = convertMediaToFile(media , mediaFormat);
					break;
				}
			}
		}
		return templateFile;
	}

	public File convertMediaToFile(MediaModel mediaModel, String mediaFormat) throws IOException {
		// Get the file data from the MediaModel
		InputStream inputStream = mediaService.getStreamFromMedia(mediaModel);

		// Create a temporary File object
		File tempFile;

		try {
			tempFile = File.createTempFile("temp_file", "." + mediaFormat);

			// Copy the data from the InputStream to the File
			FileUtils.copyInputStreamToFile(inputStream, tempFile);

		} catch (IOException e) {
			// Handle the exception, if any
			LOG.error("Error while converting MediaModel to file", e);
			throw e;
		}

		return tempFile;
	}

	public File concatenatePDFs(List<File> files, File outputFile) throws IOException {
		LOG.info("Merging Secondary PDF(s)");
		PDFMergerUtility pdfMerger = new PDFMergerUtility();

		for (File file : files) {
			pdfMerger.addSource(file);
		}

		pdfMerger.setDestinationFileName(outputFile.getAbsolutePath());
		pdfMerger.mergeDocuments();

		return outputFile;
	}

	private void mergeFiles(File primaryPDF, File secondaryPDF, File mergedTempFile) throws IOException {
		// Create a temporary file for the merged document
//		File mergedTempFile = File.createTempFile("merged_pdf_", ".pdf");
		if(primaryPDF != null && secondaryPDF != null) {
			isPrimaryPDF = true;
			LOG.info("Primary & Secondary PDF's loaded");
			PDDocument testDocument = PDDocument.load(primaryPDF);
			PDDocument secDocument = PDDocument.load(secondaryPDF);

			for (PDPage page : secDocument.getPages()) {
				testDocument.addPage(page);
			}
			testDocument.save(mergedTempFile);
			LOG.info("Primary & Secondary PDF's merged");

			// Close both documents
			testDocument.close();
			secDocument.close();

		} else if(primaryPDF != null) {
			isPrimaryPDF = true;
			LOG.info("There is no Secondary PDF");
			PDDocument primaryDocument = PDDocument.load(primaryPDF);

			// Save the merged content to this temporary file
			primaryDocument.save(mergedTempFile);
			primaryDocument.close();

		} else if (secondaryPDF != null) {
			LOG.info("There is no Primary PDF");
			PDDocument secDocument = PDDocument.load(secondaryPDF);
			secDocument.save(mergedTempFile);
			secDocument.close();

		}

	}

	private float getValue(String prop, float defaultValue) {
		return this.configurationService.getConfiguration().getFloat(prop, defaultValue);
	}

	private void addContentsToPdf(File fileMerged, OpportunityProductModel opportunity) throws IOException {
		LOG.info("***** Adding contents to PDF started *******");
		if(opportunity == null) {
			opportunity = new OpportunityProductModel();
		}

		try (PDDocument document = PDDocument.load(fileMerged)) {
			PDPage page;
			PDPageContentStream contentStream = null;
			// No processing except timestamp if there is no Primary PDF
			if(isPrimaryPDF) {
				// Page - 1

				page = document.getPage(0);
				contentStream = new PDPageContentStream(document, page, PDPageContentStream.AppendMode.APPEND, true, true);
				page1 = true;
				// Opportunity Title
				fillText(isNullOrBlank(opportunity.getName()) ? title : opportunity.getName(), contentStream,
						getValue("opportunity.page1.title.posX", 58),
						getValue("opportunity.page1.title.posY", 450),
						getValue("opportunity.page1.title.font.size", 16),
						"Opportunity Title", 60, false);
				// Sector
				Collection<CategoryModel> supercategories = opportunity.getSupercategories();
				// Get the first item from the collection
				if(Objects.nonNull(supercategories)) {
					CategoryModel firstCategory = supercategories.stream().findFirst().orElse(null);
					if(Objects.nonNull(firstCategory) && !isNullOrBlank(firstCategory.getName())) {
						sector = firstCategory.getName();
					}
				}
				fillText(sector, contentStream, getValue("opportunity.page1.sector.posX", 58),
						getValue("opportunity.page1.sector.posY", 400),
						getValue("opportunity.page1.sector.font.size", 16),
						"Opportunity Sector", 55, false);

				contentStream.close();

				// Page - 2

				page1 = false;
				page = document.getPage(1);
				contentStream = new PDPageContentStream(document, page, PDPageContentStream.AppendMode.APPEND, true, true);
				// Opportunity Title
				fillText(isNullOrBlank(opportunity.getName()) ? title : opportunity.getName(), contentStream,
						getValue("opportunity.page2.title.posX", 152),
						getValue("opportunity.page2.title.posY", 462),
						getValue("opportunity.page2.title.font.size", 12), "Opportunity Title", 63, false);
				// Description
				fillText(isNullOrBlank(opportunity.getDescription()) ? desc : opportunity.getDescription(), contentStream,
						getValue("opportunity.page2.description.posX", 152),
						getValue("opportunity.page2.description.posY", 428),
						getValue("opportunity.page2.description.font.size", 12), "Opportunity Description", 63, false);
				// Sector
				fillText(sector, contentStream, getValue("opportunity.page2.sector.posX", 550),
						getValue("opportunity.page2.sector.posY", 462),
						getValue("opportunity.page2.sector.font.size", 12), "Opportunity Sector", 63, false);
				// Segment
				Set<SagiaSegmentModel> sagiaSegments = opportunity.getSagiaSegment();
				if(Objects.nonNull(sagiaSegments)) {
					SagiaSegmentModel sagiaSegment = sagiaSegments.stream().findFirst().orElse(null);
					if(Objects.nonNull(sagiaSegment) && !isNullOrBlank(sagiaSegment.getSegmentName())) {
						segment = sagiaSegment.getSegmentName();
					}
				}
				fillText(segment, contentStream, getValue("opportunity.page2.segment.posX", 550),
						getValue("opportunity.page2.segment.posY", 428),
						getValue("opportunity.page2.segment.font.size", 12), "Opportunity Segment", 63, false);
				// Tags
				fillText(isNullOrBlank(opportunity.getSagiaKeywords()) ? tags : opportunity.getSagiaKeywords(), contentStream,
						getValue("opportunity.page2.tags.posX", 152),
						getValue("opportunity.page2.tags.posY", 395),
						getValue("opportunity.page2.tags.font.size", 12), "Tags", 155, false);
				// Investment Highlights
				if(!isNullOrBlank(opportunity.getHighlights())) {
					try {
						fillInvestmentHighlight(opportunity.getHighlights(), contentStream, 100);
					} catch (OutOfMemoryError e) {
						LOG.info("Could not fill in Investment Highlights", e);
					}
				}

				InvestmentOverviewModel investmentOverview = opportunity.getInvestmentOverview();
				if(Objects.nonNull(investmentOverview)) {
					if(!isNullOrBlank(investmentOverview.getIncentivesAndEnablers())) {
						incentivesAndEnablers = investmentOverview.getIncentivesAndEnablers();
					}

					if(!isNullOrBlank(investmentOverview.getValueProposition())) {
						valueProposition = investmentOverview.getValueProposition();
					}

				}
				// Incentives & Enablers

				fillText(incentivesAndEnablers, contentStream, getValue("opportunity.page2.incentives.posX", 490),
						getValue("opportunity.page2.incentives.posY", 280),
						getValue("opportunity.page2.incentives.font.size", 12), "Incentives & Enablers", 252, true);
				// Value Proposition
				fillText(valueProposition, contentStream, getValue("opportunity.page2.valueProposition.posX", 70),
						getValue("opportunity.page2.valueProposition.posY", 190),
						getValue("opportunity.page2.valueProposition.font.size", 12), "Value Proposition", 246, true);
				// Key Stakeholders
				Set<MediaModel> keyStakeholdersList = opportunity.getKeyStakeholders();
				if(Objects.nonNull(keyStakeholdersList)) {
					MediaModel mediaModel = keyStakeholdersList.stream().findFirst().orElse(null);
					if(Objects.nonNull(mediaModel) && !isNullOrBlank(mediaModel.getRealFileName())) {
						keyStakeholders = mediaModel.getRealFileName();
					}
				}
				fillText(keyStakeholders, contentStream, getValue("opportunity.page2.keyStakeholders.posX", 70),
						getValue("opportunity.page2.keyStakeholders.posY", 100),
						getValue("opportunity.page2.keyStakeholders.font.size", 12), "Key Stakeholders", 252, true);
				// Cost of doing business
				createCDB(document, contentStream, opportunity);

				contentStream.close();

				// Page - 3

				page = document.getPage(2);
				contentStream = new PDPageContentStream(document, page, PDPageContentStream.AppendMode.APPEND, true, true);

				MarketOverviewModel marketOverview = opportunity.getMarketOverview();
				if(Objects.nonNull(marketOverview)) {
					if(!isNullOrBlank(marketOverview.getRawMaterialsText())) {
						rawMaterials = marketOverview.getRawMaterialsText();
					}

					if(!isNullOrBlank(marketOverview.getGlobalTrendsText())) {
						globalTrends = marketOverview.getGlobalTrendsText();
					}
				}

				// Raw Material
				fillText(rawMaterials, contentStream, getValue("opportunity.page3.rawMaterials.posX", 70),
						getValue("opportunity.page3.rawMaterials.posY", 420),
						getValue("opportunity.page3.rawMaterials.font.size", 12), "Raw Material", 240, true);
				// Global Trends
				fillText(globalTrends, contentStream, getValue("opportunity.page3.globalTrends.posX", 70),
						getValue("opportunity.page3.globalTrends.posY", 345),
						getValue("opportunity.page3.globalTrends.font.size", 12), "Global Trends", 240, true);
				// Key Demand Drivers
				DemandModel demand = opportunity.getDemand();
				if(Objects.nonNull(demand) && !isNullOrBlank(demand.getKeyDemandDrivers())) {
					keyDemandDrivers = demand.getKeyDemandDrivers();
				}
				fillText(keyDemandDrivers, contentStream, getValue("opportunity.page3.demandDrivers.posX", 720),
						getValue("opportunity.page3.demandDrivers.posY", 430),
						getValue("opportunity.page3.demandDrivers.font.size", 12), "Demand", 240, true);

				SupplyModel supply = opportunity.getSupply();
				if(Objects.nonNull(supply)) {
					if(!isNullOrBlank(supply.getScalabilityAndLocalizationText())) {
						scalabilityAndLocalization = supply.getScalabilityAndLocalizationText();
					}

					if(!isNullOrBlank(supply.getImportDependencyText())) {
						importDependency = supply.getImportDependencyText();
					}
				}
				// Scalability & Localization
				fillText(scalabilityAndLocalization, contentStream, getValue("opportunity.page3.scalability.posX", 70),
						getValue("opportunity.page3.scalability.posY", 210),
						getValue("opportunity.page3.scalability.font.size", 12), "Scalability & Localization", 252, true);
				// Import Dependency
				fillText(importDependency, contentStream, getValue("opportunity.page3.importDependency.posX", 490),
						getValue("opportunity.page3.importDependency.posY", 210),
						getValue("opportunity.page3.importDependency.font.size", 12), "Import Dependency", 240, true);
				// Demand Bar Chart
				createStackedBarChart(document, contentStream, opportunity);

				contentStream.close();
			}
			int totalPages = document.getNumberOfPages();
			for(int pageIndex = 0; pageIndex < totalPages; pageIndex++) {
				page = document.getPage(pageIndex);
				contentStream = new PDPageContentStream(document, page, PDPageContentStream.AppendMode.APPEND, true, true);
				fillText(getCurrentDate(), contentStream, getValue("opportunity.timestamp.posX", 730),
						getValue("opportunity.timestamp.posY", 20),
						getValue("opportunity.timestamp.font.size", 10), null, 50, false);
				contentStream.close();
			}

			document.save(fileMerged);
			LOG.info("***** Adding contents to PDF end *******");
		}
	}

	private void fillInvestmentHighlight(String text, PDPageContentStream contentStream, int maxFieldLength) throws IOException {
		// Extract Text from HTML tags and fill as Bulleted list
		List<String> highlights = extractInnerText(text);

		contentStream.beginText();
		contentStream.setFont(PDType1Font.TIMES_ROMAN, getValue("opportunity.page2.highlights.font.size", 10));

		contentStream.newLineAtOffset(getValue("opportunity.page2.highlights.posX", 65),
				getValue("opportunity.page2.highlights.posY", 288));
		for (String highlight : highlights) {
			// Truncate the string to max length available on pdf template
			if(highlight.length() > maxFieldLength) {
				highlight = highlight.substring(0, (maxFieldLength - 3)) + " ...";
			}
			contentStream.showText(highlight);
			contentStream.newLineAtOffset(0, -10);
		}
		contentStream.endText();
		LOG.info("Investment Highlights field is filled in page 2");
	}

	private void createCDB(PDDocument document,PDPageContentStream contentStream, OpportunityProductModel opportunity) throws IOException {
		File media = getMedia(Config.getString("opportunity.media.image.name", "CODB_IMAGE"), "png");
		if(Objects.nonNull(media)) {
			PDImageXObject pdImage = PDImageXObject.createFromFile(media.getPath(), document);

			contentStream.drawImage(pdImage,getValue("opportunity.page2.CODB.image.posX", 620),
					getValue("opportunity.page2.CODB.image.posY", 55),
					getValue("opportunity.page2.CODB.image.width", 260),
					getValue("opportunity.page2.CODB.image.height", 200));

			LOG.info("Cost of doing business field is filled in page 2");

			InvestmentOverviewModel investmentOverview = opportunity.getInvestmentOverview();
			if(Objects.nonNull(investmentOverview)) {
				CostOfDoingBusinessModel costOfDoingBusiness = investmentOverview.getCostOfDoingBusiness();
				if(Objects.nonNull(costOfDoingBusiness)) {

					electricityTariffs = isNullOrBlank(costOfDoingBusiness
							.getElectricityTariffs()) ? electricityTariffs : costOfDoingBusiness
							.getElectricityTariffs();

					productivityAdjustedWages = isNullOrBlank(costOfDoingBusiness
							.getProductivityAdjustedWages()) ? productivityAdjustedWages : costOfDoingBusiness
							.getProductivityAdjustedWages();

					logisticsPerformanceIndex = isNullOrBlank(costOfDoingBusiness
							.getLogisticsPerformanceIndex()) ? logisticsPerformanceIndex : costOfDoingBusiness
							.getLogisticsPerformanceIndex();

					constructionCosts = isNullOrBlank(costOfDoingBusiness
							.getConstructionCosts()) ? constructionCosts : costOfDoingBusiness
							.getConstructionCosts();

				}
			}
		}
		// Electricity Tariffs
		if(!electricityTariffs.contains("/MWh")) {
			electricityTariffs = "$" + electricityTariffs + " /MWh";
		}
		fillText(electricityTariffs, contentStream, getValue("opportunity.page2.electricityTariffs.posX", 805),
				getValue("opportunity.page2.electricityTariffs.posY", 218),
				getValue("opportunity.page2.electricityTariffs.font.size", 10), "CODB", 10, false);

		// Productivity Adjusted Wages
		if(!productivityAdjustedWages.contains("/hour")) {
			productivityAdjustedWages = "$" + productivityAdjustedWages + " /hour";
		}
		fillText(productivityAdjustedWages, contentStream, getValue("opportunity.page2.productivityWages.posX", 845),
				getValue("opportunity.page2.productivityWages.posY", 170),
				getValue("opportunity.page2.productivityWages.font.size", 10), "CODB", 10, false);

		// Logistics Performance Index
		fillText(logisticsPerformanceIndex, contentStream, getValue("opportunity.page2.logisticsPerformance.posX", 808),
				getValue("opportunity.page2.logisticsPerformance.posY", 122),
				getValue("opportunity.page2.logisticsPerformance.font.size", 10), "CODB", 10, false);

		// Construction Costs
		fillText(constructionCosts, contentStream, getValue("opportunity.page2.constructionCosts.posX", 810),
				getValue("opportunity.page2.constructionCosts.posY", 74),
				getValue("opportunity.page2.constructionCosts.font.size", 10), "CODB", 10, false);

		LOG.info("Cost of doing business Stats are filled in page 2");

	}

	/**
	 * To fill the provided text in the specified position with customization
	 * @param text
	 * @param contentStream
	 * @param posX
	 * @param posY
	 * @param fontSize
	 * @param fieldName
	 * @param maxFieldLength
	 * @throws IOException
	 */
	public void fillText(String text, PDPageContentStream contentStream, float posX, float posY,
						 float fontSize, String fieldName, int maxFieldLength, boolean multiLineText) throws IOException {
		if (text != null) {
			String MORE =  "...";

			// Truncate the string to max length available on pdf template
			if(text.length() > maxFieldLength) {
				text = text.substring(0, (maxFieldLength - 3)) + MORE;
			}
			contentStream.beginText();

			contentStream.setFont(PDType1Font.TIMES_ROMAN, fontSize);
			// Set font color to Light Grey
			contentStream.setNonStrokingColor(111, 115, 115);

			// Indicates 1st page of the pdf template, set font color to Green
			if (page1) {
				contentStream.setNonStrokingColor(48, 135, 42);
			}
			// Set position on pdf using x, y axis
			contentStream.newLineAtOffset(posX, posY);

			if (multiLineText) {
				int maxLineLength;
				if(fieldName != null && fieldName.equals("Demand")) {
					maxLineLength = maxFieldLength / 6;
				} else {
					maxLineLength = maxFieldLength / 3;
				}
				if(text.length() > maxLineLength) {
					// It determines the spacing between lines when writing multiple lines of text
					contentStream.setLeading(14.5f);
					/* If the field length exceeds the Max length allowed in slot,
					split the text to multiple lines and write to the PDF */
					String[] lines = splitLongString(text, maxLineLength);
					for (String line : lines) {
						contentStream.showText(line);
						contentStream.newLine();
					}
				} else {
					contentStream.showText(text);
				}

			} else {
				if (fieldName != null && fieldName.equals("CODB")) {
					String[] lines = text.split(" ");
					for (String line : lines) {
						contentStream.showText(line);
						contentStream.newLineAtOffset(0, -10);
					}
				} else {
					contentStream.showText(text);
				}
			}
			contentStream.endText();

			if (fieldName != null)
				LOG.info("The " + fieldName + " field has been successfully filled.");
		}
	}

	public static String[] splitLongString(String input, int maxLineLength) {
		List<String> lines = new ArrayList<>();
		String[] words = input.split("\\s+");

		StringBuilder currentLine = new StringBuilder(words[0]);
		for (int i = 1; i < words.length; i++) {
			if (currentLine.length() + words[i].length() + 1 <= maxLineLength) {
				currentLine.append(" ").append(words[i]);
			} else {
				lines.add(currentLine.toString());
				currentLine = new StringBuilder(words[i]);
			}
		}
		lines.add(currentLine.toString());

		return lines.toArray(new String[0]);
	}

	private void createStackedBarChart(PDDocument document, PDPageContentStream contentStream, OpportunityProductModel opportunity) throws IOException {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		dataset.addValue(0.7, "KSA", "2018");
		dataset.addValue(0.7,"KSA",  "2020");
		dataset.addValue(0.9,"KSA", "2025");
		dataset.addValue(1.1,"KSA", "2030");
		dataset.addValue(1.3,"KSA", "2035");

		dataset.addValue(0.8, "Rest Gcc", "2018");
		dataset.addValue(0.8,"Rest Gcc",  "2020");
		dataset.addValue(1,"Rest Gcc", "2025");
		dataset.addValue(1.2,"Rest Gcc", "2030");
		dataset.addValue(1.4,"Rest Gcc", "2035");

		DemandModel demand = opportunity.getDemand();
		if(Objects.nonNull(demand) && Objects.nonNull(demand.getMarkets())) {
			Set<MarketModel> marketList = demand.getMarkets();

			if(!marketList.isEmpty()) {
				dataset = new DefaultCategoryDataset();
				for (MarketModel market : marketList) {
					// Assuming market codes are unique, we can use them as series identifiers
					String marketCodeSeries = market.getCode(); // Replace getMarketCode() with your actual getter method

					// Split the years and market sizes
					String[] years = market.getYears().split(",");
					String[] marketSizes = market.getMarketSizes().split(",");

					for (int i = 0; i < years.length; i++) {
						String yearCategory = years[i].trim();
						double marketSizeValue = Double.parseDouble(marketSizes[i].trim());
						dataset.addValue(marketSizeValue, marketCodeSeries, yearCategory);
					}
					LOG.info("Market Data found to generate graph.");
				}
			} else {
				LOG.info("No Market Data found in Opportunity Demand object, using stock data to generate graph.");
			}
		}

		JFreeChart chart = ChartFactory.createBarChart(
				"MARKET SIZE, USD",
				"",
				"",
				dataset,
				PlotOrientation.VERTICAL,
				true,
				true,
				false
		);

		// Chart Customization
		CategoryPlot plot = chart.getCategoryPlot();
		plot.setRenderer(new CategoryItemRenderer());
		plot.setBackgroundPaint(new Color(0, 0, 0, 0)); // Transparent background
		BarRenderer renderer = (BarRenderer) plot.getRenderer();
		renderer.setMaximumBarWidth(0.1);

		// Set the font size for the chart title
		Font titleFont = chart.getTitle().getFont();
		Font newTitleFont = titleFont.deriveFont(12f); // Change the font size to 18
		chart.getTitle().setFont(newTitleFont);
		chart.getTitle().setPaint(new Color(48, 135, 42));

		// Get the CategoryAxis object from the CategoryPlot object.
		CategoryAxis axis = plot.getDomainAxis();

		// Get the ValueAxis object from the CategoryPlot object.
		NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();

		// Set the font size of the CategoryAxis object and the ValueAxis object to 8pt.
		axis.setTickLabelFont(new Font("Arial", Font.PLAIN, 8));
		rangeAxis.setTickLabelFont(new Font("Arial", Font.PLAIN, 8));

		// Set custom colors for each section of the bars
		renderer.setSeriesPaint(0, new Color(89, 168, 110));
		renderer.setSeriesPaint(1, new Color(145, 129, 64));

		// Set flat colors without gradients
		renderer.setBarPainter(new StandardBarPainter());
		// Set the outline visible to false for a flat appearance
		renderer.setDrawBarOutline(false);
		// Remove the shadow from the bars
		renderer.setShadowVisible(false);

		ByteArrayOutputStream chartImageStream = new ByteArrayOutputStream();
		ChartUtilities.writeChartAsPNG(chartImageStream, chart,
				(int) getValue("opportunity.page3.marketSize.graph.width", 220),
				(int) getValue("opportunity.page3.marketSize.graph.height", 200));

		PDImageXObject chartImage = PDImageXObject.createFromByteArray(document, chartImageStream.toByteArray(), "Chart Image");

		contentStream.drawImage(chartImage,
				getValue("opportunity.page3.marketSize.graph.posX", 493),
				getValue("opportunity.page3.marketSize.graph.posY", 275));

		LOG.info("Graph created with data set");
		// Demand CAGR
		String demandCagr = "CAGR 3.6%";
		if(Objects.nonNull(opportunity.getDemand()) && Objects.nonNull(opportunity.getDemand().getCagr())) {
			String cagr = String.valueOf(opportunity.getDemand().getCagr());
			if(!cagr.contains("%")) {
				cagr = "CAGR " + cagr + "%";
			}

			fillText(isNullOrBlank(cagr) ? demandCagr: cagr, contentStream, getValue("opportunity.page3.cagr.posX", 560),
					getValue("opportunity.page3.cagr.posY", 425),
					getValue("opportunity.page3.cagr.font.size", 9), "CAGR", 10, false);
		}

	}

	private String getCurrentDate() {
		String pattern = DATE_TIME_PATTERN;
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		sdf.setTimeZone(TimeZone.getTimeZone(KSA_TIME_ZONE));
		String dateInString = sdf.format(new Date());
		return dateInString;
	}

	private boolean isNullOrBlank(String input) {
		return input == null || input.isBlank();
	}

	private static List<String> extractInnerText(String htmlText) {
		Document document = Jsoup.parse(htmlText);
		int count = 0;
		String cssQuery = "li span";
		if(htmlText.contains("<span><span>")) {
			cssQuery = "li span span";
		}
		Elements elements = document.select(cssQuery);
		List<String> highlights = new ArrayList<>();

		for (Element element : elements) {
			// Limit to only 4 lists items, due to space in pdf slot
			if(count == 4)
				break;
			if(!element.text().isBlank()) {
				element.prepend(". ");
				highlights.add(element.text().trim());
			}
			count++;
		}

		return highlights;
	}

}
