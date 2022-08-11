package tutorial.learn.core._6_io;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class _4_File_Class {
    public static void main(String[] args) throws IOException {

        // create a file object
        File file = new File("someRandomText.txt");

        // creates a new, empty file named by this abstract pathname if and only if a file with this name does not yet exist.
        file.createNewFile();

        // Returns the name of the file or directory denoted by this abstract pathname.
        file.getName(); // -> someRandomText.txt

        // Converts this abstract pathname into a pathname string.
        file.getPath(); // -> someRandomText.txt

        // Returns the absolute pathname string of this abstract pathname.
        file.getAbsolutePath(); // -> C:\Users\DELL\Documents\Tutorials\Java\someRandomText.txt

        // Returns the pathname string of this abstract pathname’s parent.
        file.getParent(); // -> null

        // Tests whether the file or directory denoted by this abstract pathname exists
        file.exists(); // -> true

        // Tests whether the application can execute the file denoted by this abstract pathname.
        file.canExecute(); // -> true

        // Tests whether the application can read/write the file denoted by this abstract pathname.
        file.canRead(); // -> true
        file.canWrite(); // -> true

        // returns length of the file
        file.length();

        /* Displaying contents of a directory */

        File dir = new File("F:\\Java\\src", "tutorial/learn/core/_6_io");

        // check if 2 files are same
        file.compareTo(dir); // -> 13

        if (dir.exists()) {

            // Returns an array of strings naming the files and directories in the directory denoted by this abstract pathname.
            String[] items = dir.list();
            System.out.println(Arrays.toString(items));

            // get number of files in directory
            byte totalOfItems = (byte) items.length;
            System.out.printf("Total number of items: %d\n", totalOfItems);

            // loop over each item to find if it is a file or directory
            for (int item = 0; item < totalOfItems; item++) {

                // create a file instance of the string representation of file
                File anItem = new File(items[item]);

                // Tests whether the file denoted by this abstract pathname is a normal file
                if (anItem.isFile())
                    System.out.printf("%s is a File\n", anItem.toString());

                    // Tests whether the file denoted by this abstract pathname is a directory
                else if (anItem.isDirectory())
                    System.out.printf("%s is a Directory\n", anItem.toString());

                else
                    System.out.println("Something else");
            }
        } else
            System.out.println("Directory does not exist");
    }
}
