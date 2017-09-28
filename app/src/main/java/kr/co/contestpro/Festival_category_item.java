package kr.co.contestpro;

/**
 * Created by Administrator on 2016-10-11.
 */
public class Festival_category_item {
    //    private Drawable iconDrawable ;
    private String titleStr ;
    private String Start_date_Str ;
    private String End_date_Str ;
    private String Background;

    //    public void setIcon(Drawable icon) {
//        iconDrawable = icon ;
//    }
    public void setTitle(String title) {
        titleStr = title ;
    }
    public void setStart_date(String s_date) {
        Start_date_Str = s_date ;
    }
    public void setEnd_date(String e_date) {
        End_date_Str = e_date ;
    }
    public void setBackground(String background) {
        Background = background ;
    }

    //    public Drawable getIcon() {
//        return this.iconDrawable ;
//    }
    public String getTitle() {
        return this.titleStr ;
    }
    public String getStart_date() {
        return this.Start_date_Str ;
    }
    public String getEnd_date() {
        return this.End_date_Str ;
    }

    public String getBackground() {
        return this.Background ;
    }
}
