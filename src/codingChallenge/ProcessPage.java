package codingChallenge;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ProcessPage {
	/**
	 * This method checks for invalid URLs and processes each URL to check its
	 * domain and passes value to other classes
	 *
	 * @param url
	 */
	public static void processingPage(String URL) throws IOException {

		File dir = new File(".");
		String loc = dir.getCanonicalPath() + File.separator + FileCrawler.s + File.separator + "record.txt";

		// for invalid links
		if (URL.contains(".pdf") || URL.contains("@") || URL.contains("adfad") || URL.contains(":80")
				|| URL.contains("fdafd") || URL.contains(".jpg") || URL.contains(".pdf") || URL.contains(".jpg"))
			return;

		// process the url first only
		if (URL.contains(FileCrawler.s) && !URL.endsWith("/")) {

		} else if (URL.contains(FileCrawler.s) && URL.endsWith("/")) {
			URL = URL.substring(0, URL.length() - 1);
		} else {
			// url of other site -> do nothing
			return;
		}

		File file = new File(loc);

		// check existance
		boolean existanceOfUrl = CheckExist.checking4Existence(URL, file);
		if (!existanceOfUrl) {
			System.out.println("------ :  " + URL);
			String FileName = UniqueNames.CreateUniqueNames(URL);

			// inserting a record to log file
			FileWriter fstream = new FileWriter(loc, true);
			BufferedWriter out = new BufferedWriter(fstream);
			out.write(URL);
			out.newLine();
			out.close();

			Document doc = null;
			try {
				doc = Jsoup.connect(URL).timeout(0).get();
				// Passing HTMLCONTENT and Specific Name for the respective URLs
				CreateFile.fileCreation(doc.html(), FileName);
			} catch (IOException e1) {
				if (e1.equals("org.jsoup.HttpStatusException"))
					System.out.println("Status 404, Error Fetching URL");
				if (e1.equals("java.net.MalformedURLException"))
					System.out.println("Sorry,Only http & https protocols supported");
				return;
			}

			Elements questions = doc.select("a[href]");
			for (Element link : questions) {
				processingPage(link.attr("abs:href"));
			}
		} else {
			// do nothing
			return;
		}

	}
}