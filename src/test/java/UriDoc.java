import cn.kerninventor.tools.poibox.Poibox;
import cn.kerninventor.tools.poibox.PoiboxFactory;
import cn.kerninventor.tools.poibox.data.tabulation.annotations.ExcelBanner;
import cn.kerninventor.tools.poibox.data.tabulation.annotations.ExcelColumn;
import cn.kerninventor.tools.poibox.data.tabulation.annotations.ExcelTabulation;
import cn.kerninventor.tools.poibox.data.tabulation.annotations.Style;
import cn.kerninventor.tools.poibox.data.tabulation.validation.array.EnumExplicitListDataValid;
import cn.kerninventor.tools.poibox.data.tabulation.validation.array.ExplicitListDataValid;
import cn.kerninventor.tools.poibox.data.tabulation.validation.array.FormulaListDataValid;
import cn.kerninventor.tools.poibox.data.tabulation.validation.date.DateDataValid;
import cn.kerninventor.tools.poibox.data.tabulation.validation.decimal.DecimalDataValid;
import cn.kerninventor.tools.poibox.data.tabulation.validation.textlength.TextLengthDataValid;
import cn.kerninventor.tools.poibox.data.tabulation.writer.TabulationWriter;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * @author Kern
 */

@ExcelTabulation(banners = @ExcelBanner(value = "URI调整比较文档"))
public class UriDoc {

    @ExcelColumn(value = "模块")
    private String module;

    @ExcelColumn("接口名称")
    private String apiName;

    @ExcelColumn("请求方式")
    private String method;

    @ExcelColumn("路径前缀")
    private String pathPrefix;

    @ExcelColumn("接口路径")
    private String path;

    @ExcelColumn("新-路径前缀")
    private String newPathPrefix;

    @ExcelColumn("新-接口路径")
    private String newPath;

    @ExcelColumn("接口路径2")
    private String path2;

    public String getNewPath() {
        return newPath;
    }

    public UriDoc setNewPath(String newPath) {
        this.newPath = newPath;
        return this;
    }

    public String getPathPrefix() {
        return pathPrefix;
    }

    public UriDoc setPathPrefix(String pathPrefix) {
        this.pathPrefix = pathPrefix;
        return this;
    }

    public String getModule() {
        return module;
    }

    public UriDoc setModule(String module) {
        this.module = module;
        return this;
    }

    public String getApiName() {
        return apiName;
    }

    public UriDoc setApiName(String apiName) {
        this.apiName = apiName;
        return this;
    }

    public String getMethod() {
        return method;
    }

    public UriDoc setMethod(String method) {
        this.method = method;
        return this;
    }

    public String getPath() {
        return path;
    }

    public UriDoc setPath(String path) {
        this.path = path;
        return this;
    }

    public String getPath2() {
        return path2;
    }

    public UriDoc setPath2(String path2) {
        this.path2 = path2;
        return this;
    }

    public String getNewPathPrefix() {
        return newPathPrefix;
    }

    public UriDoc setNewPathPrefix(String newPathPrefix) {
        this.newPathPrefix = newPathPrefix;
        return this;
    }

    public static void main(String[] args) throws Exception {
//        //组装数据
//        Map<String, UriDoc> originApis = getApis("C:\\Users\\FF\\Desktop\\uri调整.apifox.json", Arrays.asList("/paas/v1/web"));
//        Map<String, UriDoc> newApis = getApis("C:\\Users\\FF\\Desktop\\uri调整-cloud.apifox.json", Arrays.asList("/device/v1/web", "/basic/v1/web"));
//        originApis.forEach((key, value) -> {
//            UriDoc uriDoc = newApis.get(key);
//            if (uriDoc != null) {
//                value.setNewPathPrefix(uriDoc.getPathPrefix());
//                value.setNewPath(uriDoc.getPath());
//            }
//        });
        Poibox poibox = PoiboxFactory.open();
        //写模板
        poibox.dataTabulator().writer(UriDoc.class).writeTo("单体uri模板");
        poibox.writeToLocal("C:\\Users\\FF\\Desktop\\uri调整.xlsx");

//        //写数据到模板中
//        poibox.dataTabulator().writer(UriDoc.class).writeTo("单体uri", new ArrayList<>(originApis.values()));
//
//        //最终写到终端， 本地或者响应
//        poibox.writeToLocal("C:\\Users\\FF\\Desktop\\uri调整.xlsx");
////        poibox.writeToHttp(HttpServletResponse response);  //读到响应中
//
//
//        //读模板数据
//        Poibox readBox = PoiboxFactory.open(new FileInputStream(""));
//        List<UriDoc> list = readBox.dataTabulator().reader(UriDoc.class).readFrom(0);
    }

    public static Map<String, UriDoc> getApis(String filePath, List<String> prefixes) throws Exception {
        File file = new File(filePath);
        BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
        byte[] bytes = new byte[bufferedInputStream.available()];
        int count = bufferedInputStream.read(bytes, 0, bytes.length);
        JSONObject object = JSON.parseObject(new String(bytes, StandardCharsets.UTF_8));
        JSONArray apiCollection = object.getJSONArray("apiCollection").getJSONObject(0).getJSONArray("items");
        Map<String,UriDoc> uriDocs = new LinkedHashMap<>();
        for (int i = 0; i < apiCollection.size() ; i ++) {
            JSONObject module = apiCollection.getJSONObject(i);
            String moduleName = module.getString("name");
            JSONArray moduleApiCollection = module.getJSONArray("items");
            for (int j = 0 ; j < moduleApiCollection.size() ; j ++) {
                JSONObject apiInfo = moduleApiCollection.getJSONObject(j);
                JSONObject api = apiInfo.getJSONObject("api");

                String apiName = apiInfo.getString("name");
                String method = api.getString("method");
                String path = api.getString("path");
                String prefix = prefixes.stream().filter(path::contains).findFirst().orElse("");
                String path2 = path.replace(prefix, "");
                UriDoc doc = new UriDoc()
                        .setModule(moduleName)
                        .setApiName(apiName)
                        .setMethod(method)
                        .setPathPrefix(prefix)
                        .setPath(path)
                        .setPath2(path2);
                uriDocs.put(String.join(":", method, path2), doc);
            }
        }
        return uriDocs;
    }

}
