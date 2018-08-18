package main;

import java.net.MalformedURLException;

/**
 * This class is a generic exception handling class. since this project uses many try/catch blocks, found use in abstracting functionality.
 * the handle class with be static as to not have to create an instance for one method.
 */

public class ExceptionHandling {

    public static void handleMalformedURLException (String message, MalformedURLException e) {

        System.out.println(message);
        e.printStackTrace();

    }

    public static void handleException (String message, Exception e) {

        System.out.println(message);
        e.printStackTrace();

    }

}
