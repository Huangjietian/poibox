import cn.kerninventor.tools.poibox.Poibox;
import cn.kerninventor.tools.poibox.PoiboxFactory;
import cn.kerninventor.tools.poibox.data.tabulation.annotations.*;
import cn.kerninventor.tools.poibox.data.tabulation.validation.CompareType;
import cn.kerninventor.tools.poibox.data.tabulation.validation.array.FormulaListDataValid;
import cn.kerninventor.tools.poibox.data.tabulation.validation.date.DateDataValid;
import cn.kerninventor.tools.poibox.data.tabulation.validation.decimal.DecimalDataValid;

import java.io.IOException;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * <p>一句话描述</p>
 *
 * @author Kern
 */
@ExcelTabulation(
        banners = @ExcelBanner(
                value = "工时填报模板",
                style = @Style(index = 0, font = @Font(fontSize = 20, fontName = "黑体")), rowHeight = 25.0f),
        minimumColumnsWidth = 10
)
public class WorkHoursExcelTemplate {

    @FormulaListDataValid(value = "projectName")
    @ExcelColumn("项目")
    private String projectName;

    @FormulaListDataValid(value = "cascade:projectName")
    @ExcelColumn(value = "任务", columnWidth = 20)
    private String taskName;

    @DateDataValid(date = DateDataValid.NOW, compareType = CompareType.LTE)
    @ExcelColumn("日期")
    private LocalDate localDate;

    @FormulaListDataValid(value = "participantName")
    @ExcelColumn("填报人")
    private String participantName;

    @DecimalDataValid
    @ExcelColumn("工时")
    private Double workHour;

    @ExcelColumn(value = "工作内容描述", columnWidth = 30)
    private String content;

    public static void main(String[] args) throws IOException {
        Poibox poibox = PoiboxFactory.open();
        poibox.dataTabulator().writer(WorkHoursExcelTemplate.class)
                .withFormulaList("projectName", getProjectName())
                .withFormulaList("项目1", getProject1())
                .withFormulaList("项目2", getProject2())
                .withFormulaList("participantName", getParticipantName())
                .writeTo("工时填报模板");

        poibox.writeToLocal("C:\\Users\\82576\\Desktop\\工时填报模板.xlsx");
    }

    public static Set<String> getProjectName() {
        Set set = new LinkedHashSet();
        set.add("项目1");
        set.add("项目2");
        return set;
    }

    public static Set<String> getProject1() {
        Set set = new LinkedHashSet();
        set.add("项目1的任务1");
        set.add("项目1的任务2");
        return set;
    }

    public static Set<String> getProject2() {
        Set set = new LinkedHashSet();
        set.add("项目2的任务1");
        set.add("项目2的任务2");
        return set;
    }

    public static Set<String> getParticipantName() {
        Set set = new LinkedHashSet();
        set.add("张三");
        set.add("赵四");
        set.add("王五");
        return set;
    }
}
