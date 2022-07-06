import cn.kerninventor.tools.poibox.Poibox;
import cn.kerninventor.tools.poibox.PoiboxFactory;
import cn.kerninventor.tools.poibox.data.tabulation.annotations.*;
import cn.kerninventor.tools.poibox.data.tabulation.validation.array.EnumExplicitListDataValid;
import cn.kerninventor.tools.poibox.data.tabulation.validation.array.ExplicitListDataValid;
import cn.kerninventor.tools.poibox.data.tabulation.validation.array.FormulaListDataValid;
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
        banners = @ExcelBanner(
                value = "运动员信息",
                style = @Style(
                        index = 0,
                        font = @Font(fontName = "黑体", fontSize = 16),
                        fillPatternType = FillPatternType.SOLID_FOREGROUND,
                        foregroudColor = HSSFColor.HSSFColorPredefined.BLUE
                ),
                rowHeight = 10f,
                range = @Range(fistRow = 0, lastRow = 2)
        ),
        theadRowHeight = 20.0f,
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

    @ExcelColumn(value = "姓名")
    private String name;

    @EnumExplicitListDataValid(enumClass = GenderEnum.class)
    @ExcelColumn(value = "性别")
    private String gender;

    @ExplicitListDataValid(list = {"汉族","其他"})
    @ExcelColumn(value = "民族")
    private String nation;

    @FormulaListDataValid(value = "country")
    @ExcelColumn(value = "国家")
    private String countryCode;

    @FormulaListDataValid(value = "sport")
    @ExcelColumn("运动大项")
    private String sportCode;

    @FormulaListDataValid(value = FormulaListDataValid.CASCADE_TAG + "sportCode")
    @ExcelColumn(value = "运动小项")
    private String discipilineCode;

    public static void main(String[] args) throws IOException {
        Poibox poibox = PoiboxFactory.open();


        poibox.dataTabulator().writer(Athlete.class)
                .withFormulaList("country", Country.getCountries().stream().map(e -> e.getName()).collect(Collectors.toSet()))
                .withFormulaList("sport", Sport.getSports().stream().map(e -> e.getName()).collect(Collectors.toSet()))
                .withFormulaList("滑雪", Discipiline.getSkiingDiscipilines().stream().map(e -> e.getName()).collect(Collectors.toSet()))
                .withFormulaList("滑冰", Discipiline.getSkatingDiscipilines().stream().map(e -> e.getName()).collect(Collectors.toSet()))
                .writeTo("运动员");
        poibox.writeToLocal("C:\\Users\\FF\\Desktop\\运动员信息");
    }
}
