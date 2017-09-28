package kr.co.contestpro;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
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
public class TourAdapter extends BaseAdapter {
    private ArrayList<TourItem> listViewItemList = new ArrayList<TourItem>() ;

    // ListViewAdapter의 생성자
    public TourAdapter() {

    }

    // Adapter에 사용되는 데이터의 개수를 리턴. : 필수 구현
    @Override
    public int getCount() {
        return listViewItemList.size() ;
    }

    // position에 위치한 데이터를 화면에 출력하는데 사용될 View를 리턴. : 필수 구현
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();

        // "listview_item" Layout을 inflate하여 convertView 참조 획득.
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.tour_item, parent, false);
        }

        // 화면에 표시될 View(Layout이 inflate된)으로부터 위젯에 대한 참조 획득
        ImageView tour_item_iv = (ImageView) convertView.findViewById(R.id.tour_item_iv);;
        TextView tour_name_tv = (TextView) convertView.findViewById(R.id.tour_name_tv) ;
//        TextView tour_classify_tv = (TextView) convertView.findViewById(R.id.tour_classify_tv) ;

        // Data Set(listViewItemList)에서 position에 위치한 데이터 참조 획득
        TourItem listViewItem = listViewItemList.get(position);

        // 아이템 내 각 위젯에 데이터 반영
        if(listViewItem.getUrl().equals("None")) {
            tour_item_iv.setImageDrawable(listViewItem.getLoding());
        } else {
//            Glide.with(context).load(listViewItem.getUrl()).override(100,100).into(tour_item_iv);
            Picasso.with(context).load(listViewItem.getUrl()).placeholder(R.mipmap.ic_launcher).resize(100,100).into(tour_item_iv);
        }
        tour_name_tv.setText(listViewItem.getName());
//        tour_classify_tv.setText(listViewItem.getClassify());

        return convertView;
    }

    // 지정한 위치(position)에 있는 데이터와 관계된 아이템(row)의 ID를 리턴. : 필수 구현
    @Override
    public long getItemId(int position) {
        return position ;
    }

    // 지정한 위치(position)에 있는 데이터 리턴 : 필수 구현
    @Override
    public Object getItem(int position) {
        return listViewItemList.get(position) ;
    }

    // 아이템 데이터 추가를 위한 함수. 개발자가 원하는대로 작성 가능.
    public void addItem(String id, Drawable img, String name, String content, String address, Double longitude, Double latitude, String url, String subUrl1, String subUrl2, String subUrl3, String subUrl4) {
        TourItem item = new TourItem();

        item.setid(id);
        item.setLoding(img);
        item.setName(name);
        item.setContent(content);
        item.setAddress(address);
        item.setLongitude(longitude);
        item.setLatitude(latitude);
        item.setUrl(url);
        item.setSuburl1(subUrl1);
        item.setSuburl2(subUrl2);
        item.setSuburl3(subUrl3);
        item.setSuburl4(subUrl4);

        listViewItemList.add(item);
    }
}
