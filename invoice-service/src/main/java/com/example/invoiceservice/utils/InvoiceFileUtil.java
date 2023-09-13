package com.example.invoiceservice.utils;

import com.example.invoiceservice.exception.InvalidFileNameException;

import java.util.HashMap;
import java.util.Map;

public class InvoiceFileUtil {

    public static final String YEAR = "year";
    public static final String MONTH = "month";
    public static final String DAY_OF_MONTH = "day of month";

    public static Integer getInvoiceNumber(String invoiceFileName) {
        return Integer.parseInt(fileNameToArray(invoiceFileName)[4]);
    }

    public static Map<String, Integer> getInvoiceDate(String invoiceFileName) {
        Map<String, Integer> map = new HashMap<>(3);

        var year = Integer.parseInt(fileNameToArray(invoiceFileName)[1]);

        var month = Integer.parseInt(fileNameToArray(invoiceFileName)[2]);

        var day = Integer.parseInt(fileNameToArray(invoiceFileName)[3]);
        map.put(YEAR, year);
        map.put(MONTH, month);
        map.put(DAY_OF_MONTH, day);
        return map;
    }

    public static Long getCompanyExternalId(String invoiceFileName) {
        char[] array = fileNameToArray(invoiceFileName)[0].toCharArray();
        for (char c : array) {
            if (!Character.isDigit(c)) {
                throw new InvalidFileNameException("Company external ID can consist only of numbers");
            }
        }
        return Long.parseLong(fileNameToArray(invoiceFileName)[0]);
    }

    public static String[] fileNameToArray(String invoiceFileName) {
        return invoiceFileName.substring(0, invoiceFileName.lastIndexOf('.')).split("_");
    }
}
