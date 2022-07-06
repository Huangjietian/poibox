import java.util.ArrayList;
import java.util.List;

/**
 * <h1>中文注释</h1>
 * <p>一句话描述</p>
 *
 * @author Kern
 * @version 1.0
 */
public class Sport {

    private String code;

    private String name;

    public Sport(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public Sport setCode(String code) {
        this.code = code;
        return this;
    }

    public String getName() {
        return name;
    }

    public Sport setName(String name) {
        this.name = name;
        return this;
    }

    public static List<Sport> getSports(){
        Sport skiing = new Sport("skiing","滑雪");
        Sport skating = new Sport("skating","滑冰");
        List<Sport> sports = new ArrayList<>(2);
        sports.add(skiing);
        sports.add(skating);
        return sports;
    }
}
