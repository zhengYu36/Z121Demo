package peccancy;

/**
 * <ul>
 * <li>文件包名 : com.yanjoy.scbim.mp.entity.push</li>
 * <li>创建时间 : 2019/3/19 10:14 </li>
 * <li>修改记录 : 无</li>
 * </ul>
 * 类说明： 抓拍推送实体
 * @author zhengyu
 */

public class CapturePush {

    private String id;
    /**抓拍数据所属工点id（使用bim平台中的工点id）**/
    private String bim_station_id ;
    /**人脸图片**/
    private String face_base64;
    /**场景图片**/
    private String scene_base64;
    /**抓拍时间戳**/
    private long time;
    /**工点抓拍设备mac地址**/
    private String device_id;
    /**工点抓拍设备名称（如：鹤洞东站北侧点位(生活区附近一侧)）**/
    private String device_name;
    /**是否佩戴安全帽告警信息描述（非安全帽识别算法抓拍机返回值为未知）**/
    private String description;
    /**安全验证码，由接收方提供，验证接口使用者身份**/
    private String security_code;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBim_station_id() {
        return bim_station_id;
    }

    public void setBim_station_id(String bim_station_id) {
        this.bim_station_id = bim_station_id;
    }

    public String getFace_base64() {
        return face_base64;
    }

    public void setFace_base64(String face_base64) {
        this.face_base64 = face_base64;
    }

    public String getScene_base64() {
        return scene_base64;
    }

    public void setScene_base64(String scene_base64) {
        this.scene_base64 = scene_base64;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getDevice_id() {
        return device_id;
    }

    public void setDevice_id(String device_id) {
        this.device_id = device_id;
    }

    public String getDevice_name() {
        return device_name;
    }

    public void setDevice_name(String device_name) {
        this.device_name = device_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSecurity_code() {
        return security_code;
    }

    public void setSecurity_code(String security_code) {
        this.security_code = security_code;
    }
}
