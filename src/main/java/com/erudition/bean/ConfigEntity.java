package com.erudition.bean;

import javax.persistence.*;

/**
 * Created by tsj on 16-8-23.
 */
@Entity
@Table(name = "eru_config", schema = "", catalog = "db_erudition")
public class ConfigEntity {
    private int configId;
    private String configKey;
    private String configValue;

    @Id
    @Column(name = "config_id")
    public int getConfigId() {
        return configId;
    }

    public void setConfigId(int configId) {
        this.configId = configId;
    }

    @Basic
    @Column(name = "config_key")
    public String getConfigKey() {
        return configKey;
    }

    public void setConfigKey(String configKey) {
        this.configKey = configKey;
    }

    @Basic
    @Column(name = "config_value")
    public String getConfigValue() {
        return configValue;
    }

    public void setConfigValue(String configValue) {
        this.configValue = configValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ConfigEntity that = (ConfigEntity) o;

        if (configId != that.configId) return false;
        if (configKey != null ? !configKey.equals(that.configKey) : that.configKey != null) return false;
        if (configValue != null ? !configValue.equals(that.configValue) : that.configValue != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = configId;
        result = 31 * result + (configKey != null ? configKey.hashCode() : 0);
        result = 31 * result + (configValue != null ? configValue.hashCode() : 0);
        return result;
    }
}
