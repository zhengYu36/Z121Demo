package test;

import com.demo.bean.BaseEntity;
import com.demo.bean.PK;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.Date;

/**
 * 管理人员黑名单
 * 该人员是否脱黑了 根据 deleteFlag 字段判断,
 * 默认在黑名单是 0 false,  脱黑了状态是1 true
 */
@Data
@PK
@JsonIgnoreProperties(ignoreUnknown = true)
public class ManagerPersonBlacklist extends BaseEntity {

    //管理人员
    private String managerPersonId;
    private String managerPersonName;

    //项目id
    private String projectId;
    private String projectName;

    // 拉黑年度 TODO （会存在评分重置的情况，目前来看是年度，不确定） 参考了劳务人员黑名单
    private Integer year;

    //拉黑时间
    private Date pullBlackTime;

    //拉黑原因
    private String pullBlackReason;

    //移除时间
    private Date removeTime;

    //移除原因
    private String removeReason;

    // 拉黑信用积分
    private Integer creditScore;

    // 拉黑考核分数
    private Integer examScore;

    // 脱黑信用积分
    private Integer deblackCreditScore;

    // 脱黑考核分数
    private Integer deblackExamScore;

    /**
     * 状态(该管理人员进行黑名单后,进行的脱黑申请,记录的是最后一次脱黑培训的状态)
     * 1 未申请
     * 2 已申请
     * 3 培训成功
     */
    private Integer status;

    // 脱黑培训id (如果这个人脱黑进行了3次,那么我们也只会记录最新一次的托和培训)
    private String deblackPlanId;

}
