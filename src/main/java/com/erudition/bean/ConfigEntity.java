package com.erudition.bean;

import javax.persistence.*;

/**
 * Created by tsj on 16-8-21.
 */
@Entity
@Table(name = "eru_config", schema = "", catalog = "db_erudition")
public class ConfigEntity {
    private int configid;
    private String key;
    private String value;

    public ConfigEntity(){

    }

    public ConfigEntity(int configid, String key, String value) {
        this.configid = configid;
        this.key = key;
        this.value = value;
    }

    @Id
    @Column(name = "configid")
    public int getConfigid() {
        return configid;
    }

    public void setConfigid(int configid) {
        this.configid = configid;
    }

    @Basic
    @Column(name = "key")
    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Basic
    @Column(name = "value")
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ConfigEntity that = (ConfigEntity) o;

        if (configid != that.configid) return false;
        if (key != null ? !key.equals(that.key) : that.key != null) return false;
        if (value != null ? !value.equals(that.value) : that.value != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = configid;
        result = 31 * result + (key != null ? key.hashCode() : 0);
        result = 31 * result + (value != null ? value.hashCode() : 0);
        return result;
    }
}
