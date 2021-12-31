package com.investsaudi.portal.core.service.utils;

import de.hybris.platform.core.servicelayer.data.PaginationData;

import java.io.Serializable;

public class PaginationUtils implements Serializable {

    public static PaginationData createPaginationData(int currentPage, int pageSize)
    {
        final PaginationData paginationData = new PaginationData();
        paginationData.setPageSize(pageSize);
        paginationData.setCurrentPage(currentPage);
        paginationData.setNeedsTotal(true);
        return paginationData;
    }
}
