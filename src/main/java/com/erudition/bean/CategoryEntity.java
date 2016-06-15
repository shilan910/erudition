package com.erudition.bean;

import javax.persistence.*;

/**
 * Created by sl on 16-6-15.
 */
@Entity
@Table(name = "eru_category", schema = "", catalog = "db_erudition")
public class CategoryEntity {
    private int id;
    private String categoryName;
    private Integer category1Id;
    private Integer category2Id;
    private Integer category3Id;

    @Id
    @Column(name = "id", nullable = false, insertable = true, updatable = true)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "category_name", nullable = true, insertable = true, updatable = true, length = 255)
    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    @Basic
    @Column(name = "category_1_id", nullable = true, insertable = true, updatable = true)
    public Integer getCategory1Id() {
        return category1Id;
    }

    public void setCategory1Id(Integer category1Id) {
        this.category1Id = category1Id;
    }

    @Basic
    @Column(name = "category_2_id", nullable = true, insertable = true, updatable = true)
    public Integer getCategory2Id() {
        return category2Id;
    }

    public void setCategory2Id(Integer category2Id) {
        this.category2Id = category2Id;
    }

    @Basic
    @Column(name = "category_3_id", nullable = true, insertable = true, updatable = true)
    public Integer getCategory3Id() {
        return category3Id;
    }

    public void setCategory3Id(Integer category3Id) {
        this.category3Id = category3Id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CategoryEntity that = (CategoryEntity) o;

        if (id != that.id) return false;
        if (categoryName != null ? !categoryName.equals(that.categoryName) : that.categoryName != null) return false;
        if (category1Id != null ? !category1Id.equals(that.category1Id) : that.category1Id != null) return false;
        if (category2Id != null ? !category2Id.equals(that.category2Id) : that.category2Id != null) return false;
        if (category3Id != null ? !category3Id.equals(that.category3Id) : that.category3Id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (categoryName != null ? categoryName.hashCode() : 0);
        result = 31 * result + (category1Id != null ? category1Id.hashCode() : 0);
        result = 31 * result + (category2Id != null ? category2Id.hashCode() : 0);
        result = 31 * result + (category3Id != null ? category3Id.hashCode() : 0);
        return result;
    }
}
