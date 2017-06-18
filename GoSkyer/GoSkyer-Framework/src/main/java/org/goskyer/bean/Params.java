package org.goskyer.bean;

import org.goskyer.util.CastUtil;

import java.util.Map;

/**
 * Created by zzqno on 2017-3-22.
 */
public class Params {

    private Map<String, Object> paramMap;

    public Params(Map<String, Object> paramMap) {
        this.paramMap = paramMap;
    }


    public Map<String, Object> getMap() {
        return paramMap;
    }


    private Object get(String name) {
        return paramMap.get(name);
    }

    public String getString(String name) {
        return CastUtil.castString(get(name));
    }

    public double getDouble(String name) {
        //return CastUtil.castDouble(get(name));
        return 0d;
    }

    public long getLong(String name) {
      //  return CastUtil.castLong(get(name));
        return 0L;
    }

    public int getInt(String name) {
        //return CastUtil.castInt(get(name));
        return 0;
    }
}
