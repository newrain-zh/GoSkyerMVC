package org.goskyer.code;

/**
 * Created by zzqno on 2017-6-16.
 */
public class ServiceException extends RuntimeException{

    private ExceptionEnums exceptionEnums;

    public ServiceException(ExceptionEnums exceptionEnums){
        this.exceptionEnums = exceptionEnums;
    }
    public ExceptionEnums getExceptionEnums(){
        return exceptionEnums;
    }


    public void setExceptionEnums(ExceptionEnums exceptionEnums) {
        this.exceptionEnums = exceptionEnums;
    }

    public static void main(String[] args) {
        if(true){
            throw new ServiceException(DBExceptionEnums.UNIQUE_KEY);
        }
    }
}
