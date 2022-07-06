import java.util.ArrayList;
import java.util.List;

/**
 * <h1>中文注释</h1>
 * <p>一句话描述</p>
 *
 * @author Kern
 * @version 1.0
 */
public class Discipiline {

    private String code;

    private String sportCode;

    private String name;

    public Discipiline(String code, String sportCode, String name) {
        this.code = code;
        this.sportCode = sportCode;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public Discipiline setCode(String code) {
        this.code = code;
        return this;
    }

    public String getSportCode() {
        return sportCode;
    }

    public Discipiline setSportCode(String sportCode) {
        this.sportCode = sportCode;
        return this;
    }

    public String getName() {
        return name;
    }

    public Discipiline setName(String name) {
        this.name = name;
        return this;
    }

    public static List<Discipiline> getSkiingDiscipilines(){
        Discipiline freestyleSkiing = new Discipiline("freestyleSkiing", "skiing","自由式滑雪");
        Discipiline skiJumping = new Discipiline("skiJumping", "skiing","跳台滑雪");
        List<Discipiline> discipilines = new ArrayList<>(4);
        discipilines.add(freestyleSkiing);
        discipilines.add(skiJumping);
        return discipilines;
    }

    public static List<Discipiline> getSkatingDiscipilines(){
        Discipiline speedSkating = new Discipiline("speedSkating","skating","速度滑冰");
        Discipiline figureSkating = new Discipiline("figureSkating","skating","花样滑冰");
        List<Discipiline> discipilines = new ArrayList<>(4);
        discipilines.add(speedSkating);
        discipilines.add(figureSkating);
        return discipilines;
    }
}
