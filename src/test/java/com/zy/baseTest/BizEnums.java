package com.zy.baseTest;

/**
 * <ul>
 * <li>文件包名 : com.yanjoy.framework.util.enums</li>
 * <li>创建时间 : 2019/3/15 11:29 </li>
 * <li>修改记录 : 无</li>
 * </ul>
 * 类说明： 数据字典枚举类
 * @author zhengyu
 */

public class BizEnums {
    /**
     * 报警类型级别
     */
    public static enum  alarmLevelEnum{

        ONE("one", "Ⅰ级"),
        TWO("two", "Ⅱ级;"),
        THREE("three",  "Ⅲ级");

        private String value = null;
        private String desc = null;

        private alarmLevelEnum(String v, String desc) {
            this.value = v;
            this.desc = desc;
        }

        public static String getDesc(String value) {
            if (value == null)
                return null;
            for (alarmLevelEnum se : alarmLevelEnum.values()) {
                if (se.value.equals(value)) {
                    return se.desc;
                }
            }
            return null;
        }

        public String getValue() {
            return value;
        }

        public String getDesc() {
            return desc;
        }
    }


}