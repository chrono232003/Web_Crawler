package main;

import org.jsoup.HttpStatusException;
import java.net.MalformedURLException;

/**
 * This class is a generic exception handling class. since this project uses many try/catch blocks, found use in abstracting functionality.
 * the handle class with be static as to not have to create an instance for one method.
 * TODO: give specific errors unique functionality if needed. Now, they just display a user friendly error.
 */

public class ExceptionHandling {

    public static void handleMalformedURLException (String message, MalformedURLException e) {

        informUser(message, e);

    }

    public static void handleHttpStatusException (String message, HttpStatusException e) {

        informUser(message, e);

    }


    public static void handleException (String message, Exception e) {

        informUser(message, e);

    }

    private static void informUser(String message, Exception e) {
        System.out.println(message + ": " + e.getMessage());
    }

}
