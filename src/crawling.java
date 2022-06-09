import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class crawling {
    public static final String WEB_DRIVER_ID = "webdriver.chrome.driver";
    public static final String WEB_DRIVER_PATH = "C:\\chromedriver.exe";
    String[] lanes = new String[] {"top", "mid", "bot", "support"};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        String str = br.readLine();

        try {
            System.setProperty(WEB_DRIVER_ID, WEB_DRIVER_PATH);
        } catch (Exception e) {
            e.printStackTrace();
        }

        ChromeOptions options = new ChromeOptions();
        options.addArguments("headless");

        WebDriver driver = new ChromeDriver(options);

        String url = "https://lolalytics.com/lol/" + str + "/counters/?lane=" + line + "&vslane=" + line + "&tier=gold_plus&patch=30";
        String name = "Counter_name__1wiND";
        String winRate = "Counter_wr__Jxax6";
        String delta = "Counter_delta__3_vlN";

        driver.get(url);

        try { Thread.sleep(1500); } catch (InterruptedException e) {}

        List<WebElement> el1 = driver.findElements(By.className(name));
        List<WebElement> el2 = driver.findElements(By.className(winRate));
        List<WebElement> el3 = driver.findElements(By.className(delta));

        Map<String, Double> champs = new HashMap<>();
        Set<String> myChamps = new HashSet<>();
        myChamps.add("Graves");
        myChamps.add("Veigar");
        myChamps.add("Viktor");
        myChamps.add("Vex");
        myChamps.add("Kassadin");
        myChamps.add("Lux");
        myChamps.add("Xerath");
        myChamps.add("Zoe");
        myChamps.add("Sylas");
        myChamps.add("Singed");
        myChamps.add("Anivia");
        myChamps.add("Ahri");
        myChamps.add("Sett");
        myChamps.add("Annie");
        myChamps.add("Akali");
        myChamps.add("Malzahar");
        myChamps.add("Kog'Maw");
        myChamps.add("Aatrox");
        myChamps.add("Karthus");
        myChamps.add("Garen");
        myChamps.add("Mordekaiser");
        myChamps.add("Ziggs");
        myChamps.add("Lissandra");
        myChamps.add("Twisted Fate");
        myChamps.add("Vladimir");
        myChamps.add("Swain");
        myChamps.add("Malphite");
        myChamps.add("Morgana");
        myChamps.add("Heimerdinger");
        myChamps.add("Kennen");
        myChamps.add("Brand");
        myChamps.add("Teemo");
        myChamps.add("Fiddlesticks");
        myChamps.add("Shyvana");
        myChamps.add("Lillia");

        for(int i=0; i<el1.size(); i++) {
            String wr = el2.get(i).getText().replace("%", "");
            double wrDouble = Double.parseDouble(wr);
            String delta2 = el3.get(i).getText().split(" ")[4];
            double deltaDouble = Double.parseDouble(delta2);
            if(wrDouble <= 50.0 && deltaDouble <= 0 && myChamps.contains(el1.get(i).getText())) {
                champs.put(el1.get(i).getText(), deltaDouble);
            }
        }

        List<Map.Entry<String, Double>> entry = new ArrayList<Map.Entry<String, Double>>(champs.entrySet());
        Collections.sort(entry, new Comparator<Map.Entry<String, Double>>() {
            @Override
            public int compare(Map.Entry<String, Double> o1, Map.Entry<String, Double> o2) {
                return o1.getValue().compareTo(o2.getValue());
            }
        });

        for(Map.Entry<String, Double> e : entry) {
            System.out.println(e.getKey() + " " + e.getValue());
        }

        try {
            if(driver != null) {
                driver.close();
                driver.quit();
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
