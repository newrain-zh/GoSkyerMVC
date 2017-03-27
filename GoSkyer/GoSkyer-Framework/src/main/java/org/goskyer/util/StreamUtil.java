package org.goskyer.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by zzqno on 2017-3-23.
 */
public final class StreamUtil {

    private static final Logger log = LoggerFactory.getLogger(StreamUtil.class);

    public static String get(InputStream inputStream) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader((ServletInputStream) inputStream));
        String line;
        StringBuilder sb = new StringBuilder();
        try {
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            return sb.toString();
            // System.out.println(sb);
        } catch (Exception e) {
            log.error("read request inputStream failure");
            throw new RuntimeException(e);
        }

    }
}
