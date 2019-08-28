package com.ninep.jubu.enums;

/**
 * @author wangjunfeng
 * @version 1.0
 * @desc gender 性别 .
 * @since 2018/07/02
 */
public enum  Gender {
    UNKNOWN("未知", 0),
    BOY("男生", 1),
    GIRL("女生", 2);

    private String name;//性别名称
    private Integer index;//性别编码

    Gender(String name, Integer index) {
        this.name = name;
        this.index = index;
    }

    public static String getName(Integer index) {
        for (Gender ge : Gender.values()) {
            if (ge.getIndex().equals(index)) {
                return ge.name;
            }
        }
        return null;
    }

    public static Integer getIndex(String name) {
        for (Gender ge : Gender.values()) {
            if (ge.getName() == name) {
                return ge.getIndex();
            }
        }
        return null;
    }

    public static Gender getGender(Integer index) {
        for (Gender ge : Gender.values()) {
            if (ge.getIndex().equals(index)) {
                return ge;
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