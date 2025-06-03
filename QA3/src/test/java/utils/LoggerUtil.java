package utils;

import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class LoggerUtil {

    public static Logger getLogger(Class<?> clazz) {
        Logger logger = Logger.getLogger(clazz.getName());

        if (logger.getHandlers().length == 0) {
        	ConsoleHandler handler = new ConsoleHandler();
        	handler.setFormatter(new ColorConsoleFormatter());
        	handler.setLevel(Level.FINE);
            logger.addHandler(handler);
        }
        logger.setUseParentHandlers(false);
        logger.setLevel(Level.FINE);
        return logger;
    }
} 
