package org.goskyer.bean;

/**
 * Created by zzqno on 2017-3-22.
 * 返回数据对象
 * 封装一个Object类型的模型数据
 * 框架会将该对象写入HttpSevlertResponse
 *
 */
public class Data {

    /**
     * 模型数据
     */
    private Object model;

    public Data(Object model) {
        this.model = model;
    }

    public Object getModel() {
        return model;
    }


}
