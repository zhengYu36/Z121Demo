package cn.design.pattern.strategyPattern;

/**
 * <ul>
 * <li>文件包名 : cn.design.pattern.strategyPattern</li>
 * <li>创建时间 : 2018/12/17 11:16</li>
 * <li>修改记录 : 无</li>
 * </ul>
 * 类说明：
 *
 * @author zhengyu
 */
public enum Demo {

    RED("红色", 1), YELLOW("黄色", 2);

    private String name;
    private int code;

    Demo(String name, int code) {
        this.name = name;
        this.code = code;
    }

    public static String getName(int code) {
        for (Demo d : Demo.values()) {
            if (d.getCode() == code) {
                return d.name;
            }
        }

        return null;
    }

    @Override
    public String toString() {
        return "Demo{" +
                "name='" + name + '\'' +
                ", code=" + code +
                "} " + super.toString();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
