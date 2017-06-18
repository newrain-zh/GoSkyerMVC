package org.goskyer.code;

/**
 * Created by zzqno on 2017-6-16.
 */
public enum DBExceptionEnums implements ExceptionEnums {

    UNIQUE_KEY(10001, "主键约束错误");

    public int code;
    public String message;

    DBExceptionEnums(int code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String getCode() {
        return null;
    }

    @Override
    public String getMessage() {
        return null;
    }
}
