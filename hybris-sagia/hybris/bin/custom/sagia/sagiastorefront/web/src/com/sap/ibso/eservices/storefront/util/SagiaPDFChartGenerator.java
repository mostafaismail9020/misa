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
import de.hybris.bootstrap.config.ConfigUtil;
import de.hybris.platform.core.model.product.ProductModel;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.apache.pdfbox.multipdf.PDFMergerUtility;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import de.hybris.platform.util.Utilities;

public class SagiaPDFChartGenerator {
	
	private static final String DESCRIPTION = "This is a sample descriptive text for the pdf.This is a sample descriptive text for the pdf.This is a sample descriptive text for the pdf.";

	public static File generatePdfFile(ProductModel productModel, File secFile) {
	    File fileMerged = null;
	    try {
	        final File dataDir = ConfigUtil.getPlatformConfig(Utilities.class).getSystemConfig().getDataDir();
	        final Path dataDirectory = Paths.get(dataDir.getCanonicalPath());
	        final Path tempDir = Files.createTempDirectory(dataDirectory, "tempfiles");

	        File testPdfFile = new File(tempDir.toFile(), "Test.pdf");

	        try (PDDocument document = new PDDocument()) {
	            PDPage page = new PDPage(new PDRectangle(960, 540));
	            document.addPage(page);

	            createBarChart(document, productModel, tempDir, page);
	            createPieChart(document, productModel, tempDir, page);

	            document.save(testPdfFile);
	        }

	        fileMerged = mergeFiles(testPdfFile, secFile, tempDir);
	        addContentsToPdf(fileMerged, productModel);
	    } catch (IOException e) {
	        System.out.println("Exception occurred during PDF file generation: " + e.getMessage());
	    } finally {
	        System.out.println("In finally block");
	    }
	    return fileMerged;
	}


	private static void createBarChart(PDDocument document, ProductModel productModel, Path tempDir, PDPage page) throws IOException {
	    DefaultCategoryDataset barDataset = new DefaultCategoryDataset();
	    barDataset.addValue(productModel.getMaxOrderQuantity(), "Series 1", "Category 1");//50
	    barDataset.addValue(productModel.getMinOrderQuantity(), "Series 1", "Category 2");//70
	    barDataset.addValue(productModel.getOrderQuantityInterval(), "Series 2", "Category 1");//30
	    barDataset.addValue(productModel.getStartLineNumber(), "Series 2", "Category 2");//90

	    JFreeChart barChart = ChartFactory.createBarChart("Bar Chart", "Category", "Value", barDataset,
	            PlotOrientation.HORIZONTAL, true, true, false);
	    barChart.setBackgroundPaint(java.awt.Color.getHSBColor(0.25f, 0.51f, 0.84f));

	    File barChartFile = new File(tempDir.toFile(), "bar_chart.png");
	    ChartUtilities.saveChartAsPNG(barChartFile, barChart, 400, 300);

	    PDImageXObject pdImage1 = PDImageXObject.createFromFile(barChartFile.getAbsolutePath(), document);
	    PDPageContentStream contentStream = new PDPageContentStream(document, page, PDPageContentStream.AppendMode.APPEND, true, true);
	    contentStream.drawImage(pdImage1, 50, 140);
	    contentStream.close();
	}

	private static void createPieChart(PDDocument document, ProductModel productModel, Path tempDir, PDPage page) throws IOException {
	    DefaultPieDataset pieDataset = new DefaultPieDataset();
	    pieDataset.setValue("Category 1", productModel.getEndLineNumber());//40
	    pieDataset.setValue("Category 2", productModel.getPriceQuantity());//60
	    pieDataset.setValue("Category 3", productModel.getNumberContentUnits());//20 - Package quantity

	    JFreeChart pieChart = ChartFactory.createPieChart("Pie Chart", pieDataset, true, true, false);
	    pieChart.setBackgroundPaint(java.awt.Color.getHSBColor(0.25f, 0.51f, 0.84f));

	    File pieChartFile = new File(tempDir.toFile(), "pie_chart.png");
	    ChartUtilities.saveChartAsPNG(pieChartFile, pieChart, 400, 300);

	    PDImageXObject pdImage2 = PDImageXObject.createFromFile(pieChartFile.getAbsolutePath(), document);
	    PDPageContentStream contentStream = new PDPageContentStream(document, page, PDPageContentStream.AppendMode.APPEND, true, true);
	    contentStream.drawImage(pdImage2, 500, 140);
	    contentStream.close();
	}

	private static File mergeFiles(File testPdfFile, File secFile, Path tempDir) throws IOException {
	    PDFMergerUtility pdfMerger = new PDFMergerUtility();
	    File mergedFile = new File(tempDir.toFile(), "Merged_File.pdf");
	    pdfMerger.setDestinationFileName(mergedFile.getAbsolutePath());
	    pdfMerger.addSource(testPdfFile);
	    pdfMerger.addSource(secFile);
	    pdfMerger.mergeDocuments();
	    return mergedFile;
	}

	private static void addContentsToPdf(File fileMerged, ProductModel productModel) throws IOException {
	    addBorder(fileMerged);
	    addHeaderTextContent(435, 520, fileMerged, productModel);
	    addSampleTextContent(220, 125, "Sample Text 1", fileMerged, productModel);
	    addSampleTextContent(660, 125, "Sample Text 2", fileMerged, productModel);
	    addTDateAndTime(fileMerged);
	    addDescriptiveTextContent(52, 80, DESCRIPTION, fileMerged, productModel);
	}

	
	
	private static void addHeaderTextContent(float x, float y, File fileMrged, ProductModel productModel) throws IOException {
		//File fileMrged = new File("/Users/I557467/Documents/Merged_File.pdf");
		PDDocument docMerged = PDDocument.load(fileMrged);
		PDPage pageFirst = docMerged.getPage(0);
		PDPageContentStream contentStreamMergedDoc = new PDPageContentStream(docMerged, pageFirst, AppendMode.APPEND,
				true, true);

		contentStreamMergedDoc.beginText();
		contentStreamMergedDoc.setFont(PDType1Font.TIMES_BOLD, 16);
		contentStreamMergedDoc.setLeading(14.5f);
		contentStreamMergedDoc.newLineAtOffset(x, y);
		contentStreamMergedDoc.setNonStrokingColor(java.awt.Color.BLACK);
		contentStreamMergedDoc.showText(productModel.getSupplierAlternativeAID());
		contentStreamMergedDoc.endText();
		contentStreamMergedDoc.close();
		docMerged.save(fileMrged);
		docMerged.close();

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

		docMerged.save(fileMrged);
		docMerged.close();

	}

	private static void addSampleTextContent(float x, float y, String text, File fileMrged, ProductModel productModel) throws IOException {
		PDDocument docMerged = PDDocument.load(fileMrged);
		PDPage pageFirst = docMerged.getPage(0);
		PDPageContentStream contentStreamMergedDoc = new PDPageContentStream(docMerged, pageFirst, AppendMode.APPEND,
				true, true);

		contentStreamMergedDoc.beginText();
		contentStreamMergedDoc.setFont(PDType1Font.TIMES_BOLD, 16);
		contentStreamMergedDoc.setLeading(14.5f);
		contentStreamMergedDoc.newLineAtOffset(x, y);
		contentStreamMergedDoc.setNonStrokingColor(java.awt.Color.BLACK);
		contentStreamMergedDoc.showText(productModel.getErpGroupSupplier());
		contentStreamMergedDoc.endText();
		contentStreamMergedDoc.close();
		docMerged.save(fileMrged);
		docMerged.close();

	}

	private static void addDescriptiveTextContent(float x, float y, String text,File fileMrged, ProductModel productModel) throws IOException {
		//File fileMrged = new File("/Users/I557467/Documents/Merged_File.pdf");
		PDDocument docMerged = PDDocument.load(fileMrged);
		PDPage pageFirst = docMerged.getPage(0);
		PDPageContentStream contentStreamMergedDoc = new PDPageContentStream(docMerged, pageFirst, AppendMode.APPEND,
				true, true);

		contentStreamMergedDoc.beginText();
		contentStreamMergedDoc.setFont(PDType1Font.HELVETICA, 12);
		contentStreamMergedDoc.setLeading(14.5f);
		contentStreamMergedDoc.newLineAtOffset(x, y);
		contentStreamMergedDoc.setNonStrokingColor(java.awt.Color.BLACK);
		contentStreamMergedDoc.showText(productModel.getManufacturerTypeDescription());
		contentStreamMergedDoc.newLine();
		contentStreamMergedDoc.showText(productModel.getManufacturerTypeDescription());
		
		contentStreamMergedDoc.endText();
		contentStreamMergedDoc.close();
		docMerged.save(fileMrged);
		docMerged.close();

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

	public static void drawLine(PDPageContentStream contentStream, String text, float lineWidth, float sx, float sy,
			float linePosition) throws IOException {
		// Calculate String width
		float stringWidth = 16 * 16 / 1000;
		float lineEndPoint = sx + stringWidth;

		// begin to draw our line
		contentStream.setLineWidth(lineWidth);
		contentStream.moveTo(sx, sy + linePosition);
		contentStream.lineTo(lineEndPoint, sy + linePosition);
		contentStream.stroke();
	}

	private static String getCurrentDate() {
		String pattern = "E, dd MMM yyyy HH:mm:ss z";
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		sdf.setTimeZone(TimeZone.getTimeZone("Asia/Riyadh"));
		String dateInString = sdf.format(new Date());
		return dateInString;
	}
	

}
