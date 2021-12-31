package com.investsaudi.portal.core.dao.impl;

import com.google.common.collect.ImmutableMap;
import com.investsaudi.portal.core.dao.InvestSaudiWebinarDao;
import com.investsaudi.portal.core.model.InvestSaudiWebinarModel;
import de.hybris.platform.core.model.media.MediaModel;
import de.hybris.platform.servicelayer.internal.dao.DefaultGenericDao;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.servicelayer.search.SearchResult;

import javax.annotation.Nonnull;
import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static de.hybris.platform.servicelayer.util.ServicesUtil.validateParameterNotNull;

public class InvestSaudiWebinarDaoImpl extends DefaultGenericDao<InvestSaudiWebinarModel> implements InvestSaudiWebinarDao {

    private static final String MEDIA_FOLDER_QUALIFIER = "portal-media-email ";
    private static final String MEDIA_FOLDER = "mediaFolder";

    private static final String MEDIA_ALL_EXPORT_FOLDER_QUERY =
            "  SELECT {m:pk} " +
                    "  FROM {Media* AS m " +
                    "  JOIN MediaFolder AS mf ON {mf.pk} = {m.folder}} " +
                    "  WHERE {mf.qualifier} = ?" + MEDIA_FOLDER;
    @Resource
    private FlexibleSearchService flexibleSearchService;

    public InvestSaudiWebinarDaoImpl(String typecode) {
        super(typecode);
    }

    @Override
    public Optional<InvestSaudiWebinarModel> findWebinarByCode(String webinarCode) {

        validateParameterNotNull(webinarCode, "Webinar Code must not be null!");
        List<InvestSaudiWebinarModel> list = find(Collections.singletonMap(InvestSaudiWebinarModel.WEBINARCODE, (Object) webinarCode));
        return list.stream().findFirst();
    }

    @Override
    @Nonnull
    public SearchResult<MediaModel> getAllEmailMedias() {

        return find(MEDIA_ALL_EXPORT_FOLDER_QUERY);
    }

    @Nonnull
    private <E> SearchResult<E> find(@Nonnull final String query) {
        final FlexibleSearchQuery searchQuery = new FlexibleSearchQuery(query,
                ImmutableMap.of(MEDIA_FOLDER, MEDIA_FOLDER_QUALIFIER));
        return flexibleSearchService.search(searchQuery);
    }
}
