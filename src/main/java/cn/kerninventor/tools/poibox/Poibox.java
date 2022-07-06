package cn.kerninventor.tools.poibox;

import cn.kerninventor.tools.poibox.data.DataTabulator;
import cn.kerninventor.tools.poibox.layout.Layouter;
import cn.kerninventor.tools.poibox.style.Fonter;
import cn.kerninventor.tools.poibox.style.Styler;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <h1>中文注释</h1>
 * <p>
 *     Poibox, excel处理工具盒子接口。<br/>
 *     底层基于 org.apache.poi 实现
 * </p>
 * @author Kern
 * @version 1.0
*/
public interface Poibox {

    /**
     *
     * @return 数据处理器
     */
    DataTabulator dataTabulator();

    /**
     *
     * @return 风格处理器
     */
    Styler styler();

    /**
     *
     * @return 字体处理器
     */
    Fonter fonter();

    /**
     *
     * @return 布局处理器
     */
    Layouter layouter();

    /**
     *
     * @return Workbook
     */
    Workbook workbook();

    /**
     * <p>
     *     根据sheet页名称获取一个Sheet对象
     * </p>
     * @return Sheet
    */
    Sheet getSheet(String sheetName);

    /**
     * <p>
     *     根据sheet页下标获取一个Sheet对象
     * </p>
     * @return Sheet
    */
    Sheet getSheet(int sheetAt);

    /**
     * <p>
     *     写入文件流到 {@link HttpServletResponse}
     * </p>
    */
    void writeToHttp(HttpServletResponse response, String fileName) throws IOException;

    /**
     * <p>
     *     写入文件流到本地磁盘，需要提供一个完全限定名
     * </p>
    */
    void writeToLocal(String fileFullName) throws IOException;

    /**
     * <p>
     *     flush
     * </p>
    */
    void flush();
}
