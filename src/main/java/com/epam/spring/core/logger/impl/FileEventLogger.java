package com.epam.spring.core.logger.impl;

import com.epam.spring.core.domain.Event;
import com.epam.spring.core.logger.EventLogger;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;

@Component
public class FileEventLogger implements EventLogger {
    @Value("log.txt")
    private String filename;

    public FileEventLogger() {
    }

    public FileEventLogger(String filename) {
        this.filename = filename;
    }

    @PostConstruct
    public void init() throws IOException {
        File file = new File(filename);
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
