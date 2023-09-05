import de.hybris.platform.core.model.product.ProductModel
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery
import de.hybris.platform.servicelayer.search.SearchResult
import de.hybris.platform.servicelayer.model.ModelService
import de.hybris.platform.servicelayer.search.impl.DefaultFlexibleSearchService
import de.hybris.platform.catalog.enums.ArticleApprovalStatus
import de.hybris.platform.servicelayer.exceptions.ModelSavingException
import org.apache.log4j.Logger

final Logger LOG = Logger.getLogger(this.class.name)

// Dependency injection for required services
final DefaultFlexibleSearchService flexibleSearchService = spring.getBean("flexibleSearchService")
final ModelService modelService = spring.getBean("modelService")

String queryStr = """
    SELECT {op:pk}
    FROM {OpportunityProduct AS op 
          JOIN CatalogVersion AS cv ON {op:catalogVersion} = {cv:pk}
          JOIN Catalog AS c ON {cv:catalog} = {c:pk}}
    WHERE ({op:systemOrigin} != 'C4C')
    AND {cv:version} = 'Staged'
    AND {c:id} = 'sagiaProductCatalog'
"""

FlexibleSearchQuery query = new FlexibleSearchQuery(queryStr)

try {
    SearchResult<ProductModel> results = flexibleSearchService.search(query)
    
    LOG.info("Total OpportunityProduct items fetched: ${results.getTotalCount()}")

    if (results.getTotalCount() == 0) {
        LOG.info("No Staged OpportunityProduct items in 'sagiaProductCatalog' found with a systemOrigin different from 'C4C'.")
        return
    }

    List<ProductModel> productList = results.getResult()
    productList.each { product ->
        product.setApprovalStatus(ArticleApprovalStatus.UNAPPROVED)
        modelService.save(product)
        LOG.info("Staged OpportunityProduct in 'sagiaProductCatalog' with PK: ${product.getPk()} has been set to UNAPPROVED.")
    }
} catch (ModelSavingException e) {
    LOG.error("Error unapproving Staged OpportunityProduct items in 'sagiaProductCatalog'.", e)
}
