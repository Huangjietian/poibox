package cn.kerninventor.tools.poibox.layout;

/**
 * <h1>中文注释</h1>
 * <p>
 *     支持的图片格式枚举
 * </p>
 * @author Kern
 */
public enum  PictureFormat {
    PICTURE_TYPE_EMF(2, ".emf"),
    PICTURE_TYPE_WMF(3,  ".wmf"),
    PICTURE_TYPE_PICT(4, ".pict"),
    PICTURE_TYPE_JPEG(5, ".jpeg", ".jpg"),
    PICTURE_TYPE_PNG(6,  ".png"),
    PICTURE_TYPE_DIB(7,  ".dib"),
    ;

    PictureFormat(int index, String... suffix) {
        this.index = index;
        this.suffix = suffix;
    }

    private int index;

    private String[] suffix;

    public int getIndex() {
        return index;
    }

    public String[] getSuffix() {
        return suffix;
    }

    public static int getIndexByFileName(String filename) {
        return getByFileName(filename).getIndex();
    }

    public static PictureFormat getByFileName(String filename) {
        if (filename == null || "".equals(filename.trim())) {
            throw new IllegalArgumentException("File name can't be empty!");
        }
        filename = filename.trim().substring(filename.lastIndexOf("."));
        PictureFormat[] formats = PictureFormat.values();
        for (PictureFormat format : formats) {
            for (String suffix : format.getSuffix()) {
                if (suffix.equals(filename)) {
                    return format;
                }
            }
        }
        throw new IllegalArgumentException("Unexpected picture format: " + filename);
    }
}
