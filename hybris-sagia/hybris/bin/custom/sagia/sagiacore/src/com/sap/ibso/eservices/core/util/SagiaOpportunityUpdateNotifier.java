package com.sap.ibso.eservices.core.util;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.Map;
import javax.net.ssl.HttpsURLConnection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.investsaudi.portal.core.model.OpportunityProductModel;
import de.hybris.platform.apiregistryservices.model.ConsumedOAuthCredentialModel;
import de.hybris.platform.core.Registry;
import de.hybris.platform.servicelayer.config.ConfigurationService;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.servicelayer.search.SearchResult;

public class SagiaOpportunityUpdateNotifier {

	private static final Logger LOG = LoggerFactory.getLogger(SagiaOpportunityUpdateNotifier.class);
	private static final String GRANT_TYPE = "client_credentials";
	private static final String OPPORTUNITY_UPDATE_ENDPOINT = "sagia.scpi.oppotunity.update.endpoint";
	private static final String OPERATION_TYPE_WITHDRAWN = "WITHDRAWN";

	final ConfigurationService configurationService = (ConfigurationService) Registry.getApplicationContext()
			.getBean("configurationService");

	final FlexibleSearchService flexibleSearchService = (FlexibleSearchService) Registry.getApplicationContext()
			.getBean("flexibleSearchService");

	public void postOpportunityUpdate(Map<String, OpportunityProductModel> opportunityMap, String operationType)
			throws Exception {
		String accessToken = getAccessToken();

		URL url = getEndpointUrl();
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Content-Type", "application/json");
		conn.setRequestProperty("Authorization", "Bearer " + accessToken);
		conn.setDoOutput(true);

		JsonObject mainObject = new JsonObject();
		JsonArray opportunityUpdate = new JsonArray();

		for (Map.Entry<String, OpportunityProductModel> entry : opportunityMap.entrySet()) {
			String code = entry.getKey();
			String opportunityVersionID = entry.getValue().getOpportunityVersionId();
			OpportunityProductModel opportunityProductModel = entry.getValue();

			JsonObject opportunity = new JsonObject();
			opportunity.addProperty("code", code);
			opportunity.addProperty("opportunityVersionID", opportunityVersionID);
			opportunity.addProperty("operationType",
				    opportunityProductModel.getIsWithdrawn() == null
				        ? operationType // When isWithdrawn is null, use the provided operationType
				        : opportunityProductModel.getIsWithdrawn()
				            ? OPERATION_TYPE_WITHDRAWN // When isWithdrawn is true
				            : operationType // When isWithdrawn is false
				);
			opportunity.addProperty("timestamp", getCurrentDateTime());
			opportunityUpdate.add(opportunity);

		}

		if (opportunityUpdate.size() > 0) {
			mainObject.add("opportunityUpdate", opportunityUpdate);
			LOG.info("Main Object is not null with string value as:: " + mainObject.toString());
			try (OutputStream os = conn.getOutputStream()) {
				byte[] input = mainObject.toString().getBytes("utf-8");
				os.write(input, 0, input.length);
			}

			int responseCode = conn.getResponseCode();
			LOG.info("POST Response Code :: " + responseCode);

			// Close the connection
			conn.disconnect();
		}

		else {
			LOG.info("opportunityUpdate object is empty ");
			conn.disconnect();

		}
	}

	public URL getEndpointUrl() throws Exception {
		String endpointUrl = configurationService.getConfiguration().getString(OPPORTUNITY_UPDATE_ENDPOINT);
		return new URL(endpointUrl);
	}

	private String getAccessToken() throws Exception {
		ConsumedOAuthCredentialModel consumedOAuthCredential = getOAuthCredentialById("scpiOauthCredential");

		if (consumedOAuthCredential == null) {
			throw new RuntimeException("Unable to find ConsumedOAuthCredential with id scpiOauthCredential");
		}

		String tokenUrl = consumedOAuthCredential.getOAuthUrl();
		String clientId = consumedOAuthCredential.getClientId();
		String clientSecret = consumedOAuthCredential.getClientSecret();

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
