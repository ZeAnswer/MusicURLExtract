package com.ZeAnswer.MusicExtractor;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;

// given a URL "example.url" grabs the website title "Example Title" and returns the format
// "Example Title"
// "example.url"
public class URLFormatter {
    public static String URLFormat(String url){
        InputStream response = null;
        String fullReturnString = "";
        if (!(url.contains("http://") || url.contains("https://"))){ // we want our URL to start with https://
            url = "https://"+url;
        }
        url = url.replace("http:", "https:"); // for some reason http:// doesn't work with this method
        try {
            response = new URL(url).openStream();
            Scanner scanner = new Scanner(response);
            String responseBody = scanner.useDelimiter("\\A").next();
            try {
                fullReturnString = responseBody.substring(responseBody.indexOf("<title>") + 7, responseBody.indexOf("</title>")) + " - \n" + url + "\n";
            } catch (StringIndexOutOfBoundsException ex){
                ex.printStackTrace();
                fullReturnString = "ERROR: UNKNOWN TITLE -\n" + url + "\n"; // If grabbing the title went wrong, use instead
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                response.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            finally{
                return fullReturnString;
            }
        }
    }
}
