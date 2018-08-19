package main;

import org.jsoup.HttpStatusException;
import java.net.MalformedURLException;

/**
 * This class is a generic exception handling class. since this project uses many try/catch blocks, found use in abstracting functionality.
 * the handle class with be static as to not have to create an instance for one method.
 */

public class ExceptionHandling {

    public static void handleMalformedURLException (String message, MalformedURLException e, Boolean hardFail) {



        System.out.println(message);


        if (hardFail) {
            System.exit(1);
        }

    }

    public static void handleHttpStatusException (String message, HttpStatusException e) {

        System.out.println(message);

    }


    public static void handleException (String message, Exception e) {

        System.out.println(message);
        e.printStackTrace();

    }

}
