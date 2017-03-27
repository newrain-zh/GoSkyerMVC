package org.goskyer.bean;

import java.util.Map;

/**
 * Created by zzqno on 2017-3-22.
 */
public class Param {

    private Map<String, Object> paramMap;

    public Param(Map<String, Object> paramMap) {
        this.paramMap = paramMap;
    }

    public long getLong(String name) {
        return (long) paramMap.get(name);
    }

    public Map<String, Object> getMap() {
        return paramMap;
    }
}
