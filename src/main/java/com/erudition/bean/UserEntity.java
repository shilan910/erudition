package com.erudition.bean;

import javax.persistence.*;

/**
 * Created by sl on 16-6-15.
 */
@Entity
@Table(name = "eru_user", schema = "", catalog = "db_erudition")
public class UserEntity {
    private int id;
    private String userName;
    private String email;
    private String telephone;
    private String password;
    private String imgUrl;
    private String arrCollection;
    private String arrLog;
    private String authority;

    @Id
    @Column(name = "id", nullable = false, insertable = true, updatable = true)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "user_name", nullable = true, insertable = true, updatable = true, length = 255)
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Basic
    @Column(name = "email", nullable = true, insertable = true, updatable = true, length = 255)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "telephone", nullable = true, insertable = true, updatable = true, length = 255)
    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    @Basic
    @Column(name = "password", nullable = true, insertable = true, updatable = true, length = 255)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "img_url", nullable = true, insertable = true, updatable = true, length = 255)
    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    @Basic
    @Column(name = "arr_collection", nullable = true, insertable = true, updatable = true, length = 255)
    public String getArrCollection() {
        return arrCollection;
    }

    public void setArrCollection(String arrCollection) {
        this.arrCollection = arrCollection;
    }

    @Basic
    @Column(name = "arr_log", nullable = true, insertable = true, updatable = true, length = 255)
    public String getArrLog() {
        return arrLog;
    }

    public void setArrLog(String arrLog) {
        this.arrLog = arrLog;
    }

    @Basic
    @Column(name = "authority", nullable = true, insertable = true, updatable = true, length = 255)
    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserEntity that = (UserEntity) o;

        if (id != that.id) return false;
        if (userName != null ? !userName.equals(that.userName) : that.userName != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (telephone != null ? !telephone.equals(that.telephone) : that.telephone != null) return false;
        if (password != null ? !password.equals(that.password) : that.password != null) return false;
        if (imgUrl != null ? !imgUrl.equals(that.imgUrl) : that.imgUrl != null) return false;
        if (arrCollection != null ? !arrCollection.equals(that.arrCollection) : that.arrCollection != null)
            return false;
        if (arrLog != null ? !arrLog.equals(that.arrLog) : that.arrLog != null) return false;
        if (authority != null ? !authority.equals(that.authority) : that.authority != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (userName != null ? userName.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (telephone != null ? telephone.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (imgUrl != null ? imgUrl.hashCode() : 0);
        result = 31 * result + (arrCollection != null ? arrCollection.hashCode() : 0);
        result = 31 * result + (arrLog != null ? arrLog.hashCode() : 0);
        result = 31 * result + (authority != null ? authority.hashCode() : 0);
        return result;
    }
}
