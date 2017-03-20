package com.epam.spring.core.logger.impl;

import com.epam.spring.core.domain.Event;
import com.epam.spring.core.logger.EventLogger;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class FileEventLogger implements EventLogger {
    private String filename;
    private File file;

    public FileEventLogger(String filename) {
        this.filename = filename;
    }

    public void init() throws IOException {
        this.file = new File(filename);
        if (!(file.canWrite())) {
            throw new IOException("Cannot write to file " + filename);
        }
    }

    public void logEvent(Event event) {
        try {
            FileUtils.writeStringToFile(new File(filename),"\n" + event.toString(),true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
