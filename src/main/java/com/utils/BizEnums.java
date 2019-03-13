package com.utils;

/**
 * <ul>
 * <li>文件包名 : com.utils</li>
 * <li>创建时间 : 2018/10/12 10:34</li>
 * <li>修改记录 : 无</li>
 * </ul>
 * 类说明：
 *
 * @author zhengyu
 */
public class BizEnums {

    /**
     * 销售渠道状态
     */
    public static enum SalesChannelStatusEnum {

        //outer:对外销售,inner:内部销售,tender:招标销售
        OUTER("outer", "对外销售"),
        INNER("inner", "内部销售"),
        TENDER("tender", "招标销售");
        private String value = null;
        private String desc = null;

        private SalesChannelStatusEnum(String v, String desc) {
            this.value = v;
            this.desc = desc;
        }

        public static String getDesc(String value) {
            if (value == null)
                return null;
            for (SalesChannelStatusEnum se : SalesChannelStatusEnum.values()) {
                if (se.value.equals(value)) {
                    return se.desc;
                }
            }
            return null;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getValue() {
            return this.value;
        }

        public String getDesc() {
            return this.desc;
        }
    }
}
