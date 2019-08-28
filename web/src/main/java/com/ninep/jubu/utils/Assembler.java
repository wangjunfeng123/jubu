package com.ninep.jubu.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * domain vo对象属性转换
 * @author Administrator
 * @version 1.0
 */
public class Assembler {

    private static final Logger logger = LoggerFactory.getLogger(Assembler.class);

    /**
     * 从源对象 copy属性到目标对象
     *
     * @param fromObject the from object
     * @param toObject   the to object
     * @since 2017.03.21
     */
    public static void assemble(Object fromObject, Object toObject) {
        if (fromObject == null){
            return;
        }
        BeanUtils.copyProperties(fromObject, toObject);
    }

    /**
     * 从源对象 copy属性到目标对象
     * ignoreProperties 忽略的属性
     *
     * @param fromObject       the from object
     * @param toObject         the to object
     * @param ignoreProperties the ignore properties
     * @since 2017.03.21
     */
    public static void assemble(Object fromObject, Object toObject, String... ignoreProperties) {
        if (fromObject == null){
            return;
        }
        BeanUtils.copyProperties(fromObject, toObject, ignoreProperties);
    }

    /**
     * 从源List装配一个符合目标class类型的List.可以触发toObject的名称为
     * assemble Trigger的方法
     *
     * @param <T>           the type parameter
     * @param fromList      源list
     * @param toClass       目标class
     * @param excludeFields 不装配的属性名称
     * @return list list
     * @since 2017.03.21
     */
    public static <T> List<T> assembleList2NewList(List<?> fromList,
                                                   Class<T> toClass,
                                                   String... excludeFields) {
        List<T> toList = new ArrayList<T>(fromList.size());
        try {
            for (Iterator<?> iterator = fromList.iterator(); iterator.hasNext(); ) {
                Object fromObject = iterator.next();
                T toObject = toClass.newInstance();
                if (excludeFields.length > 0){
                    assemble(fromObject, toObject, excludeFields);
                }
                else{
                    assemble(fromObject, toObject);
                }
                toList.add(toObject);
            }
        } catch (Exception e) {
            logger.error("assember error",e);
        }
        return toList;
    }

    /**
     * 从源List装配一个符合目标class类型的List,可以触发toObject的名称为
     * assembleTrigger的方法
     *
     * @param <T>           the type parameter
     * @param fromList      源List
     * @param toList        目标List
     * @param toClass       the to class
     * @param excludeFields 不需要装配的属性
     * @return list list
     * @since 2017.03.21
     */
    public static <T> List<T> assembleList2List(List<?> fromList,
                                                List<T> toList,
                                                Class<T> toClass,
                                                String... excludeFields) {
        try {
            for (int i = 0; i < fromList.size(); i++) {
                Object fromObject = fromList.get(i);
                T toObject = i >= toList.size() ? toClass.newInstance() : toList.get(i);
                if (excludeFields.length > 0){
                    assemble(fromObject, toObject, excludeFields);
                }
                else{
                    assemble(fromObject, toObject);
                }
                toList.add(toObject);
            }
        } catch (Exception e) {
            logger.error("assember error",e);
        }
        return toList;
    }

}
