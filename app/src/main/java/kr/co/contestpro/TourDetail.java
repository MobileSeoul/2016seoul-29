package kr.co.contestpro;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.skp.Tmap.TMapMarkerItem;
import com.skp.Tmap.TMapPoint;
import com.skp.Tmap.TMapView;
import com.squareup.picasso.Picasso;


/**
 * Created by 게스트 on 2016-10-25.
 */

public class TourDetail extends Activity {
    TMapView tmapview;
    TMapMarkerItem mark;
    TextView name_tv;
    TextView content_tv;
    TextView address_tv;
    ImageView tour_detail_iv1;
    ImageView tour_detail_iv2;
    ImageView tour_detail_iv3;
    ImageView tour_detail_iv4;

    // intent
    String name;
    String content;
    String address;
    String url;
    Double latitude; // 위도
    Double longitude; // 경도
    String sub_url1;
    String sub_url2;
    String sub_url3;
    String sub_url4;

    //dialog
    ImageView detail_img;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Render View first
        setContentView(R.layout.tour_intent);

        RelativeLayout navilayout_tour = (RelativeLayout) findViewById(R.id.navilayout_tour);
        name_tv = (TextView) findViewById(R.id.name_tv);
        content_tv = (TextView) findViewById(R.id.content_tv);
        address_tv = (TextView) findViewById(R.id.address_tv);
        tour_detail_iv1 = (ImageView) findViewById(R.id.tour_detail_iv1);
        tour_detail_iv2 = (ImageView) findViewById(R.id.tour_detail_iv2);
        tour_detail_iv3 = (ImageView) findViewById(R.id.tour_detail_iv3);
        tour_detail_iv4 = (ImageView) findViewById(R.id.tour_detail_iv4);

        // Get the message from the intent
        Intent intent = getIntent();

        name = intent.getExtras().getString("name");
        content = intent.getExtras().getString("content");
        address = intent.getExtras().getString("address");
        latitude = intent.getExtras().getDouble("latitude");
        longitude = intent.getExtras().getDouble("longitude");
        sub_url1 = intent.getExtras().getString("sub_url1");
        sub_url2 = intent.getExtras().getString("sub_url2");
        sub_url3 = intent.getExtras().getString("sub_url3");
        sub_url4 = intent.getExtras().getString("sub_url4");

        // 티맵 불러와서 띄워주기
        tmapview = new TMapView(getApplicationContext());
        naviDefaultSetting(tmapview,longitude,latitude);
        navilayout_tour.addView(tmapview);

        // 마커 찍기
        mark = new TMapMarkerItem();
        naviMarking(tmapview, mark, longitude, latitude, name);

        // 정보 입력
        name_tv.setText(name);
        content_tv.setText(content);
        address_tv.setText(address);

        // 이미지 출력
        showSubImage();
    }

    public void onClickTour(View v) {
        switch (v.getId()){
            case R.id.tour_detail_iv1:
                ShowDialog(sub_url1);
                break;
            case R.id.tour_detail_iv2:
                ShowDialog(sub_url2);
                break;
            case R.id.tour_detail_iv3:
                ShowDialog(sub_url3);
                break;
            case R.id.tour_detail_iv4:
                ShowDialog(sub_url4);
                break;

        }
    }
    private void ShowDialog(String url)
    {
        LayoutInflater dialog = LayoutInflater.from(this);
        final View dialogLayout = dialog.inflate(R.layout.img_dialog, null);
        final Dialog myDialog = new Dialog(this);

        // dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

        myDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        myDialog.getWindow().setBackgroundDrawable (new ColorDrawable(android.graphics.Color.TRANSPARENT));
        myDialog.show();
        myDialog.setContentView(dialogLayout);
        detail_img = (ImageView)dialogLayout.findViewById(R.id.detail_img);


        if(url == null) {
            detail_img.setImageResource(R.mipmap.ic_launcher);
        } else {
            Glide.with(dialog.getContext()).load(url).override(500,500).into(detail_img);
        }
        ImageView btn_cancel = (ImageView)dialogLayout.findViewById(R.id.btn_cancel);

        btn_cancel.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                myDialog.cancel();
            }
        });
    }

    public void showSubImage(){
        if(sub_url1 == null) {
            tour_detail_iv1.setImageResource(R.mipmap.ic_launcher);
        } else {
//            Glide.with(this).load(sub_url1).override(100,100).into(tour_detail_iv1);
            Picasso.with(this).load(sub_url1).placeholder(R.mipmap.ic_launcher).resize(100,100).into(tour_detail_iv1);
        }
        if(sub_url2 == null) {
            tour_detail_iv1.setImageResource(R.mipmap.ic_launcher);
        } else {
//            Glide.with(this).load(sub_url2).override(100,100).into(tour_detail_iv2);
            Picasso.with(this).load(sub_url2).placeholder(R.mipmap.ic_launcher).resize(100,100).into(tour_detail_iv2);
        }
        if(sub_url3 == null) {
            tour_detail_iv1.setImageResource(R.mipmap.ic_launcher);
        } else {
//            Glide.with(this).load(sub_url3).override(100,100).into(tour_detail_iv3);
            Picasso.with(this).load(sub_url3).placeholder(R.mipmap.ic_launcher).resize(100,100).into(tour_detail_iv3);
        }
        if(sub_url4 == null) {
            tour_detail_iv1.setImageResource(R.mipmap.ic_launcher);
        } else {
//            Glide.with(this).load(sub_url4).override(100,100).into(tour_detail_iv4);
            Picasso.with(this).load(sub_url4).placeholder(R.mipmap.ic_launcher).resize(100,100).into(tour_detail_iv4);
        }
    }

    // 네비게이션 기본 설정
    public void naviDefaultSetting(TMapView tmapview, Double longitude, Double latitude) {
        tmapview.setSKPMapApiKey("c523ac9e-d9f3-3541-8c5a-f7e08275aa76");
        tmapview.setLanguage(TMapView.LANGUAGE_KOREAN);
        tmapview.setIconVisibility(false);
        tmapview.setCenterPoint(latitude, longitude);
        tmapview.setLocationPoint(latitude, longitude);
        tmapview.setZoomLevel(15);
        tmapview.setMapType(TMapView.MAPTYPE_STANDARD);
        tmapview.setCompassMode(false);
        tmapview.setTrackingMode(false);
        tmapview.setUserScrollZoomEnable(false);


    }

    // 마커 표시 함수
    public void naviMarking(TMapView tmapview, TMapMarkerItem markeritem, double y, double x, String name){
        TMapPoint tpoint = new TMapPoint(y, x);
        markeritem.setTMapPoint(tpoint);
        markeritem.setVisible(TMapMarkerItem.VISIBLE);
        Bitmap markerbitmap = BitmapFactory.decodeResource(this.getResources(), R.drawable.marker);
        markeritem.setIcon(markerbitmap);
        markeritem.setPosition((float)0.5, (float)1.5);
        tmapview.addMarkerItem(name, markeritem);
    }

}