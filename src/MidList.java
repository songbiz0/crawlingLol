import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.*;
import java.util.*;

public class MidList {
    public static final String WEB_DRIVER_ID = "webdriver.chrome.driver";
    public static final String WEB_DRIVER_PATH = "C:\\chromedriver.exe";
    static String[] lanes = new String[] {"middle"};

    public static void main(String[] args) throws IOException {
        for(int i=0; i<lanes.length; i++) {
            String lane = lanes[i];
            try {
                System.setProperty(WEB_DRIVER_ID, WEB_DRIVER_PATH);
            } catch (Exception e) {
                e.printStackTrace();
            }

            ChromeOptions options = new ChromeOptions();
            options.addArguments("headless");

            WebDriver driver = new ChromeDriver(options);

            String url = "https://lolalytics.com/lol/tierlist/?lane=" + lane + "&tier=gold_plus";

            driver.get(url);
            try { Thread.sleep(1500); } catch (InterruptedException e) {}
            List<String> champList = new ArrayList<>();

            WebElement el1 = driver.findElement(By.className("TierList_list__j33gd"));
            List<WebElement> el2 = el1.findElements(By.tagName("div"));
            for(int j=0; j<el2.size(); j++) {
                List<WebElement> el3 = el2.get(j).findElements(By.tagName("div"));
                if(el3.size() != 18) {
                    continue;
                }
                champList.add(el3.get(1).getText());
            }

            int count = 0;
            for(String str : champList) {
                System.out.println(lane + " : " + count + "/" + champList.size());
                System.out.println(str);
                addWr(str.toLowerCase().replace(" ", "").replace("'", ""), lane, ChampList.getList(lane));
                count++;
            }
        }
    }

    public static void addWr(String champName, String laneName, Set<String> myChamps) throws IOException {
        try {
            System.setProperty(WEB_DRIVER_ID, WEB_DRIVER_PATH);
        } catch (Exception e) {
            e.printStackTrace();
        }

        ChromeOptions options = new ChromeOptions();
        options.addArguments("headless");

        WebDriver driver = new ChromeDriver(options);

        String url = "https://lolalytics.com/lol/" + champName + "/counters/?lane=" + laneName + "&vslane=" + laneName + "&tier=all&patch=30";
        String name = "Counter_name__7IY8G";
        String winRate = "Counter_wr__fzjtG";
        String delta = "Counter_delta__sJfkF";

        driver.get(url);

        try { Thread.sleep(5000); } catch (InterruptedException e) {}

        List<WebElement> el1 = driver.findElements(By.className(name));
        List<WebElement> el2 = driver.findElements(By.className(winRate));
        List<WebElement> el3 = driver.findElements(By.className(delta));

        Map<String, Double> champs = new HashMap<>();

        for(int i=0; i<el1.size(); i++) {
            String wr = el2.get(i).getText().replace("%", "");
            double wrDouble = Double.parseDouble(wr);
            String delta2 = el3.get(i).getText().split(" ")[4];
            double deltaDouble = Double.parseDouble(delta2);

            champs.put(el1.get(i).getText(), deltaDouble);
            /*
            if(myChamps.contains(el1.get(i).getText())) {
            }
            */
        }

        List<Map.Entry<String, Double>> entry = new ArrayList<Map.Entry<String, Double>>(champs.entrySet());
        Collections.sort(entry, new Comparator<Map.Entry<String, Double>>() {
            @Override
            public int compare(Map.Entry<String, Double> o1, Map.Entry<String, Double> o2) {
                return o1.getValue().compareTo(o2.getValue());
            }
        });

        File file = new File("C:\\file\\" + laneName + ".txt");
        FileWriter fw = new FileWriter(file, true);
        BufferedWriter br = new BufferedWriter(fw);
        StringBuilder sb = new StringBuilder();
        sb.append("-").append(champName).append("\n");

        for(Map.Entry<String, Double> e : entry) {
            sb.append(e.getKey()).append(" ").append(e.getValue()).append("\n");
        }
        sb.append("\n");
        br.write(sb.toString());
        br.close();

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
