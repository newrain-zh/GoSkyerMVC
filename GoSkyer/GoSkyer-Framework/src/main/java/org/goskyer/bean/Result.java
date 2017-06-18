package org.goskyer.bean;

/**
 * Created by zzqno on 2017-6-14.
 */
public class Result {

    private boolean success; // 成功标志
    private String error;       // 错误代码
    private Object data;     // 相关数据

    public Result(boolean success) {
        this.success = success;
    }

    public Result error(String error) {
        this.error = error;
        return this;
    }

    public Result data(Object data) {
        this.data = data;
        return this;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
