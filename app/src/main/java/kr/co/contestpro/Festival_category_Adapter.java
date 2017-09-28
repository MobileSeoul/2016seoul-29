package kr.co.contestpro;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016-10-11.
 */
public class Festival_category_Adapter extends BaseAdapter {
    private ArrayList<Festival_category_item> listViewItemList = new ArrayList<Festival_category_item>() ;

    // ListViewAdapter???앹꽦??
    public Festival_category_Adapter() {

    }

    // Adapter???ъ슜?섎뒗 ?곗씠?곗쓽 媛쒖닔瑜?由ы꽩. : ?꾩닔 援ы쁽
    @Override
    public int getCount() {
        return listViewItemList.size() ;
    }

    // position???꾩튂???곗씠?곕? ?붾㈃??異쒕젰?섎뒗???ъ슜??View瑜?由ы꽩. : ?꾩닔 援ы쁽
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();

        // "listview_item" Layout??inflate?섏뿬 convertView 李몄“ ?띾뱷.
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.festival_category_item, parent, false);
        }

        // ?붾㈃???쒖떆??View(Layout??inflate???쇰줈遺???꾩젽?????李몄“ ?띾뱷
        ImageView background = (ImageView) convertView.findViewById(R.id.Festival_category_ImageView_1) ;
        TextView titleTextView = (TextView) convertView.findViewById(R.id.title) ;
        TextView Start_TextView = (TextView) convertView.findViewById(R.id.text1) ;
//        TextView Mid_TextView = (TextView) convertView.findViewById(R.id.text2) ;
        TextView End_TextView = (TextView) convertView.findViewById(R.id.text3) ;

        // Data Set(listViewItemList)?먯꽌 position???꾩튂???곗씠??李몄“ ?띾뱷
        Festival_category_item listViewItem = listViewItemList.get(position);

        // ?꾩씠????媛??꾩젽???곗씠??諛섏쁺
//        iconImageView.setImageDrawable(listViewItem.getIcon());
        titleTextView.setText(listViewItem.getTitle().toString());
        Start_TextView.setText(listViewItem.getStart_date().toString());
//        Mid_TextView.setText(listViewItem.getMid().toString());
        End_TextView.setText((listViewItem.getEnd_date().toString()));

//        if (convertView == null) {
//            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//            convertView = inflater.inflate(R.layout.festival_category_item, parent, false);
//        }
      // LinearLayout_background.setBackground(listViewItem.getBackground());
//        Glide.with(context).load(listViewItem.getBackground()).into(background);
        Picasso.with(context).load(listViewItem.getBackground()).placeholder(R.mipmap.ic_launcher).resize(100,100).into(background);
        return convertView;
    }

    // 吏?뺥븳 ?꾩튂(position)???덈뒗 ?곗씠?곗? 愿怨꾨맂 ?꾩씠??row)??ID瑜?由ы꽩. : ?꾩닔 援ы쁽
    @Override
    public long getItemId(int position) {
        return position ;
    }

    // 吏?뺥븳 ?꾩튂(position)???덈뒗 ?곗씠??由ы꽩 : ?꾩닔 援ы쁽
    @Override
    public Object getItem(int position) {
        return listViewItemList.get(position) ;
    }

    // ?꾩씠???곗씠??異붽?瑜??꾪븳 ?⑥닔. 媛쒕컻?먭? ?먰븯?붾?濡??묒꽦 媛??
    public void addItem(String title, String s_date, String e_date, String Background) {
        Festival_category_item item = new Festival_category_item();

        item.setTitle(title);
        item.setStart_date(s_date);
        item.setEnd_date(e_date);

        item.setBackground(Background);

        listViewItemList.add(item);
    }
}
