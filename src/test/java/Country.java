import java.util.ArrayList;
import java.util.List;

/**
 * <h1>中文注释</h1>
 * <p>一句话描述</p>
 *
 * @author Kern
 * @version 1.0
 */
public class Country {

    public Country(String code, String name) {
        this.code = code;
        this.name = name;
    }

    private String code;

    private String name;

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public static List<Country> getCountries() {
        Country china = new Country("CHN","中国");
        Country america = new Country("USA", "美国");
        Country japan = new Country("JPA","日本");
        Country britain = new Country("UK","英国");
        Country german = new Country("GER", "德国");
        List<Country> countries = new ArrayList<>(5);
        countries.add(china);
        countries.add(america);
        countries.add(japan);
        countries.add(britain);
        countries.add(german);
        return countries;
    }
}
