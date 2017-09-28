package kr.co.contestpro;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016-10-18.
 */
class KakaoAdapter extends BaseAdapter {
    Context context;     // 현재 화면의 제어권자
    int layout;              // 한행을 그려줄 layout
    ArrayList<Group_setting> al;     // 다량의 데이터
    LayoutInflater inf; // 화면을 그려줄 때 필요

    public KakaoAdapter(Context context, int layout, ArrayList<Group_setting> al) {
        this.context = context;
        this.layout = layout;
        this.al = al;
        this.inf = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() { // 총 데이터의 개수를 리턴
        return al.size();
    }

    @Override
    public Object getItem(int position) { // 해당번째의 데이터 값
        return al.get(position);
    }

    @Override
    public long getItemId(int position) { // 해당번째의 고유한 id 값
        return position;
    }

    @Override // 해당번째의 행에 내용을 셋팅(데이터와 레이아웃의 연결관계 정의)
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null)
            convertView = inf.inflate(layout, null);

        ImageView iv = (ImageView) convertView.findViewById(R.id.group_img);
        TextView tvName = (TextView) convertView.findViewById(R.id.group_name);
        Group_setting m = al.get(position);

        //iv.setImageResource(m.img);

        Glide.with(context).load(m.getUrl()).into(iv);
//        if(m.getUrl() == null) {
//            iv.setImageResource(m.getLoding());
//        } else {
//            Glide.with(context).load(m.getUrl()).into(iv);
//        }
        tvName.setText(m.getName());
        //tvName.setText(m.name);

        return convertView;
    }
    public void addItem(String name, String url) {
        Group_setting item = new Group_setting();
//        item.setLoding(img);
        item.setName(name);
        item.setUrl(url);

        al.add(item);
    }
}
