package com.ninep.jubu.enums;

/**
 * @author wangjunfeng
 * @version 1.0
 * @desc department 部门岗位.
 * @since ${date}
 */
public enum PositionType {

    CEO("CEO", 1),
    RD("技术人员",2),
    MANAGER("经理", 3),
    OPERATIONAL_MANAGER("运营人员", 4),
    FARMER("农户", 5),
    HUMAN_RESOURCES("HR", 6),
    ADMINISTRATION("行政", 7),
    BUSINESS_SUPPORT("业务支持人员",8),
    ;

    private String name;//部门名称
    private Integer index;//部门编码

    PositionType(String name, Integer index) {
        this.name = name;
        this.index = index;
    }

    public static String getName(Integer index) {
        for (PositionType positionType : PositionType.values()) {
            if (positionType.getIndex().equals(index)) {
                return positionType.name;
            }
        }
        return null;
    }

    public static Integer getIndex(String name) {
        for (PositionType positionType : PositionType.values()) {
            if (positionType.getName().equals(name)) {
                return positionType.getIndex();
            }
        }
        return null;
    }

    public static PositionType getPositionType(Integer index) {
        for (PositionType positionType : PositionType.values()) {
            if (positionType.getIndex().equals(index)) {
                return positionType;
            }
        }
        return null;
    }

    public Integer getIndex() {
        return index;
    }

    public String getName() {
        return name;
    }

}