package codingChallenge;


import java.io.*;

public class CheckExist {
    /**
     * This method checks for unique URLs i.e. once a URL is been saved it
     * shouldn't be saved again and return if the String is contained in the
     * File
     *
     * @param url,fin
     * @return boolean
     */
    public static boolean checking4Existence(String url, File fin) throws IOException {

        FileInputStream fileInput = new FileInputStream(fin);
        //Construct the BufferedReader object
        BufferedReader bufferInput = new BufferedReader(new InputStreamReader(fileInput));

        String aLine = null;
        //Process each line
        while ((aLine = bufferInput.readLine()) != null) {
            if (aLine.trim().contains(url)) {
                bufferInput.close();
                fileInput.close();
                return true;
            }
        }

        //closing the resource
        bufferInput.close();
        fileInput.close();

        return false;
    }
}
