package _6_io;

import java.io.*;
import java.util.Date;

public class _5_Serialization {

    // `transient` -> keyword used to mark variable as not to be serialized
    transient String doNotSerialize = "keyword \"transient\" used to mark variable as not to be serialized";

    public static void main(String[] args) {


        String absoluteFilePath = "F:\\Java\\src\\io\\Serialization\\object.ser";

        // both following objects are serializable as they implement `java.io.Serializable` interface
        String someString = "Hello World";
        Date now = new Date();

        // Serialization
        try (ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(absoluteFilePath)))) {

            // FileOutputStream is the class which actually writes to a file
            // BufferedOutputStream is used to make the process faster by buffering <- optional
            // ObjectOutputStream is the class which converts objects to writable format
            // .ser -> is just to indicate this is a file with serialized object

            // Writing object to a file
            out.writeObject(someString);
            out.writeObject(now);

        } catch (IOException e) {
            e.printStackTrace();
        }

        // DeSerialization
        try (ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(new FileInputStream(absoluteFilePath)))) {

            // reading object from a file
            String deSerializedSomeString = (String) in.readObject();
            Date deSerializedDate = (Date) in.readObject();

            System.out.println(deSerializedSomeString);
            System.out.println(deSerializedDate.toString());

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}