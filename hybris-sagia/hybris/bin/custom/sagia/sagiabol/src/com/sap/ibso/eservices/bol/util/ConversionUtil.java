package com.sap.ibso.eservices.bol.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class ConversionUtil
{
    private static final Logger LOGGER = LogManager.getLogger(ConversionUtil.class);

    /**
     * Avoids instance creation.
     */
    private ConversionUtil() {}

    /**
     * Adds <code>length - s.length</code> leading zeros as prefix to string <code>s</code> if the following condition hold:
     * <code>s != null</code> and <code>s.length < length</code>. Otherwise <code>s</code> is returned unchanged.
     *
     * @param s the string to be prefixed with leading zeros
     * @param length the resulting prefixed string length
     * @return the prefixed string or <code>s</code> (depending whether the above described condition holds)
     */
    public static String addLeadingZeros(String s, int length)
    {
        // Check conditions before prefixing s
        if (s == null || s.length() >= length)
        {
            return s;
        }

        StringBuilder b = new StringBuilder(length);
        for (int i = s.length(); i < length; i++)
        {
            b.append('0');
        }

        return b.append(s).toString();
    }

    /**
     * Removes leading zeros from a string.
     * Note that strings with less than 2 characters are returned unchanged as there are no leading zeros in such cases.
     * In particular, 0 is returned as 0.
     *
     * @param s the string to be shortened by leading zeros
     * @return the string without leading zeros
     */
    public static String removeLeadingZeros(String s)
    {
        if (!StringUtils.hasLength(s))
        {
            return s;
        }

        String result = s;
        while (result.length() > 1 && result.charAt(0) == '0')
        {
            result = result.substring(1);
        }

        return result;
    }

    /**
     * Converts a boolean value into a string flag.
     *
     * @param b the boolean value
     * @return "X" if b is true, " " (space) otherwise
     */
    public static String toFlag(boolean b)
    {
        return b ? "X" : " ";
    }

    /**
     * Returns a local date instance for a date string in format "yyyy-MM-dd". In case the date string is either
     * {@code null}, "0" or has not the expected format "yyyy-MM-dd" then {@code null} is returned.
     *
     * @param datsDateString
     *           the date string (which corresponds to ABAP DATS data type)
     * @return the local date
     */
    public static LocalDate toLocalDate(String datsDateString)
    {
        try
        {
            return datsDateString == null || "0".equals(datsDateString) ? null : LocalDate.parse(datsDateString, DateTimeFormatter.ISO_LOCAL_DATE);
        }
        catch (DateTimeParseException e)
        {
            LOGGER.error("Date to be parsed is not an ABAP DATS date string", e);
            return null;
        }
    }
}
