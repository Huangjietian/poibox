# ProjectDescription
Personal early POI package based on project requirements, based on annotated programming, support Excel table reading and writing, support Excel template generation.
Later there are Easy-Excel projects, packaging more rich functions, recommended to use. This project is intended as an example or learning project only, and your comments are welcome.
If you want to join some open source community or organization, you can contact me (personal contact email: 825761175@qq.com).
I also have some Excel development of new projects in the idea, welcome to have ideas to participate in the small partners.

# POI BOX
Excel analysis tool based on annotation programming and POI framework
# 用法

The following is only an example. See src/test/java for an example.

```java

import cn.kerninventor.tools.poibox.Poibox;
import cn.kerninventor.tools.poibox.PoiboxFactory;
import cn.kerninventor.tools.poibox.data.tabulation.annotations.*;
import cn.kerninventor.tools.poibox.data.tabulation.validation.array.EnumExplicitListDataValid;
import cn.kerninventor.tools.poibox.data.tabulation.validation.array.ExplicitListDataValid;
import cn.kerninventor.tools.poibox.data.tabulation.validation.array.FormulaListDataValid;
import cn.kerninventor.tools.poibox.layout.Layouter;
import cn.kerninventor.tools.poibox.style.Fonter;
import cn.kerninventor.tools.poibox.style.Styler;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.FillPatternType;

import java.io.IOException;
import java.util.stream.Collectors;

/**
 * <h1>中文注释</h1>
 * <p>一句话描述</p>
 *
 * @author Kern
 * @version 1.0
 */
@ExcelTabulation(
        // 书写模板标题
        banners = @ExcelBanner(
                // 标题名称
                value = "运动员信息",
                // 标题风格配置
                style = @Style(
                        // 风格下标
                        index = 0,
                        // 字体样式
                        font = @Font(fontName = "黑体", fontSize = 16),
                        fillPatternType = FillPatternType.SOLID_FOREGROUND,
                        foregroudColor = HSSFColor.HSSFColorPredefined.BLUE
                ),
                // 行高
                rowHeight = 10f,
                // 标题区域
                range = @Range(fistRow = 0, lastRow = 2)
        ),
        // 表头行高度
        theadRowHeight = 20.0f,
        // 表头行样式，参考标题样式
        theadStyles = {
                @Style(
                        index = 0,
                        font = @Font(fontName = "楷体", fontSize = 14, color = HSSFColor.HSSFColorPredefined.WHITE),
                        fillPatternType = FillPatternType.SOLID_FOREGROUND,
                        foregroudColor = HSSFColor.HSSFColorPredefined.BLACK
                )}
)
public class Athlete {

    private Long id;

    // 列配置
    @ExcelColumn(value = "姓名")
    private String name;

    // 列单元格约束 - 示例为数组 指向一个枚举类
    @EnumExplicitListDataValid(enumClass = GenderEnum.class)
    @ExcelColumn(value = "性别")
    private String gender;

    // 列单元格约束 - 示例为数组
    @ExplicitListDataValid(list = {"汉族", "其他"})
    @ExcelColumn(value = "民族")
    private String nation;

    // 列单元格约束 - 示例为数组 指向一个字典健
    @FormulaListDataValid(value = "country")
    @ExcelColumn(value = "国家")
    private String countryCode;

    // 列单元格约束 - 示例为数组 指向一个字典健
    @FormulaListDataValid(value = "sport")
    @ExcelColumn("运动大项")
    private String sportCode;

    // 级联列单元格约束 - 示例为级联 指向对应字段名  固定格式为/ cascade:字段名
    @FormulaListDataValid(value = FormulaListDataValid.CASCADE_TAG + "sportCode")
    @ExcelColumn(value = "运动小项")
    private String discipilineCode;

    public static void main(String[] args) throws IOException {
        // 如下示例下载一个模板到本地
        Poibox poibox = PoiboxFactory.open();
        poibox.dataTabulator()
                // 传入模板类
                .writer(Athlete.class)
                // 声明一个常规字典
                .withFormulaList("country", Country.getCountries().stream().map(e -> e.getName()).collect(Collectors.toSet()))
                .withFormulaList("sport", Sport.getSports().stream().map(e -> e.getName()).collect(Collectors.toSet()))
                // 声明一个级联字典
                .withFormulaList("滑雪", Discipiline.getSkiingDiscipilines().stream().map(e -> e.getName()).collect(Collectors.toSet()))
                // 声明一个级联字典
                .withFormulaList("滑冰", Discipiline.getSkatingDiscipilines().stream().map(e -> e.getName()).collect(Collectors.toSet()))
                // 写入对应sheet页
                .writeTo("运动员");
        poibox.writeToLocal("C:\\Users\\FF\\Desktop\\运动员信息");
        // 也支持下载到网络
        // HttpServletResponse response = null;
        // poibox.writeToHttp(response, "运动员信息");

        // 也支持一些风格、字体、布局的快速生成, 以工具类的形式提供
        Styler styler = poibox.styler();
        Fonter fonter = poibox.fonter();
        Layouter layouter = poibox.layouter();
    }
}

```