package utils;

import org.junit.runner.Description;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunListener;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class TestListener extends RunListener {
    private static Logger logger = Logger.getLogger("TestAutomationLog");
    private static FileHandler fileHandler;

    static {
        try {
            fileHandler = new FileHandler("TestAutomationLog.log", true); 
            logger.addHandler(fileHandler);
            SimpleFormatter formatter = new SimpleFormatter();
            fileHandler.setFormatter(formatter);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void testStarted(Description description) {
        logger.info("Starting Test: " + description.getMethodName());
    }

    @Override
    public void testFinished(Description description) {
        logger.info("Finished Test: " + description.getMethodName());
    }

    @Override
    public void testFailure(Failure failure) {
        logger.severe("Test Failed: " + failure.getDescription().getMethodName() + " - " + failure.getMessage());
    }

    @Override
    public void testRunFinished(Result result) {
        logger.info("Test Run Finished. Total tests: " + result.getRunCount() + 
                    ", Failures: " + result.getFailureCount());
    }
}
