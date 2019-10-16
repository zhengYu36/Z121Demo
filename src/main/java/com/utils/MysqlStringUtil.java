package com.utils;


import org.apache.commons.lang.StringUtils;

/**
 * MysqlString 工具类
 * 在插入mysql时,如果insert中有转义字符需要额外处理
 */
public class MysqlStringUtil {

    /**
     * 处理转义字符
     *
     * @param keyword
     * @return
     */
    public static String escapeSpecialChar(String keyword) {
        if (StringUtils.isNotBlank(keyword)) {
            String[] fbsArr = {"\\", "$", "|", "%", "_", "(", ")", "*", "+", ".", "[", "]", "?", "^", "{", "}"};
            for (String key : fbsArr) {
                if (keyword.contains(key)) {
                    keyword = keyword.replace(key, "\\" + key);
                }
            }
        }

        return keyword;
    }


}
