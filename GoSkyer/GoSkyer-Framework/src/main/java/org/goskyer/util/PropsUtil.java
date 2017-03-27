package org.goskyer.util;

import java.util.Properties;

/**
 * Created by zzqno on 2017-3-20.
 * 属性文件工具类
 */
public final class PropsUtil {

    public static Properties loadProps(String filePath) {
        Properties properties = new Properties();
        try {
            properties.load(PropsUtil.class.getResourceAsStream(filePath));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return properties;
    }

    /**
     * 读取配置文件
     * @param key
     * @return
     */
    public static String getString(Properties properties,String key){
        return properties.getProperty(key);
    }

}
