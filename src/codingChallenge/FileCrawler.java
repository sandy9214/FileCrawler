package codingChallenge;

import org.apache.commons.io.FileUtils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * @author sandy
 */
public class FileCrawler {
    public static String s;

    public static void main(String[] args) throws IOException {

        Scanner in = new Scanner(System.in);

        // current directory
        File dir = new File(".");

        System.out.println("Please Enter a URL in the following format \nEx. google.com \nor  google.com/shop");
        s = in.nextLine();
        String tmp = "http://" + s;
        String temp = dir.getCanonicalPath() + File.separator + FileCrawler.s;
        File deleteDirectory = new File(temp);

        // creating a new directory for the URL
        boolean success = (new File("./" + s)).mkdirs();
        if (!success && deleteDirectory.isDirectory()) { // Directory creation
            // failed
            FileUtils.deleteDirectory(deleteDirectory);
            new File("./" + s).mkdirs();
        }
        // current path in the disk for the log file
        String loc = dir.getCanonicalPath() + File.separator + FileCrawler.s + File.separator + "record.txt";

        FileWriter fstream = new FileWriter(loc, true);
        BufferedWriter out = new BufferedWriter(fstream);
        out.newLine();
        out.close();

        ProcessPage.processingPage(tmp);

        System.out.println("\nThe Files are Stored in: \n" + dir.getCanonicalPath() + File.separator + FileCrawler.s);
        System.out.println("\nRefer the 'record.txt' file for the log details");

        in.close();
    }
}