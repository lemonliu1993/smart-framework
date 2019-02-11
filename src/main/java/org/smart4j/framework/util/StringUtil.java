package org.smart4j.framework.util;

import org.apache.commons.lang3.StringUtils;

/**
 * 字符串工具类
 * Created by lemoon on 19/2/11 下午11:32
 */
public class StringUtil {
    /*
     * 判断字符串是否为空
     * */
    public static boolean isEmpty(String str){
        if(str != null){
            str=str.trim();
        }
        return StringUtils.isEmpty(str);
    }
    /*
     * 判断字符串是否非空
     * */
    public static boolean isNotEmpty(String str){
        return !isEmpty(str);
    }

}
