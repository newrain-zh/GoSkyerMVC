package org.goskyer.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * Created by zzqno on 2017-3-23.
 */
public final class CodecUtil {

    private static final Logger log = LoggerFactory.getLogger(CodecUtil.class);

    /**
     * 将URL编码
     * @param source
     * @return
     */
    public static String encodeURL(String source){
        String target;
        try {
            target = URLEncoder.encode(source,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            log.error("encode url failure",e);
            throw new RuntimeException(e);
        }
        return target;
    }

    /**
     * 将URL解码
     * @param source
     * @return
     */
    public static  String decodeURL(String source){
        String target;
        try {
            target = URLDecoder.decode(source, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            log.error("decode url failure",e);
            throw new RuntimeException(e);
        }
        return target;
    }


}
