package com.sagia.indexer;

import de.hybris.platform.core.model.ItemModel;

import de.hybris.platform.solrfacetsearch.config.IndexOperation;
import de.hybris.platform.solrfacetsearch.indexer.IndexerBatchContext;
import de.hybris.platform.solrfacetsearch.indexer.exceptions.IndexerException;
import de.hybris.platform.solrfacetsearch.indexer.strategies.impl.DefaultIndexerBatchStrategy;
import java.util.Set;

import java.util.HashSet;

import org.apache.log4j.Logger;
import com.investsaudi.portal.core.model.OpportunityProductModel;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.net.ssl.HttpsURLConnection;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.util.Base64;

public class SagiaIndexerBatchStrategy extends DefaultIndexerBatchStrategy {

	private static final Logger LOG = Logger.getLogger(SagiaIndexerBatchStrategy.class);
	private static final String TOKEN_URL = "https://oauthasservices-lepnnvzpc6.sa1.hana.ondemand.com/oauth2/api/v1/token";
	private static final String CLIENT_ID = "af0abb4b-5c32-320d-8766-6cad1c618eaf";
	private static final String CLIENT_SECRET = "MISA2020Test";
	private static final String GRANT_TYPE = "client_credentials"; // assuming client credential grant type

	@Override
	protected void executeIndexerOperation(IndexerBatchContext batchContext)
			throws IndexerException, InterruptedException {
		LOG.info("===================== Inside SagiaIndexerBatchStrategy ======================");

		// Check if the operation is an UPDATE and print the product codes
		if (IndexOperation.UPDATE.equals(batchContext.getIndexOperation())) {
			LOG.info("===================== Inside SagiaIndexerBatchStrategy UPDATE ======================");
			Set<String> uniqueProductCodes = new HashSet<>();
			for (ItemModel item : batchContext.getItems()) {
				if (item instanceof OpportunityProductModel) {
					OpportunityProductModel product = (OpportunityProductModel) item;
					uniqueProductCodes.add(product.getCode());
				}
			}

			// Convert the set of product codes to a comma-separated string
			String productCodesStr = String.join(",", uniqueProductCodes);
			LOG.info("Unique opportunity codes are --> " + productCodesStr);

			// postToCPIEndpoint(productCodesStr);
			try {
				postOpportunityUpdate(uniqueProductCodes);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	public void postOpportunityUpdate(Set<String> codes) throws Exception {
		String accessToken = getAccessToken();

		URL url = new URL("https://e650024-iflmap.hcisbt.sa1.hana.ondemand.com/http/Commerce/C4C/OpportunityUpdate");
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Content-Type", "application/json");
		conn.setRequestProperty("Authorization", "Bearer " + accessToken);
		conn.setDoOutput(true);

		JsonObject mainObject = new JsonObject();
		JsonArray opportunityUpdate = new JsonArray();

		for (String code : codes) {
			JsonObject opportunity = new JsonObject();
			opportunity.addProperty("code", code);
			opportunity.addProperty("operationType", "UPDATE");
			opportunity.addProperty("timestamp", getCurrentDateTime());
			opportunityUpdate.add(opportunity);
		}

		mainObject.add("opportunityUpdate", opportunityUpdate);

		try (OutputStream os = conn.getOutputStream()) {
			byte[] input = mainObject.toString().getBytes("utf-8");
			os.write(input, 0, input.length);
		}

		int responseCode = conn.getResponseCode();
		LOG.info("POST Response Code :: " + responseCode);
	}

	private String getAccessToken() throws Exception {
		URL url = new URL(TOKEN_URL + "?grant_type=" + GRANT_TYPE);
		HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

		// Encode CLIENT_ID and CLIENT_SECRET in Base64 as per the Basic Authentication scheme
		String auth = CLIENT_ID + ":" + CLIENT_SECRET;
		String encodedAuth = Base64.getEncoder().encodeToString(auth.getBytes(StandardCharsets.UTF_8));
		conn.setRequestProperty("Authorization", "Basic " + encodedAuth);

		conn.setDoOutput(true);

		int responseCode = conn.getResponseCode();
		if (responseCode == HttpURLConnection.HTTP_OK) {
			String responseBody = new String(conn.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
			JsonObject jsonObject = new JsonParser().parse(responseBody).getAsJsonObject();
			return jsonObject.get("access_token").getAsString();
		} else {
			throw new RuntimeException("Failed to get OAuth2 token. Response code: " + responseCode);
		}
	}

	public String getCurrentDateTime() {
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyHHmmssSSSnnn");
		String formattedDateTime = now.format(formatter);
		return formattedDateTime;
	}

}
