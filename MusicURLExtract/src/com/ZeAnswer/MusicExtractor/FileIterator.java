package com.ZeAnswer.MusicExtractor;

import java.io.File;
import java.util.Iterator;

// simple iterator for files in ./TextSources
public class FileIterator implements Iterator<File> {
    private final String dirPath = "./TextSources";
    private File dir;
    private File[] dirListing;
    private int fileCount = 0;
    private int curFile = 0;

    public FileIterator() {
        dir = new File(dirPath);
        dirListing = dir.listFiles();
        if (dirListing != null){
            fileCount = dirListing.length;
        }
    }

    @Override
    public boolean hasNext() {
        return curFile < fileCount;
    }

    @Override
    public File next() {
        if (hasNext()){
            return dirListing[curFile++];
        }
        return null;
    }
}
