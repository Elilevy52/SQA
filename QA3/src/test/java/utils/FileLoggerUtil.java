package utils;

import java.io.IOException;
import java.util.logging.*;

public class FileLoggerUtil {

    public static Logger getLogger(Class<?> clazz) {
        Logger logger = Logger.getLogger(clazz.getName());

        if (logger.getHandlers().length == 0) {
            try {          
                FileHandler fileHandler = new FileHandler("log.txt", true);
                fileHandler.setFormatter(new SimpleFormatter());
                fileHandler.setLevel(Level.FINE);

                ConsoleHandler consoleHandler = new ConsoleHandler();
                consoleHandler.setFormatter(new SimpleFormatter());
                consoleHandler.setLevel(Level.FINE);

                logger.addHandler(fileHandler);
                logger.addHandler(consoleHandler);

                logger.setUseParentHandlers(false); 
                logger.setLevel(Level.FINE);

            } catch (IOException e) {
                System.err.println("Failed to initialize FileHandler for logger: " + e.getMessage());
            }
        }

        return logger;
    }
}