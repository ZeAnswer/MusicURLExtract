package com.ZeAnswer.MusicExtractor;


// Holds an array of URLs to search for. Given a string, searches for a matching URL.
// If found, returns it. else returns empty string.

public class URLchecker {

    // Array of URL formats to search for
    private static final String[] URLtypes = {
            "youtube.com",
            "youtu.be",
            "spotify.com"
    };

    /* gets a line through checkLine4URL and returns a url if it exists */
    public static String checkLine4URL(String line){
        String[] tokens = line.split(" ");
        for (String token : tokens){
            for (String url : URLtypes){
                if (token.toLowerCase().contains(url)){
                    System.out.println("Found url! " + token);
                    return token;
                }
            }

        }
        return "";
    }
}
