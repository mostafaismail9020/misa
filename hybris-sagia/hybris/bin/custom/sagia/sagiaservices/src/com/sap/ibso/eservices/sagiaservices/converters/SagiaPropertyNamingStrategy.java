package com.sap.ibso.eservices.sagiaservices.converters;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import com.fasterxml.jackson.databind.introspect.AnnotatedMethod;

import java.util.StringTokenizer;

/**
 *
 */
public class SagiaPropertyNamingStrategy extends PropertyNamingStrategy {

    public static final PropertyNamingStrategy UPPER_SNAKE_CASE = new SagiaPropertyNamingStrategy.UpperSnakeCaseStrategy();
    public static final PropertyNamingStrategy PASCAL_SNAKE_CASE = new SagiaPropertyNamingStrategy.PascalSnakeCaseStrategy();
    public static final PropertyNamingStrategy ALL_TO_UPPER_CASE = new AllUpperCaseStrategy();

    public static class AllUpperCaseStrategy extends PropertyNamingStrategy {
        @Override
        public String nameForGetterMethod(MapperConfig<?> config, AnnotatedMethod method, String defaultName) {
            return defaultName.toUpperCase();
        }

        @Override
        public String nameForSetterMethod(MapperConfig<?> config, AnnotatedMethod method, String defaultName) {
            return defaultName.toUpperCase();
        }

    }

    public static class UpperSnakeCaseStrategy extends PropertyNamingStrategyBase {

        public String translate(String input) {

            return input != null ? getResult(input) : null;
        }

        private String getResult(String input) {

            int resultLength = 0;
            int length = input.length();
            StringBuilder result = new StringBuilder(length * 2);
            boolean wasPrevTranslated = false;

            for (int i = 0; i < length; ++i) {
                char c = input.charAt(i);
                if (i == 0 && c == '_') {
                    continue;
                }
                if (Character.isUpperCase(c)) {
                    if (!wasPrevTranslated && resultLength > 0 && result.charAt(resultLength - 1) != '_') {
                        result.append('_');
                        ++resultLength;
                    }

                    c = Character.toLowerCase(c);
                    wasPrevTranslated = true;
                } else {
                    wasPrevTranslated = false;
                }
                result.append(c);
                ++resultLength;

            }
            return resultLength > 0 ? result.toString().toUpperCase() : input.toUpperCase();
        }
    }

    public static class PascalSnakeCaseStrategy extends PropertyNamingStrategyBase
    {
        @Override
        public String translate(String input)
        {
            StringTokenizer st = new StringTokenizer(input,"_");

            int tokens = st.countTokens();

            if(tokens <= 1)
            {
                String s = st.nextToken();
                return s.replaceFirst(s.substring(0,1),s.substring(0,1).toUpperCase());
            }

            StringBuilder sb = new StringBuilder(input.length());
            while(st.hasMoreTokens())
            {
                String s = st.nextToken().toLowerCase();
                s = s.replaceFirst(s.substring(0,1),s.substring(0,1).toUpperCase());
                sb.append(s).append("_");
            }
            sb.deleteCharAt(sb.length() - 1);

            return sb.toString();
        }
    }
}

