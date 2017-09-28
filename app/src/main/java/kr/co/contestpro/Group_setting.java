package kr.co.contestpro;

/**
 * Created by Administrator on 2016-10-15.
 */
public class Group_setting {
    private String id;
    private String name ;
    private String classify ;
    private String url;

    public void setName(String Tourtitle) {
        name = Tourtitle ;
    }
    public void setUrl(String Toururl) {
        url = Toururl ;
    }


    public String getName() {
        return this.name ;
    }
    public String getUrl() {
        return this.url ;
    }
}
