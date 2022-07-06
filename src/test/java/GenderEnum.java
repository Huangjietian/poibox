import cn.kerninventor.tools.poibox.data.tabulation.validation.array.EnumExplicitList;

/**
 * <h1>中文注释</h1>
 * <p>一句话描述</p>
 *
 * @author Kern
 * @version 1.0
 */
public enum GenderEnum implements EnumExplicitList {

    MAN(1, "男"),
    WOMEN(2,"女"),
    ;

    private int code;

    private String name;

    GenderEnum(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    @Override
    public String explicitList() {
        return name;
    }
}
