package com.ZeAnswer.MusicExtractor;

import java.io.File;
import java.io.IOException;

public class Main {
    // generate output file using current time
    // Iterate the files in TextSources and for each, run fileChecker
    public static void main(String[] args) {
        FileIterator fileIterator = new FileIterator();
        String currentTime = String.valueOf(System.currentTimeMillis());
	    File outFile = new File("./OutputFiles/output_"+currentTime+".txt");
        FileChecker fileChecker;
	    try {
            outFile.createNewFile();
            while (fileIterator.hasNext()){
                fileChecker = new FileChecker(outFile, fileIterator.next());
                fileChecker.checkFile();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
