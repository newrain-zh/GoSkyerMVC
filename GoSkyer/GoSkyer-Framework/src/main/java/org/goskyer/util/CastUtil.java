package org.goskyer.util;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by zzqno on 2017-6-15.
 */
public final class CastUtil {

    public static final String STRING_DEFAUL_TVALUE = "";
    public static final double DOUBLE_DEFAUL_TVALUE = 0d;
    public static final int INT_DEFAUL_TVALUE = 0;


    /**
     * 类型转换为String
     *
     * @param object
     * @return
     */
    public static String castString(Object object) {
        return CastUtil.castString(object, STRING_DEFAUL_TVALUE);
    }

    /**
     * 转换为String
     *
     * @param object
     * @param defaultValue
     * @return
     */
    public static String castString(Object object, String defaultValue) {
        return object != null ? String.valueOf(object) : defaultValue;
    }

    /**
     * 转换为double
     *
     * @param strValue
     * @return
     */
    public static double castDouble(String strValue) {
        double doubleValue = DOUBLE_DEFAUL_TVALUE;
        if (StringUtils.isNotEmpty(strValue)) {
            try {
                doubleValue = Double.valueOf(strValue);
            } catch (NumberFormatException e) {
                doubleValue = DOUBLE_DEFAUL_TVALUE;
            }
        }
        return doubleValue;
    }

    /**
     * 转换为int
     *
     * @param strInt
     * @return
     */
    public static int castInt(String strInt) {
        int intValue = INT_DEFAUL_TVALUE;
        if (StringUtils.isNotEmpty(strInt)) {
            try {
                intValue = Integer.valueOf(strInt);
            } catch (NumberFormatException e) {
            }
        }
        return intValue;
    }

    /**
     * 转换为long
     * @param strLng
     * @return
     */
    public static long castLong(String strLng) {
        long lngValue = INT_DEFAUL_TVALUE;
        if (StringUtils.isNotEmpty(strLng)) {
            try {
                lngValue = Integer.valueOf(strLng).intValue();
            } catch (NumberFormatException e) {
                lngValue = INT_DEFAUL_TVALUE;
            }
        }
        return lngValue;
    }


    public static void main(String[] args) {
        int castInt = CastUtil.castInt("2w");
        System.out.println(castInt);
    }

}
