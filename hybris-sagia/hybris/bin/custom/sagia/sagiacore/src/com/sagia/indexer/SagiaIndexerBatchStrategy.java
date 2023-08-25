package com.sagia.indexer;

import de.hybris.platform.core.model.ItemModel;
import de.hybris.platform.solrfacetsearch.config.IndexOperation;
import de.hybris.platform.solrfacetsearch.indexer.IndexerBatchContext;
import de.hybris.platform.solrfacetsearch.indexer.exceptions.IndexerException;
import de.hybris.platform.solrfacetsearch.indexer.strategies.impl.DefaultIndexerBatchStrategy;
import java.util.Set;
import com.investsaudi.portal.core.model.OpportunityProductModel;
import com.sap.ibso.eservices.core.util.SagiaOpportunityUpdateNotifier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


public class SagiaIndexerBatchStrategy extends DefaultIndexerBatchStrategy {
	
	private static final Logger LOG = LoggerFactory.getLogger(SagiaIndexerBatchStrategy.class);
	private static final String OPERATION_TYPE_UPDATE = "UPDATE";


	@Override
	protected void executeIndexerOperation(IndexerBatchContext batchContext)
			throws IndexerException, InterruptedException {
		LOG.info("===================== Inside SagiaIndexerBatchStrategy ======================");

		if (IndexOperation.UPDATE.equals(batchContext.getIndexOperation())) {
		    LOG.info("===================== Inside SagiaIndexerBatchStrategy UPDATE ======================");
		    Map<String, OpportunityProductModel> opportunityMap = new HashMap<>();
		    
		    for (ItemModel item : batchContext.getItems()) {
		        if (item instanceof OpportunityProductModel) {
		            OpportunityProductModel product = (OpportunityProductModel) item;
		            
		            if (Objects.nonNull(product.getSystemOrigin()) && Objects.nonNull(product.getOpportunityVersionId()) && product.getSystemOrigin().equalsIgnoreCase("C4C")) {
						LOG.info("isWithdrawn value is: " + product.getIsWithdrawn());
						opportunityMap.put(product.getCode(), product);
					}
		        }
		    }

		    Set<String> uniqueProductCodes = opportunityMap.keySet();
		    String productCodesStr = String.join(",", uniqueProductCodes);
		    LOG.info("Unique opportunity codes are --> " + productCodesStr);

		    try {
		        SagiaOpportunityUpdateNotifier sagiaOpportunityUpdateNotifier = new SagiaOpportunityUpdateNotifier();
		        sagiaOpportunityUpdateNotifier.postOpportunityUpdate(opportunityMap, OPERATION_TYPE_UPDATE);
		    } catch (Exception e) {
		        LOG.error("Opportunity Update to SCPI failed with error: ", e);
		    }
		}
		// Continue with the normal operation
        super.executeIndexerOperation(batchContext);
	}

	

}
