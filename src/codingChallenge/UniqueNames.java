package codingChallenge;

public class UniqueNames {

    /**
     * This method is used to create Unique names for each URL and return name for each URL file
     *
     * @param URL
     * @return String
     */
    public static String CreateUniqueNames(String URL) {
        String Name;
        //removing characters which would create confusion in name
        Name = URL.replaceAll("/", "");
        Name = Name.replaceAll(FileCrawler.s, "");
        Name = Name.replaceAll("www", "");
        Name = Name.replaceAll("WWW", "");
        Name = Name.replaceAll("http:.", "");
        Name = Name.replaceAll("https:.", "");
        if (Name.equals(""))
            Name = "index";

		/*	checking for file extensions 
            if doesn't have any extension
			giving a extension of .html
		*/

        /*will only work for php aspx jsp and js files
        * must include other files too */
        if (!(Name.contains(".php") || Name.contains(".aspx") || Name.contains(".jsp") || Name.contains(".js")
                || Name.contains(".html"))) {
            Name = Name + ".html";
        }

        return Name;
    }
}