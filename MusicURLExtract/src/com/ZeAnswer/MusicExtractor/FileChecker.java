package com.ZeAnswer.MusicExtractor;

import java.io.*;

// In charge of operating on a single file. Given an input and output file,
// searches the input file for URLs (using URLchecker), formats the URLs (using URLFormatter) and prints to output file.

public class FileChecker {
    private final File outputFile;
    private final File inputFile;
    private final String inputName;
    private BufferedReader inputStream;
    private BufferedWriter outputStream;


    // Constructor. Takes 2 files (input, output) and sets corresponding fields.
    public FileChecker(File outputFile, File inputFile) {
        this.outputFile = outputFile;
        this.inputFile = inputFile;
        inputName = inputFile.getName();
    }

    // Writes to outputStream and flushes to make sure everything gets there correctly
    private void writeToOutput(String str) throws IOException {
        outputStream.write(str);
        outputStream.flush();
    }

    // Calls writeToOutput but with a newline at the end
    private void writeLineToOutput(String line) throws IOException {
        writeToOutput(line + "\n");
    }

    // Calls URLFormatter with the found url (which returns the structure "page name\n url"
    // Then sends the result to writeLineToOutput
    private void writeURL(String url) throws IOException {
        if (!url.equals("")) {
            writeLineToOutput(URLFormatter.URLFormat(url));
        }
    }

    // Goes through each line in the text file, uses URLchecker to see if it finds what we're looking for
    // sends the output (either a URL or empty string) to writeURL
    private void readFile() throws IOException {
        String line;
        String url;
        while ((line = inputStream.readLine()) != null) {
            url = URLchecker.checkLine4URL(line);
            writeURL(url);
        }
    }

    // String to appear before file content on output file
    private void writePre() throws IOException {
        writeLineToOutput(inputName + ":\n");
    }

    // String to appear after file content on output file
    private void writePost() throws IOException {
        writeLineToOutput("\n");
    }



    // Main action. Sets all the IO streams, prints progress (which file are we looking into now, found URL etc)
    // Write pre-string, readFile() contents and post-string, then closing streams and files
    public void checkFile() {
        try {
            FileReader inputReader = new FileReader(inputFile);
            FileWriter outputWriter = new FileWriter(outputFile, true);
            inputStream = new BufferedReader(inputReader);
            outputStream = new BufferedWriter(outputWriter);
            System.out.println("Searching for URLS in: " + inputName);
            writePre();
            readFile();
            writePost();
            System.out.println("Done processing file: " + inputName);
            inputStream.close();
            outputStream.close();
            outputWriter.close();
            inputReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
