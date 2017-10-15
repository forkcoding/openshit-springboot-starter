package org.eappcat.entity;

import javax.persistence.Entity;

/**
 * Created by yuebo on 2017/10/15.
 */
@Entity
public class User extends BaseEntity {
    private String openid;
    private String headImg;
    private String nickname;

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
