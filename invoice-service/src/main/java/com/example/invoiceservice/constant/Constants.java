package com.example.invoiceservice.constant;

import java.io.File;

public class Constants {

    public static final String ROOT_PATH = "/home/user/bin/projects/work/a-system-invoice-system/invoice-service";
    public static final String DIRECTORY_FOR_UPLOADED_FILES = "loaded/";
    public static final String FILE_PATH = ROOT_PATH + File.separator + DIRECTORY_FOR_UPLOADED_FILES;
    public static final String DATE_PATTERN = "dd.MM.yyyy";
    public static final String TIME_PATTERN = "HH:mm:ss";
    public static final String DATE_TIME_PATTERN = DATE_PATTERN + " " + TIME_PATTERN;

    public static final String LOG_UPLOAD_REJECTED = "Upload rejected. File {} isn't uploaded. Cause: {}";



}
