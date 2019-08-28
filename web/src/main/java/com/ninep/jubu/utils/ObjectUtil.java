package com.ninep.jubu.utils;

import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.*;

/**
 * 基础对象操作
 *
 * @version 1.0
 * @since 2017.03.21
 */
public class ObjectUtil {
    /**
     * The constant log.
     */
    private static final Logger log = LoggerFactory.getLogger(ObjectUtil.class);

    /**
     * 设置对象field的默认值(跳过id域及非空域)
     * String = "", Integer=0, Long = 0, Boolean = false
     * @param obj the obj
     */
    public static void setDefault(Object obj) {
        try {
            List<Field> allField = getAllField(obj);
            for (Field field : allField) {
                field.setAccessible(true);
                Object object = field.get(obj);
                // 跳过id，自增长主键
                if (!StringUtils.equals("id", field.getName()) && object == null) {
                    setDefault(field, obj);
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    private static List<Field> getAllField(Object o) {
        List<Field> fieldList = new ArrayList<>();
        Class tempClass = o.getClass();
        while (tempClass != null && !tempClass.getName().toLowerCase().equals("java.lang.object")) {//当父类为null的时候说明到达了最上层的父类(Object类).
            fieldList.addAll(Arrays.asList(tempClass.getDeclaredFields()));
            tempClass = tempClass.getSuperclass(); //得到父类,然后赋给自己
        }
        return fieldList;
    }

    /**
     * 设置对象field的默认值(跳过id域、非空域及其excludeFields,)
     * String = "", Integer=0, Long = 0, Boolean = false
     * @param obj           the obj
     * @param excludeFields the exclude fields
     */
    public static void setDefaultExcludeFields(Object obj, String... excludeFields) {
        try {
            List<String> fieldNameList = Arrays.asList(excludeFields);
            for (Field field : obj.getClass().getDeclaredFields()) {
                field.setAccessible(true);
                Object object = field.get(obj);
                // 跳过id，自增长主键
                if (!StringUtils.equals("id", field.getName()) && !fieldNameList.contains(field.getName()) && object == null) {
                    setDefault(field, obj);
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    /**
     * Sets default.
     *
     * @param field the field
     * @param obj   the obj
     * @throws IllegalAccessException the illegal access exception
     */
    private static void setDefault(Field field, Object obj) throws IllegalAccessException {
        if (field.getType().equals(String.class)) {
            field.set(obj, "");
        }
        if (field.getType().equals(Long.class)) {
            field.set(obj, 0L);
        }
        if (field.getType().equals(Integer.class)) {
            field.set(obj, 0);
        }
        if (field.getType().equals(Byte.class)) {
            field.set(obj, (byte) 0);
        }
        if (field.getType().equals(Boolean.class)) {
            field.set(obj, false);
        }
        if (field.getType().equals(Date.class)) {
            field.set(obj, new Date());
        }
        if (field.getType().equals(Float.class)) {
            field.set(obj, 0f);
        }
        if (field.getType().equals(BigDecimal.class)) {
            field.set(obj, BigDecimal.ZERO);
        }
        if (field.getType().equals(Double.class)) {
            field.set(obj, 0.0d);
        }
        if (field.getType().equals(Timestamp.class)) {
            field.set(obj, new Timestamp(0));
        }
    }


    /**
     * 提供统一的 Object  tostring 方法
     *
     * @param obj the obj
     * @return string string
     * @since 2017.03.21
     */
    @SuppressWarnings("rawtypes")
    public static String toString(Object obj) {
        // 先拿反射实现
        StringBuilder sb = new StringBuilder();
        if (obj == null) {
            return "null";
        }
        try {
            if (obj.getClass().isPrimitive() || obj instanceof String || obj instanceof Integer
                    || obj instanceof Long || obj instanceof Boolean) {
                return obj.toString();
            }
            if (obj instanceof Map) {
                Map map = (Map) obj;
                return toString(map);
            } else if (obj instanceof Collection) {
                Collection cls = (Collection) obj;
                return toString(cls);
            } else if (obj instanceof Object[]) {
                for (Object o : (Object[]) obj) {
                    if (o.getClass().isPrimitive() || o instanceof String || o instanceof Integer
                            || o instanceof Long || o instanceof Boolean) {
                        sb.append(o).append(",");
                    } else {
                        sb.append(toString(o)).append(",");
                    }
                }
            }
            for (Field field : obj.getClass().getDeclaredFields()) {
                field.setAccessible(true);
                Object object = field.get(obj);
                if (object != null) {
                    sb.append("\"").append(field.getName()).append("\":");
                    if (object instanceof String) {
                        sb.append("\"").append(object).append("\",");
                    } else {
                        sb.append(object).append(",");
                    }
                }
            }
            return sb.length() == 0 ? "{}" : "{" + sb.substring(0, sb.length() - 1) + "}";
        } catch (Exception e) {
            log.error("ObjectUtil toString error,{},{}", obj, e);
            return obj.toString();
        }
    }

    /**
     * 提供统一的 Collection  tostring 方法
     *
     * @param objs the objs
     * @return string string
     * @since 2017.03.21
     */
    @SuppressWarnings("rawtypes")
    public static String toString(Collection objs) {
        // 先拿反射实现
        StringBuilder sb = new StringBuilder();
        if (objs == null) {
            return "null";
        }
        for (Object obj : objs) {
            if (obj.getClass().isPrimitive()) {
                sb.append(obj).append(",");
            } else {
                sb.append(toString(obj)).append(",");
            }
        }
        return sb.length() == 0 ? "[]" : "[" + sb.substring(0, sb.length() - 1) + "]";
    }

    /**
     * 判断是否有不为空的属性值
     *
     * @param obj the obj
     * @return the boolean
     * @since 2017.03.21
     */
    public static boolean hasNonNullProperty(Object obj) {
        try {
            for (Field field : obj.getClass().getDeclaredFields()) {
                field.setAccessible(true);
                Object property = field.get(obj);
                if (property != null) {
                    return true;
                }
            }
        } catch (Exception e) {
            log.warn(e.getMessage(), e);
        }
        return false;

    }

    /**
     * 提供统一的 Map  tostring 方法
     *
     * @param map the map
     * @return the string
     * @since 2017.03.21
     */
    public static String toString(Map map) {
        // 先拿反射实现
        StringBuilder sb = new StringBuilder();
        if (map == null) {
            return "null";
        }
        for (Object key : map.entrySet()) {
            Object value = map.get(key);
            sb.append("\"").append(toString(key)).append("\":").append(toString(value)).append(",");
        }
        return sb.length() == 0 ? "{}" : "{" + sb.substring(0, sb.length() - 1) + "}";
    }


    /**
     * Bean --> Map 1: 利用Introspector和PropertyDescriptor 将Bean --> Map
     *
     * @param obj the obj
     * @return map map
     * @since 2017.03.21
     */
    public static Map<String, Object> transBean2Map(Object obj) {
        if (obj == null) {
            return null;
        }
        Map<String, Object> map = Maps.newHashMap();
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor property : propertyDescriptors) {
                String key = property.getName();
                // 过滤class属性
                if (!StringUtils.equals("class", key)) {
                    // 得到property对应的getter方法
                    Method getter = property.getReadMethod();
                    Object value = getter.invoke(obj);
                    map.put(key, value);
                }
            }
        } catch (Exception e) {
            log.error("transBean2Map Error " + e);
        }
        return map;
    }

    /**
     * 判断对象属性是否为空
     *
     * @param obj the obj
     * @return the boolean
     * @since 2017.03.21
     */
    public static boolean isNullObj(Object obj) {
        if (obj == null ||
                (obj instanceof Collection && ((Collection<?>) obj).size() == 0) ||
                (obj instanceof Map && ((Map<?, ?>) obj).keySet().size() == 0) ||
                (obj.getClass().isArray() && ((Object[]) obj).length == 0)
                ) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isNotNullObj(Object obj) {
        return !isNullObj(obj);
    }

    /**
     * 判断两个对象是否相同
     *
     * @param source the source
     * @param target the target
     * @return the boolean
     * @since 2017.04.17
     */
    public static boolean isSame(Object source, Object target) {
        try {
            // 获取对象的class
            Class<?> clazz1 = source.getClass();
            Class<?> clazz2 = target.getClass();
            // 如果class不同
            if (!clazz1.getSimpleName().equals(clazz2.getSimpleName())) {
                return false;
            }
            // 获取对象的属性列表
            Field[] field1 = clazz1.getDeclaredFields();
            Field[] field2 = clazz2.getDeclaredFields();
            // 属性列表长度不同
            if (field1.length != field2.length) {
                return false;
            }
            // 遍历属性列表field1
            for (Field aField1 : field1) {
                //遍历属性列表field2
                for (Field aField2 : field2) {
                    //如果field1[i]属性名与field2[j]属性名内容相同
                    if (aField1.getName().equals(aField2.getName())) {
                        aField1.setAccessible(true);
                        aField2.setAccessible(true);
                        //如果field1[i]属性值与field2[j]属性值内容不相同
                        if (!isSameObject(aField1.get(source), aField2.get(target))) {
                            return false;
                        }
                    }
                }
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    /**
     * 判断两个对象是否相同，推荐使用在基本类型数据的判断上
     *
     * @param obj1 the obj 1
     * @param obj2 the obj 2
     * @return the boolean
     * @since 2017.03.21
     */
    public static boolean isSameObject(Object obj1, Object obj2) {
        if (obj1 == null && obj2 == null) {
            return true;
        } else {
            return obj1 != null && obj2 != null && obj1.equals(obj2);
        }
    }
}
