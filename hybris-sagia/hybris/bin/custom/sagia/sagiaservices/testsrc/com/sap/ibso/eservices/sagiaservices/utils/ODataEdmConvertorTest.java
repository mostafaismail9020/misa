package com.sap.ibso.eservices.sagiaservices.utils;

import de.hybris.bootstrap.annotations.UnitTest;
import org.apache.olingo.odata2.api.edm.EdmElement;
import org.apache.olingo.odata2.api.edm.EdmException;
import org.apache.olingo.odata2.api.edm.EdmFacets;
import org.apache.olingo.odata2.api.edm.EdmType;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.math.BigInteger;
import java.util.Calendar;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Convert object value into well typed, well formatted OData value.
 */
@UnitTest
public class ODataEdmConvertorTest
{

	ODataEdmConvertor oDataEdmConvertor =  Mockito.spy(new ODataEdmConvertor());

	@Mock
	EdmElement property;

	@Mock
	EdmType type;

	@Mock
	EdmFacets edmFacets;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void shoudConvertEdmDateTime() throws EdmException {
		MockitoAnnotations.initMocks(this);
		when(type.getName()).thenReturn("DateTime");
		when(property.getType()).thenReturn(type);
		Object value = Calendar.getInstance().getTime();
		oDataEdmConvertor.convertEdm(value, property);
		value = "2016-01-21T14:33:17.354-0500";
		oDataEdmConvertor.convertEdm(value, property);
		value = "06.12.2016 19:45:17-05:00";
		oDataEdmConvertor.convertEdm(value, property);
		value = "03.12.2015 12:00:00";
		oDataEdmConvertor.convertEdm(value, property);
		verify(oDataEdmConvertor, Mockito.times(4)).convertEdmDateTime(value, property);
	}

	@Test
	public void shoudConvertEdmDateTimeOffset() throws EdmException {
		MockitoAnnotations.initMocks(this);
		when(type.getName()).thenReturn("DateTimeOffset");
		when(property.getType()).thenReturn(type);
		Object value = Calendar.getInstance().getTime();
		oDataEdmConvertor.convertEdm(value, property);
		verify(oDataEdmConvertor, Mockito.times(1)).convertEdmDateTimeOffset(value, property);
	}

	@Test
	public void shoudConvertEdmDecimal() throws EdmException {
		MockitoAnnotations.initMocks(this);
		when(type.getName()).thenReturn("Decimal");
		when(property.getType()).thenReturn(type);
		when(property.getFacets()).thenReturn(edmFacets);
		Object value = new BigInteger("10000000");
		oDataEdmConvertor.convertEdm(value, property);
		verify(oDataEdmConvertor, Mockito.times(1)).convertEdmDecimal(value, property);
	}

	@Test
	public void shoudConvertEdmInt32() throws EdmException {
		MockitoAnnotations.initMocks(this);
		when(type.getName()).thenReturn("Int16");
		when(property.getType()).thenReturn(type);
		Object value = Calendar.getInstance().getTime();
		oDataEdmConvertor.convertEdm(value, property);
		verify(oDataEdmConvertor, Mockito.times(1)).convertEdmInt32(value, property);
	}

	@Test
	public void shoudConvertEdmString() throws EdmException {
		MockitoAnnotations.initMocks(this);
		when(type.getName()).thenReturn("String");
		when(property.getType()).thenReturn(type);
		Object value = Calendar.getInstance().getTime();
		oDataEdmConvertor.convertEdm(value, property);
		verify(oDataEdmConvertor, Mockito.times(1)).convertEdmString(value, property);
	}
}
