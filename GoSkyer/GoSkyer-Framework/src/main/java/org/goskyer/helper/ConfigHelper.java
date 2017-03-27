package org.goskyer.helper;

import org.goskyer.config.ConfigConstant;
import org.goskyer.util.PropsUtil;
import java.util.Properties;

/**
 * Created by zzqno on 2017-3-20.
 */
public final class ConfigHelper {

    private static final Properties CONFIG_PROS = PropsUtil.loadProps("/"+ ConfigConstant.CONFIG_FILE);

    public static String getJdbcDriver() {
        return PropsUtil.getString(CONFIG_PROS, ConfigConstant.JDBC_DRIVER);
    }

    public static String getJdbcUrl() {
        return PropsUtil.getString(CONFIG_PROS, ConfigConstant.JDBC_URL);
    }


    public static String getJdbcUsername() {
        return PropsUtil.getString(CONFIG_PROS, ConfigConstant.JDBC_USERNAME);
    }


    public static String getJdbcPassword() {
        return PropsUtil.getString(CONFIG_PROS, ConfigConstant.JDBC_PASSWORD);
    }

    public static String getAppBasePackage() {
        return PropsUtil.getString(CONFIG_PROS, ConfigConstant.APP_BASE_PACKEAGE);
    }

    public static String getAppJspPath() {
        return PropsUtil.getString(CONFIG_PROS, ConfigConstant.APP_JSP_PATH);
    }

    public static String getAppAssetPath() {
        return PropsUtil.getString(CONFIG_PROS, ConfigConstant.APP_ASSET_PATH);
    }

}
