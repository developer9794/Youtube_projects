package com.Myrestaurant.Myrestaurant.Entity;

import javax.persistence.*;

@Entity
public class ImageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long imgid;

    @Lob
    private byte[] imageData;

    private Long restId;
    private String imgtype;
    private Long imagemapid;

    // Getters and setters
    public Long getImgid() {
        return imgid;
    }

    public void setImgid(Long imgid) {
        this.imgid = imgid;
    }

    public byte[] getImageData() {
        return imageData;
    }

    public void setImageData(byte[] imageData) {
        this.imageData = imageData;
    }

    public Long getRestId() {
        return restId;
    }

    public void setRestId(Long restId) {
        this.restId = restId;
    }

    public String getImgtype() {
        return imgtype;
    }

    public void setImgtype(String imgtype) {
        this.imgtype = imgtype;
    }

    public Long getImagemapid() {
        return imagemapid;
    }

    public void setImagemapid(Long imagemapid) {
        this.imagemapid = imagemapid;
    }
}
