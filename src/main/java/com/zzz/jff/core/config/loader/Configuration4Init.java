package com.zzz.jff.core.config.loader;

import java.util.Map;

/**
 * @auther zhaowei.zhang
 * @since 2018/4/28 15:03
 */
public abstract class Configuration4Init {

    /**
     * 驱动名称
     */
    private String driverName;

    /**
     * 用户名
     */
    private String userId;

    /**
     * 密码
     */
    private String password;

    /**
     * 数据库连接地址
     */
    private String url;

    /**
     * 其他参数
     */
    private Map<String, String> paramMap;

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Map<String, String> getParamMap() {
        return paramMap;
    }

    public void setParamMap(Map<String, String> paramMap) {
        this.paramMap = paramMap;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Configuration4Init{" +
                "driverName='" + driverName + '\'' +
                ", userId='" + userId + '\'' +
                ", password='" + password + '\'' +
                ", url='" + url + '\'' +
                ", paramMap=" + paramMap +
                '}';
    }
}
