package com.gyfish.formflow.util;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.cglib.core.Converter;

import java.beans.PropertyDescriptor;
import java.util.HashSet;
import java.util.Set;

import lombok.extern.slf4j.Slf4j;

/**
 * 对象复制工具类
 *
 * @author geyu
 */
@Slf4j
public class BeanUtil {

    /**
     * 基于 cglib 的 beanCopier，复制 bean，并返回指定类的实例 只会复制名称相同的属性，如果是 null 也会复制过去
     *
     * @param bean  要复制的对象
     * @param clazz 目标类的 class
     * @param <T>   目标 bean 的类型
     * @return 目标对象
     */
    public static <T> T copy(Object bean, Class<T> clazz) {

        BeanCopier copier = BeanCopier.create(bean.getClass(), clazz, true);

        T target = null;

        try {
            target = clazz.newInstance();
            copier.copy(bean, target, (o, aClass, o1) -> o);
        } catch (Exception e) {
            log.error("=== bean util copy 异常 ===");
            log.error("=== bean：{}", bean);
            log.error("=== clazz：{}", clazz);
            e.printStackTrace();
        }

        return target;
    }

    /**
     * 基于 spring BeanUtils，对象 copy
     *
     * @param src      原对象
     * @param target   目标对象
     * @param copyNull 是否 copy null 属性
     */
    public static void copy(Object src, Object target, boolean copyNull) {

        String[] ignoreProperties = copyNull ? null : getNullPropertyNames(src);

        BeanUtils.copyProperties(src, target, ignoreProperties);
    }

    private static String[] getNullPropertyNames(Object source) {

        final BeanWrapper src = new BeanWrapperImpl(source);
        PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<>();
        for (PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) emptyNames.add(pd.getName());
            if (srcValue instanceof String && "".equals(srcValue)) emptyNames.add(pd.getName());
        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }

}
