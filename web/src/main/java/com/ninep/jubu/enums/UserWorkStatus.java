package com.ninep.jubu.enums;

/**
 * @author wangjunfeng
 * @version 1.0
 * @desc 用户状态.
 * @since 2018/07/02
 */
public enum UserWorkStatus {

    NOT_VERIFY("未审核",0),
    IN_WORK("在职", 1),
    LOCKED_WORK("账户被锁", 2);

    private String name;// 是否在职 名称
    private Integer index;// 在职 编码

    UserWorkStatus(){}
    UserWorkStatus(String name, Integer index) {
        this.name = name;
        this.index = index;
    }

    public static String getName(Integer index) {
        for (UserWorkStatus userWorkStatus : UserWorkStatus.values()) {
            if (userWorkStatus.getIndex().equals(index)) {
                return userWorkStatus.getName();
            }
        }
        return null;
    }

    public static Integer getIndex(String name) {
        for (UserWorkStatus userWorkStatus : UserWorkStatus.values()) {
            if (userWorkStatus.getName() == name) {
                return userWorkStatus.getIndex();
            }
        }
        return null;
    }

    public static UserWorkStatus getEmployeeWorkStatus(Integer index) {
        for (UserWorkStatus userWorkStatus : UserWorkStatus.values()) {
            if (userWorkStatus.getIndex().equals(index)) {
                return userWorkStatus;
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