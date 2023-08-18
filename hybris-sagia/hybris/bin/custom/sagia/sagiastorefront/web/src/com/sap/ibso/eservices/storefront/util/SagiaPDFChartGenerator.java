package com.sap.ibso.eservices.storefront.util;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.PDPageContentStream.AppendMode;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import de.hybris.platform.core.model.product.ProductModel;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.investsaudi.portal.core.model.OpportunityProductModel;

import org.apache.commons.text.StringEscapeUtils;
import org.apache.pdfbox.multipdf.PDFMergerUtility;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import com.investsaudi.portal.core.model.MarketModel;

public class SagiaPDFChartGenerator {

	private static final Logger LOG = LoggerFactory.getLogger(SagiaPDFChartGenerator.class);
	private static final String DESCRIPTION = "This is a sample descriptive text for the pdf.This is a sample descriptive text for the pdf.This is a sample descriptive text for the pdf.";
	private static final String DATE_TIME_PATTERN = "E, dd MMM yyyy HH:mm:ss z";
	private static final String KSA_TIME_ZONE = "Asia/Riyadh";

	public static File generatePdfFile(ProductModel productModel, List<File> secFiles) throws IOException {
		File fileMerged;
		fileMerged = File.createTempFile("merged_file", ".pdf");
		File outputFile;
		outputFile = File.createTempFile("output_file", ".pdf");

		if (productModel instanceof OpportunityProductModel) {
			OpportunityProductModel opportunity = (OpportunityProductModel) productModel;
			try {
				ByteArrayOutputStream testPdfStream = new ByteArrayOutputStream();
				try (PDDocument document = new PDDocument()) {
					PDPage page = new PDPage(new PDRectangle(960, 540));
					document.addPage(page);
					document.save(testPdfStream);

					try (PDDocument loadDoc = PDDocument.load(testPdfStream.toByteArray())) {
						page = loadDoc.getPage(0);
						createBarChart(loadDoc, opportunity, null, page);
						createPieChart(loadDoc, opportunity, null, page);
						testPdfStream.reset(); // clear the stream for reuse
						loadDoc.save(testPdfStream);
					}
				}

				File secondaryConcatenatedFile = concatenatePDFs(secFiles, outputFile);
				// Convert the stream back to a File object for further use
				File primaryPdfFile = File.createTempFile("intermediate_file", ".pdf");
				try (FileOutputStream fos = new FileOutputStream(primaryPdfFile)) {
					testPdfStream.writeTo(fos);
				}

				fileMerged = mergeFiles(primaryPdfFile, secondaryConcatenatedFile);
				addContentsToPdf(fileMerged, opportunity);

			} catch (IOException e) {
				LOG.error("Exception occurred during PDF file generation: " + e.getMessage());
			} finally {
				LOG.info("In finally block");
			} 
		}
		return fileMerged;
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

	private static void createBarChart(PDDocument document, OpportunityProductModel opportunity, Path tempDir, PDPage page)
	        throws IOException {
	    DefaultCategoryDataset barDataset = new DefaultCategoryDataset();

	    // Assuming you have a method to fetch the MarketModel List from the opportunity
	    List<MarketModel> marketList = opportunity.getDemand().getMarkets();

	    for (MarketModel market : marketList) {
	        // Assuming market codes are unique, we can use them as series identifiers
	        String marketCodeSeries = market.getCode(); // Replace getMarketCode() with your actual getter method

	        // Split the years and market sizes
	        String[] years = market.getYears().split(",");
	        String[] marketSizes = market.getMarketSizes().split(",");

	        for (int i = 0; i < years.length; i++) {
	            String yearCategory = years[i].trim();
	            double marketSizeValue = Double.parseDouble(marketSizes[i].trim());
	            barDataset.addValue(marketSizeValue, marketCodeSeries, yearCategory);
	        }
	    }

	    JFreeChart barChart = ChartFactory.createBarChart("Bar Chart", "Year", "Market Size", barDataset,
	            PlotOrientation.HORIZONTAL, true, true, false);
	    barChart.setBackgroundPaint(java.awt.Color.getHSBColor(0.25f, 0.51f, 0.84f));

	    ByteArrayOutputStream chartOutputStream = new ByteArrayOutputStream();
	    ChartUtilities.writeChartAsPNG(chartOutputStream, barChart, 400, 300);

	    PDImageXObject pdImage = PDImageXObject.createFromByteArray(document, chartOutputStream.toByteArray(),
	            "bar_chart.png");
	    PDPageContentStream contentStream = new PDPageContentStream(document, page,
	            PDPageContentStream.AppendMode.APPEND, true, true);
	    contentStream.drawImage(pdImage, 50, 140);
	    contentStream.close();
	}


	private static void createPieChart(PDDocument document, OpportunityProductModel opportunity, Path tempDir, PDPage page)
	        throws IOException {
	    DefaultPieDataset pieDataset = new DefaultPieDataset();

	    List<MarketModel> marketList = opportunity.getDemand().getMarkets();

	    for (MarketModel market : marketList) {
	        String marketCodeCategory = market.getCode(); // Assuming getMarketCode() is the getter for market code

	        // Split the market sizes and sum them up for each market code
	        String[] marketSizes = market.getMarketSizes().split(",");
	        double totalMarketSize = 0;
	        for (String size : marketSizes) {
	            totalMarketSize += Double.parseDouble(size.trim());
	        }
	        pieDataset.setValue(marketCodeCategory, totalMarketSize);
	    }

	    JFreeChart pieChart = ChartFactory.createPieChart("Pie Chart", pieDataset, true, true, false);
	    pieChart.setBackgroundPaint(java.awt.Color.getHSBColor(0.25f, 0.51f, 0.84f));

	    ByteArrayOutputStream chartOutputStream = new ByteArrayOutputStream();
	    ChartUtilities.writeChartAsPNG(chartOutputStream, pieChart, 400, 300);

	    PDImageXObject pdImage = PDImageXObject.createFromByteArray(document, chartOutputStream.toByteArray(),
	            "pie_chart.png");
	    PDPageContentStream contentStream = new PDPageContentStream(document, page,
	            PDPageContentStream.AppendMode.APPEND, true, true);
	    contentStream.drawImage(pdImage, 500, 140);
	    contentStream.close();
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

	private static void addContentsToPdf(File fileMerged, OpportunityProductModel opportunity) throws IOException {
		PDDocument docMerged = PDDocument.load(fileMerged);
		addBorder(fileMerged);
		addHeaderTextContent(435, 520, fileMerged, opportunity,docMerged);
		addSampleTextContent(220, 125, "Sample Text 1", fileMerged, opportunity,docMerged);
		addSampleTextContent(660, 125, "Sample Text 2", fileMerged, opportunity,docMerged);
		addTDateAndTime(fileMerged);
		addDescriptiveTextContent(52, 80, DESCRIPTION, fileMerged, opportunity,docMerged);
		docMerged.save(fileMerged);
		docMerged.close();
	}

	private static void addHeaderTextContent(float x, float y, File fileMerged, OpportunityProductModel opportunity, PDDocument docMerged)
			throws IOException {
		//PDDocument docMerged = PDDocument.load(fileMerged);
		PDPage pageFirst = docMerged.getPage(0);
		PDPageContentStream contentStreamMergedDoc = new PDPageContentStream(docMerged, pageFirst, AppendMode.APPEND,
				true, true);

		contentStreamMergedDoc.beginText();
		contentStreamMergedDoc.setFont(PDType1Font.TIMES_BOLD, 16);
		contentStreamMergedDoc.setLeading(14.5f);
		contentStreamMergedDoc.newLineAtOffset(x, y);
		contentStreamMergedDoc.setNonStrokingColor(java.awt.Color.BLACK);
		contentStreamMergedDoc.showText(opportunity.getDemand().getMarketSizeText());
		contentStreamMergedDoc.endText();
		contentStreamMergedDoc.close();
		//docMerged.save(fileMerged);
		//docMerged.close();

	}

	private static void addTDateAndTime(File fileMrged) throws IOException {
		PDDocument docMerged = PDDocument.load(fileMrged);
		int numOfPages = docMerged.getNumberOfPages();

		PDPageContentStream contentStreamForDate;
		for (int i = 0; i < numOfPages; i++) {

			PDPage pageFirst = docMerged.getPage(i);
			contentStreamForDate = new PDPageContentStream(docMerged, pageFirst, AppendMode.APPEND, true, true);
			contentStreamForDate.beginText();
			contentStreamForDate.setFont(PDType1Font.TIMES_BOLD, 10);
			contentStreamForDate.setLeading(14.5f);
			contentStreamForDate.newLineAtOffset(824, 3);
			String dateText = getCurrentDate();
			contentStreamForDate.setNonStrokingColor(java.awt.Color.RED);
			contentStreamForDate.showText(dateText);
			contentStreamForDate.endText();
			contentStreamForDate.close();
		}

		//docMerged.save(fileMrged);
		//docMerged.close();

	}

	private static void addSampleTextContent(float x, float y, String text, File fileMrged, OpportunityProductModel opportunity, PDDocument docMerged2)
			throws IOException {
		PDDocument docMerged = PDDocument.load(fileMrged);
		PDPage pageFirst = docMerged.getPage(0);
		PDPageContentStream contentStreamMergedDoc = new PDPageContentStream(docMerged, pageFirst, AppendMode.APPEND,
				true, true);

		contentStreamMergedDoc.beginText();
		contentStreamMergedDoc.setFont(PDType1Font.TIMES_BOLD, 16);
		contentStreamMergedDoc.setLeading(14.5f);
		contentStreamMergedDoc.newLineAtOffset(x, y);
		contentStreamMergedDoc.setNonStrokingColor(java.awt.Color.BLACK);
		double cagrValue = opportunity.getDemand().getCagr();
		String cagrString = String.format("CAGR value: %.2f%%", cagrValue);
		contentStreamMergedDoc.showText(cagrString);
		contentStreamMergedDoc.endText();
		contentStreamMergedDoc.close();
		//docMerged.save(fileMrged);
		//docMerged.close();

	}

	private static void addDescriptiveTextContent(float x, float y, String text, File fileMrged,
			OpportunityProductModel opportunity, PDDocument docMerged2) throws IOException {
		PDDocument docMerged = PDDocument.load(fileMrged);
		PDPage pageFirst = docMerged.getPage(0);
		PDPageContentStream contentStreamMergedDoc = new PDPageContentStream(docMerged, pageFirst, AppendMode.APPEND,
				true, true);

		contentStreamMergedDoc.beginText();
		contentStreamMergedDoc.setFont(PDType1Font.HELVETICA, 12);
		contentStreamMergedDoc.setLeading(14.5f);
		contentStreamMergedDoc.newLineAtOffset(x, y);
		contentStreamMergedDoc.setNonStrokingColor(java.awt.Color.BLACK);
		contentStreamMergedDoc.showText(opportunity.getDemand().getKeyDemandDrivers());
		contentStreamMergedDoc.endText();
		contentStreamMergedDoc.close();
		//docMerged.save(fileMrged);
		//docMerged.close();

	}

	private static void addBorder(File fileMrged) throws IOException {
		PDDocument docMerged = PDDocument.load(fileMrged);
		PDPage pageFirst = docMerged.getPage(0);
		PDPageContentStream contentStreamMergedDoc = new PDPageContentStream(docMerged, pageFirst, AppendMode.APPEND,
				true, true);

		contentStreamMergedDoc.setNonStrokingColor(Color.DARK_GRAY);
		contentStreamMergedDoc.addRect(400, 650, 200, 300);
		contentStreamMergedDoc.fill();
		contentStreamMergedDoc.setNonStrokingColor(0, 0, 0);
		contentStreamMergedDoc.close();
		docMerged.save(fileMrged);
		docMerged.close();
	}

	private static String getCurrentDate() {
		String pattern = DATE_TIME_PATTERN;
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		sdf.setTimeZone(TimeZone.getTimeZone(KSA_TIME_ZONE));
		String dateInString = sdf.format(new Date());
		return dateInString;
	}

}
