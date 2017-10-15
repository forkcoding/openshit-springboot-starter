package org.eappcat.entity;

import javax.persistence.Entity;

/**
 * Created by yuebo on 2017/10/15.
 */
@Entity
public class Cover extends BaseEntity {
    private String coverId;
    private String pic;
    private String name;
    private String url;
    private String type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCoverId() {
        return coverId;
    }

    public void setCoverId(String coverId) {
        this.coverId = coverId;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }
}
