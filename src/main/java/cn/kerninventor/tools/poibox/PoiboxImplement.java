package cn.kerninventor.tools.poibox;

import cn.kerninventor.tools.poibox.data.DataTabulationHandler;
import cn.kerninventor.tools.poibox.data.DataTabulator;
import cn.kerninventor.tools.poibox.layout.LayoutHandler;
import cn.kerninventor.tools.poibox.layout.Layouter;
import cn.kerninventor.tools.poibox.style.FontHandler;
import cn.kerninventor.tools.poibox.style.Fonter;
import cn.kerninventor.tools.poibox.style.StyleHandler;
import cn.kerninventor.tools.poibox.style.Styler;
import cn.kerninventor.tools.poibox.utils.ExcelDownloader;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Kern
 * @version 1.0
 */
public final class PoiboxImplement implements Poibox {

    private Workbook workbook;
    private DataTabulationHandler dataTabulationHandler;
    private Styler styler;
    private Fonter fonter;
    private Layouter layouter;

    protected PoiboxImplement() {
        workbook = new HSSFWorkbook();
    }

    public PoiboxImplement(Workbook workbook) {
        this.workbook = workbook;
    }

    @Override
    public DataTabulator dataTabulator() {
        if (dataTabulationHandler == null) {
            dataTabulationHandler = new DataTabulationHandler(this);
        }
        return dataTabulationHandler;
    }

    @Override
    public Styler styler() {
        if (styler == null) {
            styler = new StyleHandler(this);
        }
        return styler;
    }

    @Override
    public Fonter fonter() {
        if (fonter == null) {
            fonter = new FontHandler(this);
        }
        return fonter;
    }

    @Override
    public Layouter layouter() {
        if (layouter == null) {
            layouter = new LayoutHandler(this);
        }
        return layouter;
    }

    @Override
    public Workbook workbook() {
        return workbook;
    }

    @Override
    public Sheet getSheet(String sheetName) {
        return BoxGadget.getSheetForce(workbook, sheetName);
    }

    @Override
    public Sheet getSheet(int sheetAt) {
        return workbook.getSheetAt(sheetAt);
    }

    @Override
    public void writeToHttp(HttpServletResponse response, String fileName) throws IOException {
        ExcelDownloader.writeToHttp(workbook, response, fileName);
        flush();
    }

    @Override
    public void writeToLocal(String fileFullName) throws IOException {
        ExcelDownloader.writeToLocal(workbook, fileFullName);
        flush();
    }

    @Override
    public void flush(){
        if (workbook != null) {
            try {
                workbook.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        this.flush();
    }
}
