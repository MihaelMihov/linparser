package com.mihov.parser;

import java.io.*;

public class Printer {

    private static final String FILE_NAME = "C:\\Users\\Mihael\\Desktop\\export.lin";

    public static void appendFileToLinOutput(String lin, boolean appendTofile) throws IOException {
        FileWriter fileWriter = new FileWriter(FILE_NAME, appendTofile);
        PrintWriter printWriter = new PrintWriter(fileWriter);
        printWriter.print(lin);
        printWriter.close();
    }

}
