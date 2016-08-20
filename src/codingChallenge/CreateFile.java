package codingChallenge;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class CreateFile {

    /**
     * This method is used to create Files and writing contents read into the
     * file which is read from the URL
     *
     * @param HtmlContent,Name
     */

    public static void fileCreation(String HtmlContent, String Name) throws IOException {
        File dir = new File(".");
        File newFileCreated = null;
        String loc = dir.getCanonicalPath() + File.separator + FileCrawler.s + File.separator + Name;

        try {
            // create a new file with a unique name according to the location
            newFileCreated = new File(loc);
            newFileCreated.createNewFile();
            PrintWriter out = new PrintWriter(newFileCreated);
            out.println(HtmlContent);
            out.close();

        } catch (Exception e) {
            if (e.equals("java.io.IOException")) {
                System.out.println("File Creation Error");
            }
        }

    }

}
