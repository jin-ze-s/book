package com.atguigu.book.utils;

import org.apache.commons.beanutils.BeanUtils;


import java.util.Map;

/**
 * @author Tom 2022/2/7 0:44
 */
public class WebUtils {

    public static<T> T mapToBean(Map map,T bean) {

        try {
            BeanUtils.populate(bean, map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bean;
    }

    public static int strToInt(String id,int defaultNum){
        try {
            int i = Integer.parseInt(id);
            return i;
        } catch (NumberFormatException e) {
//            e.printStackTrace();
        }
        return defaultNum;
    }

}
