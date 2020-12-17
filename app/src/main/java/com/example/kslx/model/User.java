package com.example.kslx.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;
@Entity
public class User {
    @Id(autoincrement = true)
    private Long mId;
    private String mName;
    private String mSrc;
    @Generated(hash = 1638531560)
    public User(Long mId, String mName, String mSrc) {
        this.mId = mId;
        this.mName = mName;
        this.mSrc = mSrc;
    }
    @Generated(hash = 586692638)
    public User() {
    }
    public Long getMId() {
        return this.mId;
    }
    public void setMId(Long mId) {
        this.mId = mId;
    }
    public String getMName() {
        return this.mName;
    }
    public void setMName(String mName) {
        this.mName = mName;
    }
    public String getMSrc() {
        return this.mSrc;
    }
    public void setMSrc(String mSrc) {
        this.mSrc = mSrc;
    }

}
