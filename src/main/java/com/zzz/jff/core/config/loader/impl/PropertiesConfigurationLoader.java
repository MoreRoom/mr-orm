package com.zzz.jff.core.config.loader.impl;

import com.zzz.jff.core.config.loader.Configuration4Init;
import com.zzz.jff.core.config.loader.IConfigurationLoader;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.*;

/**
 * @auther zhaowei.zhang
 * @since 2018/4/28 15:04
 */
public class PropertiesConfigurationLoader implements IConfigurationLoader {

    /** 配置文件的根目录 */
    private static final String DEFAULT_PROPERTIES_ROOT_PATH = "orm/datasource.properties";

    private final Set<String> dataSourcesNameSet = new HashSet<>();

    @Override

    public List<Configuration4Init> getLoader() {
        Properties properties = new Properties();
        List<Configuration4Init> configuration4InitList = new ArrayList<>();

        ClassLoader classLoader = getClass().getClassLoader();
        URL url = classLoader.getResource(DEFAULT_PROPERTIES_ROOT_PATH);
        Assert.isTrue(url == null, "couldn't found datasource.properties, please check out if it is exist.");
        try (InputStream inputStream = new FileInputStream(url.getFile())) {
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String dataSourceStr = properties.getProperty("dataSourceName");
        Assert.isTrue(!StringUtils.isEmpty(dataSourceStr), "couldn't found properties [dataSourcesName].");
        String[] names = dataSourceStr.split(",");
        for (String name : names) {
            Assert.isTrue(dataSourcesNameSet.contains(name), "dataSourceName [" + name + "] is exist");
            dataSourcesNameSet.add(name);
        }
        properties.remove("dataSourceName");
        for (String key : dataSourcesNameSet) {
            Configuration4Init configuration4Init;
            if ((configuration4Init = initSingleConfiguration(key, properties)) != null) {
                configuration4InitList.add(configuration4Init);
            }
        }

        return null;
    }

    private Configuration4Init initSingleConfiguration(String key, Properties properties) {
        Enumeration<String> enumeration = (Enumeration<String>) properties.propertyNames();
        while (enumeration.hasMoreElements()) {
            String propertiesKey = enumeration.nextElement();
            Map<String, String> paramMap = new HashMap<>();
            if (propertiesKey.startsWith(key)) {
                if (key.indexOf(".") == -1 || key.indexOf(".") >= key.length()) {
                    throw new RuntimeException("unknown properties [" + key + "].");
                }
                // 写到参数的map里面
//                paramMap.put();
            }


        }

        return null;
    }

    public static void main(String[] args) {
//        IConfigurationLoader configurationLoader = new PropertiesConfigurationLoader();
//        configurationLoader.getLoader();
        System.out.println("asdasdasd".indexOf("."));
    }


}
