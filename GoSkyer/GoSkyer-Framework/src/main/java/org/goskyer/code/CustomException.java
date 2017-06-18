package org.goskyer.code;

/**
 * Created by zzqno on 2017-6-15.
 */
public class CustomException extends Exception {

    private String code;

    public CustomException(FrameworkCode code) {
        super(code.getDesc());
        this.code = code.getCode();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public static void main(String[] args) {
        String desc = "";
        if (desc.isEmpty()) {
            //throw new CustomException(FrameworkCode.Test);
        }

    }
}
