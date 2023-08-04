package com.sap.ibso.eservices.storefront.util;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.PDPageContentStream.AppendMode;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFontFactory;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.PDFontSetting;
import org.apache.pdfbox.pdmodel.graphics.color.PDColor;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.general.PieDataset;

import de.hybris.platform.core.model.product.ProductModel;

import org.jfree.data.category.DefaultCategoryDataset;



import org.jfree.data.general.DefaultPieDataset;
import org.apache.pdfbox.multipdf.PDFMergerUtility;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.TimeZone;

public class SagiaPDFChartGenerator {
	//Test
	private static final float POINTS_PER_INCH = 72;
	private static final String DESCRIPTION = "This is a sample descriptive text for the pdf.This is a sample descriptive text for the pdf.This is a sample descriptive text for the pdf.";

	public static File generatePdfFile(ProductModel productModel, File secFile) throws IOException
	{
		File fileMerged;

		try {
			File file = new File("/Users/I557467/Documents/Test.pdf");
			// PDDocument.load(file);

			// Create a new PDF document
			PDDocument document = new PDDocument();
			document.save("/Users/I557467/Documents/Test.pdf");
			// PDRectangle rectangle = new PDRectangle(200, 1000, 1000, 1000);
			// PDPage page = new PDPage(PDRectangle.A4);
			PDPage page = new PDPage(new PDRectangle(960, 540));
			document.addPage(page);
			PDDocument.load(file);

			page = document.getPage(0);

			// Generate bar chart data
			DefaultCategoryDataset barDataset = new DefaultCategoryDataset();
			barDataset.addValue(productModel.getMaxOrderQuantity(), "Series 1", "Category 1");//50
			barDataset.addValue(productModel.getMinOrderQuantity(), "Series 1", "Category 2");//70
			barDataset.addValue(productModel.getOrderQuantityInterval(), "Series 2", "Category 1");//30
			barDataset.addValue(productModel.getStartLineNumber(), "Series 2", "Category 2");//90

			// Generate bar chart
			JFreeChart barChart = ChartFactory.createBarChart("Bar Chart", // chart title
					"Category", // x-axis label
					"Value", // y-axis label
					barDataset, // dataset
					PlotOrientation.HORIZONTAL, // chart orientation
					true, // include legend
					true, // include tooltips
					false // include URLs
			);
			// add colour background
			barChart.setBackgroundPaint(java.awt.Color.getHSBColor(0.25f, 0.51f, 0.84f));

			// Set dimensions and position of the bar chart
			int barChartWidth = 400;
			int barChartHeight = 300;
			int barChartX = 100;
			int barChartY = 500;

			// Convert the bar chart into an image
			File barChartFile = new File("/Users/I557467/Documents/bar_chart.png");
			ChartUtilities.saveChartAsPNG(barChartFile, barChart, barChartWidth, barChartHeight);

			// Embed the bar chart image in the PDF
//            PDImageXObject barChartImage = PDImageXObject.createFromFileByContent(barChartFile, document);
//            contentStream.drawImage(barChartImage, barChartX, barChartY, barChartWidth, barChartHeight);

			// Generate pie chart data
			DefaultPieDataset pieDataset = new DefaultPieDataset();
			pieDataset.setValue("Category 1", productModel.getEndLineNumber());//40
			pieDataset.setValue("Category 2", productModel.getPriceQuantity());//60
			pieDataset.setValue("Category 3", productModel.getNumberContentUnits());//20 - Package quantity

			// Generate pie chart
			JFreeChart pieChart = ChartFactory.createPieChart("Pie Chart", // chart title
					pieDataset, // dataset
					true, // include legend
					true, // include tooltips
					false // include URLs
			);
			// add colour background
			pieChart.setBackgroundPaint(java.awt.Color.getHSBColor(0.25f, 0.51f, 0.84f));

			// Set dimensions and position of the pie chart
			int pieChartWidth = 400;
			int pieChartHeight = 300;
			int pieChartX = 100;
			int pieChartY = 200;

			// Convert the pie chart into an image
			File pieChartFile = new File("/Users/I557467/Documents/pie_chart.png");
			ChartUtilities.saveChartAsPNG(pieChartFile, pieChart, pieChartWidth, pieChartHeight);

			// Saving Image to PDF
			PDImageXObject pdImage1 = PDImageXObject.createFromFile("/Users/I557467/Documents/bar_chart.png", document);
			PDImageXObject pdImage2 = PDImageXObject.createFromFile("/Users/I557467/Documents/pie_chart.png", document);

			PDPageContentStream contentStream = new PDPageContentStream(document, page);
			contentStream.drawImage(pdImage1, 50, 140);
			contentStream.drawImage(pdImage2, 500, 140);

			contentStream.close();
			document.save("/Users/I557467/Documents/Test.pdf");
			document.close();

			// Merge PDF Files
			PDFMergerUtility pdfMerger = new PDFMergerUtility();
			pdfMerger.setDestinationFileName("/Users/I557467/Documents/Merged_File.pdf");
			File file1 = new File("/Users/I557467/Documents/Test.pdf");
			//File file2 = new File("/Users/I557467/Documents/Bisha-International-K-12-Schools.pdf");
			File file2 = secFile;
			
			fileMerged = new File("/Users/I557467/Documents/Merged_File.pdf");
			pdfMerger.addSource(file1);
			pdfMerger.addSource(file2);
			pdfMerger.mergeDocuments();

			addBorder(fileMerged);
			addHeaderTextContent(435, 520,fileMerged,productModel);
			addSampleTextContent(220, 125, "Sample Text 1",fileMerged,productModel);
			addSampleTextContent(660, 125, "Sample Text 2",fileMerged,productModel);
			addTDateAndTime(fileMerged);
			addDescriptiveTextContent(52, 80, DESCRIPTION,fileMerged,productModel);

		}

		finally {
			System.out.println("In finally block");
		}
		return fileMerged;

	
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

		String dateText = getCurrentDate();
		contentStreamMergedDoc.setNonStrokingColor(java.awt.Color.BLACK);
		//contentStreamMergedDoc.showText("Header Text");
		contentStreamMergedDoc.showText(productModel.getSupplierAlternativeAID());
		contentStreamMergedDoc.endText();
		contentStreamMergedDoc.close();
		docMerged.save(fileMrged);
		docMerged.close();

	}

	private static void addTDateAndTime(File fileMrged) throws IOException {
		//File fileMrged = new File("/Users/I557467/Documents/Merged_File.pdf");
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
		//File fileMrged = new File("/Users/I557467/Documents/Merged_File.pdf");
		PDDocument docMerged = PDDocument.load(fileMrged);
		PDPage pageFirst = docMerged.getPage(0);
		PDPage pageSecond = docMerged.getPage(1);
		PDRectangle mediaBox = pageSecond.getMediaBox();
//		float width = mediaBox.getWidth();
//		float height = mediaBox.getHeight();
//		System.out.println("Width is ---> " + width + " and height is ----->" + height);
		PDPageContentStream contentStreamMergedDoc = new PDPageContentStream(docMerged, pageFirst, AppendMode.APPEND,
				true, true);

		contentStreamMergedDoc.beginText();
		contentStreamMergedDoc.setFont(PDType1Font.TIMES_BOLD, 16);
		contentStreamMergedDoc.setLeading(14.5f);
		contentStreamMergedDoc.newLineAtOffset(x, y);
		contentStreamMergedDoc.setNonStrokingColor(java.awt.Color.BLACK);
		//contentStreamMergedDoc.showText(text);
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
		//contentStreamMergedDoc.showText(text);
		contentStreamMergedDoc.showText(productModel.getManufacturerTypeDescription());
		contentStreamMergedDoc.newLine();
		//contentStreamMergedDoc.showText(text);
		contentStreamMergedDoc.showText(productModel.getManufacturerTypeDescription());
		
		contentStreamMergedDoc.endText();
		contentStreamMergedDoc.close();
		docMerged.save(fileMrged);
		docMerged.close();

	}
	

	private static void addBorder(File fileMrged) throws IOException {
		//File fileMrged = new File("/Users/I557467/Documents/Merged_File.pdf");
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
//		String dateInString = new SimpleDateFormat(pattern).format(new Date());
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		sdf.setTimeZone(TimeZone.getTimeZone("Asia/Riyadh"));
		String dateInString = sdf.format(new Date());
		return dateInString;
	}
	

}
