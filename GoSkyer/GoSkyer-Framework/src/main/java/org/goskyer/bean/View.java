package org.goskyer.bean;

import org.apache.commons.collections.map.HashedMap;

import java.util.Map;

/**
 * Created by zzqno on 2017-3-22.
 * 返回视图类型
 */
public class View {

    //视图路径
    private String path;

    //模型数据
    private Map<String, Object> model;


    public View(String path) {
        this.path = path;
        model = new HashedMap();
    }

    public View addModel(String key, Object value) {
        model.put(key, value);
        return this;
    }


    public String getPath() {
        return path;
    }

    public Map<String, Object> getModel() {
        return model;
    }

    public boolean isRedirect() {
        return path.startsWith("/");
    }
}
