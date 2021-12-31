package com.sap.ibso.eservices.sagiaservices.converters;

import com.sap.ibso.eservices.core.sagia.services.SagiaFormatProvider;
import com.sap.ibso.eservices.sagiaservices.data.zclassification.ZListData;
import com.sap.ibso.eservices.sagiaservices.services.ODataModel;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoField;

/**
 *
 */
public class ClassificationUpgradePopulator extends ODataPopulator<ZListData> {
	private static final Logger LOG = Logger.getLogger(ClassificationUpgradePopulator.class);

	private SagiaFormatProvider sagiaFormatProvider;
	@Override
	public void populate(ODataModel model, ZListData zListData) {
		super.populate(model, zListData, SagiaPropertyNamingStrategy.ALL_TO_UPPER_CASE);
		String date = zListData.getDATE();
		if(StringUtils.isNotBlank(date)) {
			LocalDateTime formattedDate = formatDate(date);
			if(formattedDate != null) {
				zListData.setSAGIADATE(sagiaFormatProvider.getLocalizedDateTimeData(formattedDate));
			}
		}

	}

	/**
	 * @return
	 */
	public SagiaFormatProvider getSagiaFormatProvider() {
		return sagiaFormatProvider;
	}

	/**
	 * @param sagiaFormatProvider
	 */
	public void setSagiaFormatProvider(SagiaFormatProvider sagiaFormatProvider) {
		this.sagiaFormatProvider = sagiaFormatProvider;
	}

	private LocalDateTime formatDate(String date) {
		LocalDateTime dateTime = null;
		try {
			DateTimeFormatter formatter = new DateTimeFormatterBuilder().appendPattern("uuuuMMdd")
					.parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
					.parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
					.parseDefaulting(ChronoField.SECOND_OF_MINUTE, 0)
					.toFormatter();
			dateTime = LocalDateTime.parse(date, formatter);
		} catch (DateTimeParseException dateException) {
			LOG.error("Date object could not be parsed!", dateException);
		}
		return dateTime;
	}
	}