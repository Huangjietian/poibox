package cn.kerninventor.tools.poibox.utils;

/**
 * @author Kern
 * @version 1.0
 */
public enum FileFormatEnum {

    XLS_EXCEL("D0CF11E0", ".xls"),
    XLSX_EXCEL("504B0304", ".xlsx"),
    ;

    private String header;
    private String suffix;

    FileFormatEnum(String header, String suffix) {
        this.header = header;
        this.suffix = suffix;
    }

    public String getHeader() {
        return header;
    }

    public String getSuffix() {
        return suffix;
    }

    public static FileFormatEnum getFormatByHeader(String header){
        FileFormatEnum[] enums = FileFormatEnum.values();
        for (FileFormatEnum formatEnum : enums){
            if (header.contains(formatEnum.header)){
                return formatEnum;
            }
        }
        return null;
    }

    public static boolean isCorrectSuffix(String path, FileFormatEnum... fileFormatEnums) {
        if (path == null || "".equals(path.trim()) || !path.contains(".")){
            return false;
        }
        boolean isCorrect = false;
        for (FileFormatEnum fileFormatEnum : fileFormatEnums){
            if (path.substring(path.lastIndexOf(".")).equals(fileFormatEnum.suffix)){
                isCorrect = true;
            }
        }
        return isCorrect;
    }
}
