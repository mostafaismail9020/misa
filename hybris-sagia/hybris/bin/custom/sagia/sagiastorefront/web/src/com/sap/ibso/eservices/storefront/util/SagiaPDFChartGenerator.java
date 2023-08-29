package com.sap.ibso.eservices.storefront.util;

import com.investsaudi.portal.core.model.OpportunityProductModel;
import de.hybris.platform.catalog.CatalogVersionService;
import de.hybris.platform.category.CategoryService;
import de.hybris.platform.core.model.media.MediaModel;
import de.hybris.platform.core.model.product.ProductModel;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class SagiaPDFChartGenerator {

	private static final Logger LOG = LoggerFactory.getLogger(SagiaPDFChartGenerator.class);

	private static final String SAGIA_PRODUCT_CATALOG = "sagiaProductCatalog";
	private static final String CATALOG_VERSION_STAGED = "Staged";

	private static final String ROOT_CATEGORY_ID = "sector-opportunities";

	private static final String MEDIA_PDF_FILE_NAME = "opportunity-pdf";

	@Resource
	private CatalogVersionService catalogVersionService;

	@Resource
	private CategoryService categoryService;

	@Resource(name = "mediaService")
	private MediaService mediaService;

	public File generatePdfFile(ProductModel productModel, List<File> secFiles) throws IOException {
		File fileMerged;
		fileMerged = File.createTempFile("merged_file", ".pdf");
		File outputFile;
		outputFile = File.createTempFile("output_file", ".pdf");

		if (productModel instanceof OpportunityProductModel) {
			OpportunityProductModel opportunity = (OpportunityProductModel) productModel;
			File secondaryConcatenatedFile = concatenatePDFs(secFiles, outputFile);

			// loading the primary pdf template

			List<MediaModel> medias = categoryService.getCategoryForCode("sector-opportunities").getMedias();
			File primaryPdfFileTemplate = null;
			for(MediaModel media: medias) {
				if(media.getCode().equals(MEDIA_PDF_FILE_NAME)) {
					primaryPdfFileTemplate = convertMediaToFile(media);
				}
			}
//			primaryPdfFileTemplate = new File(Config.getString("opportunity.pdf.template.path", ""));

			fileMerged = mergeFiles(primaryPdfFileTemplate, secondaryConcatenatedFile);
			addContentsToPdf(fileMerged, opportunity);
		}
		return fileMerged;
	}

	public File convertMediaToFile(MediaModel mediaModel) throws IOException {

		// Get the file data from the MediaModel
		InputStream inputStream = mediaService.getStreamFromMedia(mediaModel);

		// Create a temporary File object
		File tempFile;

		try {
			tempFile = File.createTempFile("temp_file", ".pdf");

			// Copy the data from the InputStream to the File
			FileUtils.copyInputStreamToFile(inputStream, tempFile);

		} catch (IOException e) {
			// Handle the exception, if any
			LOG.error("Error while converting MediaModel to file", e);
			throw e;
		}

		return tempFile;
	}

	public static File concatenatePDFs(List<File> files, File outputFile) throws IOException {
		PDFMergerUtility pdfMerger = new PDFMergerUtility();

		for (File file : files) {
			pdfMerger.addSource(file);
		}

		pdfMerger.setDestinationFileName(outputFile.getAbsolutePath());
		pdfMerger.mergeDocuments();

		return outputFile;
	}
	private static File mergeFiles(File testPdfFile, File secFile) throws IOException {
		PDDocument testDocument = PDDocument.load(testPdfFile);
		PDDocument secDocument = PDDocument.load(secFile);

		for (PDPage page : secDocument.getPages()) {
			testDocument.addPage(page);
		}

		// Create a temporary file for the merged document
		File mergedTempFile = File.createTempFile("merged_pdf_", ".pdf");

		// Save the merged content to this temporary file
		testDocument.save(mergedTempFile);

		// Close both documents
		testDocument.close();
		secDocument.close();

		return mergedTempFile; // Return the temporary file with the merged content
	}

	private void addContentsToPdf(File fileMerged, OpportunityProductModel opportunity) throws IOException {
		String title = "Neom";
		String desc = "Opportunity brief description";
		String sector = "Construction";
		String segment = "Not sure";
		String tags = "List of key words linked to the investment opportunity";
		String investmentHighlights = "Expected Investment size, Plant capacity, Expected IRR, Payback period, Job Creation, GDP Impact, Location (Region):";
		String incentivesAndEnablers = "Factors that enable investment in the underlying opportunity such as General Incentive and financing";
		String valueProposition = "Summary of key differentiators that position KSA as a strategic choice over other regional/global peers";
		String keyStakeholders = "Government institutions, organizations, and/or authorities that participate or influence the market for the underlying product/service Logos";

		// Page - 2
		String rawMaterials = "Raw Materials";
		String globalTrends = "Latest business developments within the sector/product category";
		String keyDemandDrivers = "Selected number of factors that will influence future demand for the related product/service";
		String scalabilityAndLocalization = "Ease of scaling the business across the value chain or into new adjacent products or geographies that would maximize the opportunity’s investment returns and the ability and potential to locally manufacture the product and its components";
		String importDependency = "An overview of the countries from which Saudi Arabia is importing the product and their value/volume and share in total import";

		try (PDDocument document = PDDocument.load(fileMerged)) {
//			Objects.nonNull()
			// Page - 1

			fillText("NEOM", document, 0, 58, 450, 16, "0");
			fillText("Sector", document, 0, 58, 400, 16, "0");

			// Title
			fillText(title, document, 1, 152, 462, 12, null);
			// Description
			fillText(desc, document, 1, 152, 428, 12, null);
			// Sector
			fillText(sector, document, 1, 550, 462, 12, null);
			// Segment
			fillText(segment, document, 1, 550, 428, 12, null);
			// Tags
			fillText(tags, document, 1, 152, 395, 12, null);
			// Investment Highlights
			fillText(investmentHighlights, document, 1, 70, 280, 12, null);
			// Incentives & Enablers
			fillText(incentivesAndEnablers, document, 1, 490, 280, 12, null);
			// Value Proposition
			fillText(valueProposition, document, 1, 70, 190, 12, null);
			// Key Stakeholders
			fillText(keyStakeholders, document, 1, 70, 100, 12, null);
			// Cost of doing business
			createCDB(document);

//			createCDBText(document);

			// Page - 2
			fillText(rawMaterials, document, 2, 70, 420, 12, null);

			fillText(globalTrends, document, 2, 70, 345, 12, null);

			fillText(keyDemandDrivers, document, 2, 720, 430, 12, null);

			fillText(scalabilityAndLocalization, document, 2, 70, 210, 12, null);

			fillText(importDependency, document, 2, 490, 210, 12, null);

			createStackedBarChart(document);

			fillText("CAGR 3.6%", document, 2, 560 , 425 , 9, null);

			document.save(fileMerged);
		}
	}

	private void createCDB(PDDocument document) throws IOException {
		PDPage page = document.getPage(1);
		PDPageContentStream contentStream = new PDPageContentStream(document, page, PDPageContentStream.AppendMode.APPEND, true, true);

		PDImageXObject pdImage = PDImageXObject.createFromFile(Config.getString("opportunity.image.CODB", ""), document);
		contentStream.drawImage(pdImage, 620, 55, 260, 200);
		contentStream.close();
	}

	public void fillText(String text, PDDocument document, int pageIndex, float posX, float posY, int fontSize, String field) throws IOException {
		PDPage page = document.getPage(pageIndex);
		PDPageContentStream contentStream = new PDPageContentStream(document, page, PDPageContentStream.AppendMode.APPEND, true, true);
		contentStream.beginText();
		contentStream.setFont(PDType1Font.TIMES_ROMAN, fontSize);
		if(field != null && field.equals("0")) {
			contentStream.setNonStrokingColor(48, 135, 42);
		} else {
			contentStream.setNonStrokingColor(111, 115, 115);
		}
		contentStream.newLineAtOffset(posX, posY);
		int maxLength = -1;

		if(field != null && field.equals("CODB")){
			maxLength = 25;
		}else if(posX == 720 && text.length() >= 40) {
			maxLength = 40;
		} else if(text.length() >= 82){
			maxLength = 82;
		}

		if (maxLength != -1) {
			contentStream.setLeading(14.5f);
			String[] lines = splitLongString(text, maxLength);
			for (String line : lines) {
				contentStream.showText(line);
				contentStream.newLine();
			}
		} else {
			contentStream.showText(text);
		}
		contentStream.endText();

		contentStream.close();
	}

	public String[] splitLongString(String input, int maxLineLength) {
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

	private void createStackedBarChart(PDDocument document) throws IOException {
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


		PDPage page = document.getPage(2);

		PDPageContentStream contentStream = new PDPageContentStream(document, page, PDPageContentStream.AppendMode.APPEND, true, true);


		ByteArrayOutputStream chartImageStream = new ByteArrayOutputStream();
		ChartUtilities.writeChartAsPNG(chartImageStream, chart, 220, 200 );

		PDImageXObject chartImage = PDImageXObject.createFromByteArray(document, chartImageStream.toByteArray(), "Chart Image");

		contentStream.drawImage(chartImage, 493 , 275 );

		contentStream.close();
	}


}
