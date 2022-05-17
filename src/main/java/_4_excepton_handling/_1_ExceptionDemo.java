package _4_excepton_handling;

import java.io.FileNotFoundException;
import java.io.IOException;

// create our own exception
class MyException extends Exception {
    public MyException() {
        this("MyException occurred");
    }

    public MyException(String message) {
        super(message);
    }
}

class HTTPConnect {

    public static void send(int destination, String data, String partner) throws FileNotFoundException, IOException {
        System.out.println("Inside send");

        // configuring preconditions for `destination`
        // https://softwareengineering.stackexchange.com/a/15518
        if (destination < 0 || destination > 1) {
            throw new IllegalArgumentException();
        }

        if (destination == 0) throw new FileNotFoundException();
        else if (destination == 1) throw new IOException();

        System.out.println("End send");
    }
}

public class _1_ExceptionDemo {
    public static void main(String[] args) {
        System.out.println("Inside main");
        share(10);
        System.out.println("End main");
    }

    public static void share(int destination) {
        System.out.println("Inside share");

        try {
            HTTPConnect.send(destination, "Hello World", "www.google.lk");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("IOException occurred");
        } /*catch (Exception e) {
            System.out.println("Exception occurred");
        } catch (Throwable e) {
            System.out.println("Throwable occurred");
        } */ finally {
            System.out.println("Inside share's finally");
        }
        // FileNotFoundException < IOException < Exception < Throwable <- exception super classes in order

        System.out.println("End share");
    }
}

/*

Throwable

1. Exception

    1. IOException
        1. FileNotFoundException
        2. EOFException

    2. AWTException

    3. Runtime Exception
        1. NullPointerException
        2. ArrayOutOfBoundsException
        3. IllegalArgumentException <- constructors and methods invoke this exception to enforce
                                       preconditions on the parameters
        4. ClassCastException
        5. NumberFormatException

2. Error

    1. VirtualMachineError

    2. LinkageError
        1. NoClassDefFoundError

Exception vs Error: https://stackoverflow.com/a/5813695/10582056

*/
