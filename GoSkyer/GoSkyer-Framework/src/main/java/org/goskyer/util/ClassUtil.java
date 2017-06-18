package org.goskyer.util;


import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * Created by zzqno on 2017-3-20.
 * 类加载工具类
 */
public final class ClassUtil {

    private static final Logger log = LoggerFactory.getLogger(ClassUtil.class);


    /**
     * 获取类加载器
     *
     * @return
     */
    public static ClassLoader getClassLoad() {
        return Thread.currentThread().getContextClassLoader();
    }


    /**
     * 加载类
     *
     * @param classname
     * @param isInitialized
     * @return
     */
    public static Class<?> loadClass(String classname, boolean isInitialized) {
        Class<?> cls;
        try {
            cls = Class.forName(classname, isInitialized, getClassLoad());
        } catch (ClassNotFoundException e) {
            log.error("load class failure", e);
            throw new RuntimeException(e);
        }
        return cls;
    }


    /**
     * 获取指定包下的所有类
     *
     * @param packagename
     * @return
     */
    public static Set<Class<?>> getClassSet(String packagename) {
        Set<Class<?>> classSet = new HashSet<Class<?>>();
        try {
            Enumeration<URL> urls = getClassLoad().getResources(packagename.replace(".", "/"));
            while (urls.hasMoreElements()) {
                URL url = urls.nextElement();
                if (url != null) {
                    String protocol = url.getProtocol();
                    if ("file".equals(protocol)) {
                        String packagePath = url.getPath().replace("%20", "");
                        addClass(classSet, packagePath, packagename);
                    } else if (protocol.equals("jar")) {
                        JarURLConnection jarURLConnection = (JarURLConnection) url.openConnection();
                        url.openConnection();
                        if (jarURLConnection != null) {
                            JarFile jarFile = jarURLConnection.getJarFile();
                            if (jarFile != null) {
                                Enumeration<JarEntry> jarEntries = jarFile.entries();
                                while (jarEntries.hasMoreElements()) {
                                    JarEntry jarEntry = jarEntries.nextElement();
                                    String jarEntryName = jarEntry.getName();
                                    if (jarEntryName.endsWith(".class")) {
                                        String classname = jarEntryName.substring(0, jarEntryName.lastIndexOf(".")).replaceAll("/", ".");
                                        doAddClass(classSet, classname);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } catch (IOException e) {
            log.error("get class failure", e);
            throw new RuntimeException(e);
        }
        return classSet;
    }


    private static void addClass(Set<Class<?>> classSet, String packagePath, String packagename) {
        File[] files = new File(packagePath).listFiles(file -> (file.isFile() && file.getName().endsWith(".class")) || file.isDirectory());
        for (File file : files) {
            String filename = file.getName();
            if (file.isFile()) {
                String className = filename.substring(0, filename.lastIndexOf("."));
                if (StringUtils.isNotEmpty(packagename)) {
                    className = packagename + "." + className;
                }
                doAddClass(classSet, className);
            } else {
                String subPackagePath = filename;
                if (StringUtils.isNotEmpty(packagePath)) {
                    subPackagePath = packagePath + "/" + subPackagePath;
                }
                String subPackageName = filename;
                if (StringUtils.isNotEmpty(packagename)) {
                    subPackageName = packagename + "." + subPackageName;
                }
                addClass(classSet, subPackagePath, subPackageName);

            }
        }
    }


    private static void doAddClass(Set<Class<?>> classSet, String classname) {
        Class<?> cls = loadClass(classname, false);
        classSet.add(cls);
    }

    /**
     * 是否为 int 类型（包括 Integer 类型）
     */
    public static boolean isInt(Class<?> type) {
        return type.equals(int.class) || type.equals(Integer.class);
    }

    /**
     * 是否为 long 类型（包括 Long 类型）
     */
    public static boolean isLong(Class<?> type) {
        return type.equals(long.class) || type.equals(Long.class);
    }

    /**
     * 是否为 double 类型（包括 Double 类型）
     */
    public static boolean isDouble(Class<?> type) {
        return type.equals(double.class) || type.equals(Double.class);
    }

    /**
     * 是否为 String 类型
     */
    public static boolean isString(Class<?> type) {
        return type.equals(String.class);
    }
}
