package org.goskyer.code;

/**
 * Created by zzqno on 2017-6-15.
 */
public enum FrameworkCode {

    Test("1001", "自定义异常");
    private String code;
    private String desc;

    FrameworkCode(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
