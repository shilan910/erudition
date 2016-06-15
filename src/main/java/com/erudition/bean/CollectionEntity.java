package com.erudition.bean;

import javax.persistence.*;

/**
 * Created by sl on 16-6-15.
 */
@Entity
@Table(name = "eru_collection", schema = "", catalog = "db_erudition")
public class CollectionEntity {
    private int id;
    private Integer userId;
    private Integer fileId;

    @Id
    @Column(name = "id", nullable = false, insertable = true, updatable = true)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "user_id", nullable = true, insertable = true, updatable = true)
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "file_id", nullable = true, insertable = true, updatable = true)
    public Integer getFileId() {
        return fileId;
    }

    public void setFileId(Integer fileId) {
        this.fileId = fileId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CollectionEntity that = (CollectionEntity) o;

        if (id != that.id) return false;
        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;
        if (fileId != null ? !fileId.equals(that.fileId) : that.fileId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (fileId != null ? fileId.hashCode() : 0);
        return result;
    }
}
