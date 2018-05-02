package com.zzz.jff.core.config.loader.impl;

import com.zzz.jff.core.config.loader.Configuration4Init;
import com.zzz.jff.core.config.loader.DefaultConfiguration;
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
        Assert.isTrue(url != null, "couldn't found datasource.properties, please check out if it is exist.");
        try (InputStream inputStream = new FileInputStream(url.getFile())) {
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String dataSourceStr = properties.getProperty("dataSourceName");
        Assert.isTrue(!StringUtils.isEmpty(dataSourceStr), "couldn't found properties [dataSourcesName].");
        String[] names = dataSourceStr.split(",");
        for (String name : names) {
            Assert.isTrue(!dataSourcesNameSet.contains(name), "dataSourceName [" + name + "] is exist");
            dataSourcesNameSet.add(name);
        }
        properties.remove("dataSourceName");
        for (String key : dataSourcesNameSet) {
            Configuration4Init configuration4Init;
            if ((configuration4Init = initSingleConfiguration(key, properties)) != null) {
                configuration4InitList.add(configuration4Init);
            }
        }
        return configuration4InitList;
    }

    private Configuration4Init initSingleConfiguration(String key, Properties properties) {
        Enumeration<String> enumeration = (Enumeration<String>) properties.propertyNames();
        DefaultConfiguration configuration = new DefaultConfiguration();
        Map<String, String> paramMap = new HashMap<>();
        while (enumeration.hasMoreElements()) {
            String propertiesKey = enumeration.nextElement();
            if (propertiesKey.startsWith(key)) {
                int indexOfDot = -1;
                if ((indexOfDot = propertiesKey.indexOf(".")) == -1 || propertiesKey.indexOf(".") >= propertiesKey.length()) {
                    continue;
                }
                paramMap.put(propertiesKey.substring(indexOfDot + 1, propertiesKey.length()), properties.getProperty(propertiesKey));
            }
        }
        configuration.setParamMap(paramMap);
        return configuration;
    }

    public static void main(String[] args) {
        IConfigurationLoader configurationLoader = new PropertiesConfigurationLoader();
        List<Configuration4Init> result = configurationLoader.getLoader();
        for (Configuration4Init configuration4Init : result) {
            System.out.println(configuration4Init.toString());
        }
    }


}
