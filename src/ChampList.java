import java.util.HashSet;
import java.util.Set;

public class ChampList {
    static Set<String> champList;

    public static Set<String> getList(String lane) {
         champList = new HashSet<>();
        switch (lane) {
            case "top":
                champList.add("Lillia");
                champList.add("Mordekaiser");
                champList.add("Garen");
                champList.add("Olaf");
                champList.add("Skarner");
                champList.add("Sejuani");
                champList.add("Nasus");
                champList.add("Shyvana");
                champList.add("Dr. Mundo");
                champList.add("Neeko");
                champList.add("Swain");
                champList.add("Teemo");
                break;
            case "jungle":
                break;
            case "middle":
                champList.add("Ahri");
                champList.add("Veigar");
                champList.add("Viktor");
                champList.add("Vex");
                champList.add("Vladimir");
                champList.add("Lux");
                champList.add("Malzahar");
                champList.add("Anivia");
                champList.add("Zoe");
                champList.add("Twisted Fate");
                champList.add("Kassadin");
                champList.add("Lissandra");
                champList.add("Xerath");
                champList.add("Neeko");
                champList.add("Malphite");
                champList.add("Heimerdinger");
                champList.add("Garen");
                champList.add("Morgana");
                champList.add("Sett");
                champList.add("Karthus");
                champList.add("Mordekaiser");
                champList.add("Kennen");
                champList.add("Wukong");
                champList.add("Nasus");
                champList.add("Urgot");
                champList.add("Olaf");
                champList.add("Fiddlesticks");
                champList.add("Shyvana");
                champList.add("Sejuani");
                champList.add("Nautilus");
                break;
            case "bottom":
                break;
            case "support":
                champList.add("Janna");
                champList.add("Senna");
                champList.add("Renata Glasc");
                champList.add("Sona");
                champList.add("Zilean");
                champList.add("Neeko");
                champList.add("Soraka");
                champList.add("Xerath");
                champList.add("Bard");
                champList.add("Nami");
                champList.add("Swain");
                champList.add("Lulu");
                break;
        }
        return champList;
    }
}
