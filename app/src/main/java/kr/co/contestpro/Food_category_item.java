package kr.co.contestpro;

/**
 * Created by Administrator on 2016-10-11.
 */
public class Food_category_item {
    private int iconDrawable ;
    private String titleStr ;


    public void setIcon(int icon) {
        iconDrawable = icon ;
    }
    public void setTitle(String title) {
        titleStr = title ;
    }


    public int getIcon() {
        return this.iconDrawable ;
    }
    public String getTitle() {
        return this.titleStr ;
    }

}
