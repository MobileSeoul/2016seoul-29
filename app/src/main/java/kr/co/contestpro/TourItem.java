package kr.co.contestpro;

import android.graphics.drawable.Drawable;

/**
 * Created by Administrator on 2016-10-11.
 */
public class TourItem {
    private String id;
    private Drawable Lodingimg ;
    private String name ;
    private String classify ;
    private String url;
    private String content;
    private String address;
    private Double longitude;
    private Double latitude;
    private String sub_img1;
    private String sub_img2;
    private String sub_img3;
    private String sub_img4;

    public void setid(String Tourid) {
        id = Tourid ;
    }
    public void setLoding(Drawable TourLoagding) {
        Lodingimg = TourLoagding ;
    }
    public void setName(String Tourtitle) {
        name = Tourtitle ;
    }
    public void setClassify(String TourClassify) {
        classify = TourClassify ;
    }
    public void setUrl(String Toururl) {
        url = Toururl ;
    }
    public void setContent(String TourContent) {
        content = TourContent ;
    }
    public void setAddress(String TourLocation) {
        address = TourLocation ;
    }
    public void setLongitude(Double ToutLongitude) {
        longitude = ToutLongitude ;
    }
    public void setLatitude(Double TourLatitude) {
        latitude = TourLatitude ;
    }
    public void setSuburl1(String Toursub1) {
        sub_img1 = Toursub1 ;
    }
    public void setSuburl2(String Toursub2) {
        sub_img2 = Toursub2 ;
    }
    public void setSuburl3(String Toursub3) {
        sub_img3 = Toursub3 ;
    }
    public void setSuburl4(String Toursub4) {
        sub_img4 = Toursub4 ;
    }



    public String getId() {
        return this.id ;
    }
    public Drawable getLoding() {
        return this.Lodingimg ;
    }
    public String getName() {
        return this.name ;
    }
    public String getClassify() {
        return this.classify ;
    }
    public String getUrl() {
        return this.url ;
    }
    public String getContent() {
        return this.content ;
    }
    public String getAddress() {
        return this.address ;
    }
    public Double getLongitude() {
        return this.longitude ;
    }
    public Double getLatitude() {
        return this.latitude ;
    }
    public String getSub1() {
        return this.sub_img1 ;
    }
    public String getSub2() {
        return this.sub_img2 ;
    }
    public String getSub3() {
        return this.sub_img3 ;
    }
    public String getSub4() {
        return this.sub_img4 ;
    }
}
