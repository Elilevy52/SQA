package utils;

import java.util.logging.Formatter;
import java.util.logging.LogRecord;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ColorConsoleFormatter extends Formatter {
    private static final String RESET = "\u001B[0m";
    private static final String GREEN = "\u001B[32m";
    private static final String YELLOW = "\u001B[33m";
    private static final String CYAN = "\u001B[36m";
    private static final String RED = "\u001B[31m";

    private final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Override
    public String format(LogRecord record) {
        String color;
        switch (record.getLevel().getName()) {
            case "INFO":
                color = GREEN;
                break;
            case "FINE":
                color = CYAN;
                break;
            case "WARNING":
                color = YELLOW;
                break;
            case "SEVERE":
                color = RED;
                break;
            default:
                color = RESET;
        }

        return String.format("%s[%s] [%s] %s%s%n",
                color,
                dateFormat.format(new Date(record.getMillis())),
                record.getLevel().getName(),
                formatMessage(record),
                RESET
        );
    }
}
