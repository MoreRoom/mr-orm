package com.zzz.jff.core.config.loader;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * @auther zhaowei.zhang
 * @since 2018/5/2 15:48
 */
public class DefaultConfiguration extends Configuration4Init {

    @Override
    public void setParamMap(Map<String, String> paramMap) {
        Set<String> keySet = paramMap.keySet();
        Iterator<String> iterator = keySet.iterator();
        while (iterator.hasNext()) {
            String key = iterator.next();
            if ("userid".equals(key.toLowerCase())) {
                super.setUserId(paramMap.get(key));
                iterator.remove();
            }
            if ("password".equals(key.toLowerCase())) {
                super.setPassword(paramMap.get(key));
                iterator.remove();
            }
            if ("driver".equals(key.toLowerCase())) {
                super.setDriverName(paramMap.get(key));
                iterator.remove();
            }
            if ("url".equals(key.toLowerCase())) {
                super.setUrl(paramMap.get(key));
                iterator.remove();
            }

        }
        super.setParamMap(paramMap);
    }


    @Override
    public String toString() {
        return super.toString();
    }
}
