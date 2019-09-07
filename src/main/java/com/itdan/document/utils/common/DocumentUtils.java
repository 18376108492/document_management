package com.itdan.document.utils.common;

import java.text.DecimalFormat;

/**
 * 文档工具类
 */
public class DocumentUtils {


    /**
     * 格式化文件大小
     * 参考：https://stackoverflow.com/a/5599842/1253611
     *
     * @param size byte
     * @return readable file size
     */
    public static String readableFileSize(long size) {
        if (size <= 0) return "0";
        final String[] units = new String[]{"B", "kB", "MB", "GB", "TB"};
        int digitGroups = (int) (Math.log10(size) / Math.log10(1024));
        return new DecimalFormat("#,##0.#").format(size / Math.pow(1024, digitGroups)) + " " + units[digitGroups];
    }
}
