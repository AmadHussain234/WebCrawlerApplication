import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class JsoupProject {
    public static void main(String[] args) throws IOException {
        englishToHindiAndRoman();
    }

    private static void englishToHindiAndRoman() throws IOException {
        FileReader fileReader = new FileReader("EnglishWords.txt");
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        String lines;
        int count = 0;

        while ((lines = bufferedReader.readLine()) != null) {
            FileWriter fileWriter = new FileWriter("WordsSavedInFile.txt", true);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.println("\tEnglish\t\t\tHindi\t\t\t\tRoman ");
            System.out.println("\tEnglish\t\t\tHindi\t\t\t\tRoman ");

            String url = "https://hamariweb.com/dictionaries/hindi-english-dictionary.aspx?eu=" + lines;
            Document document = Jsoup.connect(url).get();
            Elements table = document.select(".col-1");
            Elements tableRow = table.select("tr");
            System.out.println("Searched  word From file : " + lines);
            List<Element> temp = new ArrayList<>();
            if (tableRow.size() > 1) {
                temp = tableRow.subList(2, tableRow.size() - 1);
                count++;
            }

            printWriter.println("Searched  word From file : " + lines);

            for (Element tr : temp) {
                Elements tableDivision = tr.select("td");
                if (!tableDivision.text().equals("")) {
                    System.out.println(+count + ")" + "[ " + tableDivision.text() + " ]");
                    printWriter.println( + count + ")" + "[ " + tableDivision.text() + " ]");
                }
            }
            System.out.println("___________________________________________________________________________________________________________________________________________________________");
            printWriter.println("___________________________________________________________________________________________________________________________________________________________");
            printWriter.close();
        }
    }
}