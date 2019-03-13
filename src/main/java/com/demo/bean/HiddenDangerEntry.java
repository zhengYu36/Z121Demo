package com.demo.bean;

// TODO: Auto-generated Javadoc

/**
 * 隐患排查条目库
 */
public class HiddenDangerEntry {

    /**
     * The Constant serialVersionUID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * The type.
     */
    //分类
    private String type;

    /**
     * The parent id.
     */
    //父级
    private String parentId;

    /**
     * The grade.
     */
    //等级
    private String grade;
    private Integer gradeLevel;

    /**
     * The is hava photo.
     */
    //是否需要拍照
    private Integer isHavaPhoto;

    /**
     * The measures.
     */
    //整改措施
    private String measures;

    /**
     * The term.
     */
    //整改期限
    private Integer term;

    /**
     * The is default.
     */
    //是否默认 1.是,2.否
    private Integer isDefault;

    /**
     * The is catalog.
     */
    //是否目录 1.是,2.否
    private Integer isCatalog;

    /**
     * The ebs code id.
     */
    //EBS编码
    private String ebsCodes;
    private String ebsCodeNames;

    //角色
    private String roleId;
    private String roleName;

    //开工阶段
    private String totalValue;
    private String totalValueName;

    //一级条目
    private String oneLevelCode;
    private String oneLevelName;
    //二级条目
    private String twoLevelCode;
    private String twoLevelName;
    //三级条目
    private String thrLevelCode;
    private String thrLevelName;
    /**
     * The sort.
     */
    //排序
    private int sort;

    /**
     * The tree level.
     */
    //层级
    private int treeLevel;

    private int isConfig;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public Integer getGradeLevel() {
        return gradeLevel;
    }

    public void setGradeLevel(Integer gradeLevel) {
        this.gradeLevel = gradeLevel;
    }

    public Integer getIsHavaPhoto() {
        return isHavaPhoto;
    }

    public void setIsHavaPhoto(Integer isHavaPhoto) {
        this.isHavaPhoto = isHavaPhoto;
    }

    public String getMeasures() {
        return measures;
    }

    public void setMeasures(String measures) {
        this.measures = measures;
    }

    public Integer getTerm() {
        return term;
    }

    public void setTerm(Integer term) {
        this.term = term;
    }

    public Integer getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(Integer isDefault) {
        this.isDefault = isDefault;
    }

    public Integer getIsCatalog() {
        return isCatalog;
    }

    public void setIsCatalog(Integer isCatalog) {
        this.isCatalog = isCatalog;
    }

    public String getEbsCodes() {
        return ebsCodes;
    }

    public void setEbsCodes(String ebsCodes) {
        this.ebsCodes = ebsCodes;
    }

    public String getEbsCodeNames() {
        return ebsCodeNames;
    }

    public void setEbsCodeNames(String ebsCodeNames) {
        this.ebsCodeNames = ebsCodeNames;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(String totalValue) {
        this.totalValue = totalValue;
    }

    public String getTotalValueName() {
        return totalValueName;
    }

    public void setTotalValueName(String totalValueName) {
        this.totalValueName = totalValueName;
    }

    public String getOneLevelCode() {
        return oneLevelCode;
    }

    public void setOneLevelCode(String oneLevelCode) {
        this.oneLevelCode = oneLevelCode;
    }

    public String getOneLevelName() {
        return oneLevelName;
    }

    public void setOneLevelName(String oneLevelName) {
        this.oneLevelName = oneLevelName;
    }

    public String getTwoLevelCode() {
        return twoLevelCode;
    }

    public void setTwoLevelCode(String twoLevelCode) {
        this.twoLevelCode = twoLevelCode;
    }

    public String getTwoLevelName() {
        return twoLevelName;
    }

    public void setTwoLevelName(String twoLevelName) {
        this.twoLevelName = twoLevelName;
    }

    public String getThrLevelCode() {
        return thrLevelCode;
    }

    public void setThrLevelCode(String thrLevelCode) {
        this.thrLevelCode = thrLevelCode;
    }

    public String getThrLevelName() {
        return thrLevelName;
    }

    public void setThrLevelName(String thrLevelName) {
        this.thrLevelName = thrLevelName;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public int getTreeLevel() {
        return treeLevel;
    }

    public void setTreeLevel(int treeLevel) {
        this.treeLevel = treeLevel;
    }

    public int getIsConfig() {
        return isConfig;
    }

    public void setIsConfig(int isConfig) {
        this.isConfig = isConfig;
    }
}
