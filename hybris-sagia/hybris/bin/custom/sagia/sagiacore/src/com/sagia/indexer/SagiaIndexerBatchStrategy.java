package com.sagia.indexer;

import de.hybris.platform.apiregistryservices.model.ConsumedOAuthCredentialModel;
import de.hybris.platform.core.model.ItemModel;

import de.hybris.platform.solrfacetsearch.config.IndexOperation;
import de.hybris.platform.solrfacetsearch.indexer.IndexerBatchContext;
import de.hybris.platform.solrfacetsearch.indexer.exceptions.IndexerException;
import de.hybris.platform.solrfacetsearch.indexer.strategies.impl.DefaultIndexerBatchStrategy;
import java.util.Set;

import java.util.HashSet;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

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

import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.servicelayer.search.SearchResult;
import de.hybris.platform.servicelayer.config.ConfigurationService;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;


public class SagiaIndexerBatchStrategy extends DefaultIndexerBatchStrategy {
	
	@Autowired
	private FlexibleSearchService flexibleSearchService;
	@Autowired
    private ConfigurationService configurationService;

	private static final Logger LOG = Logger.getLogger(SagiaIndexerBatchStrategy.class);
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

		URL url = getEndpointUrl();
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

	
	public URL getEndpointUrl() throws Exception {
        String endpointUrl = configurationService.getConfiguration().getString("sagia.scpi.oppotunity.update.endpoint");
        return new URL(endpointUrl);
    }



	private String getAccessToken() throws Exception {
	    ConsumedOAuthCredentialModel consumedOAuthCredential = getOAuthCredentialById("scpiOauthCredential");
	    
	    if (consumedOAuthCredential == null) {
	        throw new RuntimeException("Unable to find ConsumedOAuthCredential with id scpiOauthCredential");
	    }
	    
	    String tokenUrl = consumedOAuthCredential.getOAuthUrl();
	    String clientId = consumedOAuthCredential.getClientId();
	    String clientSecret = consumedOAuthCredential.getClientSecret();  // Assuming you have getter for this too

	    URL url = new URL(tokenUrl + "?grant_type=" + GRANT_TYPE);
	    HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
	    conn.setRequestMethod("POST");
	    conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

	    String auth = clientId + ":" + clientSecret;
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

	private ConsumedOAuthCredentialModel getOAuthCredentialById(String id) {
	    String queryStr = "SELECT {c:pk} FROM {ConsumedOAuthCredential AS c} WHERE {c:id} = ?id";
	    FlexibleSearchQuery query = new FlexibleSearchQuery(queryStr);
	    query.addQueryParameter("id", id);
	    
	    SearchResult<ConsumedOAuthCredentialModel> result = flexibleSearchService.search(query);
	    
	    return result.getResult().stream().findFirst().orElse(null);
	}

	
	

	public String getCurrentDateTime() {
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyHHmmssSSSnnn");
		String formattedDateTime = now.format(formatter);
		return formattedDateTime;
	}

}
