package kr.co.contestpro;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by Administrator on 2016-10-25.
 */
public class Food_detail extends Activity {
    String myJSON;

    TMapView tmapview;
    TMapMarkerItem mark;

    private static final String TAG_RESULTS="result";
    private static final String TAG_ID = "name";
    private static final String TAG_NAME = "urlimg";
    private static final String TAG_CALL = "call_num";
    private static final String TAG_COD_X = "cod_x";
    private static final String TAG_COD_Y = "cod_y";
    private static final String TAG_MENU = "menu";
    private static final String TAG_ADDRESS = "address";
    private static final String TAG_OPERATIONTIME = "operationtime";
    private static final String TAG_URLIMG2 = "urlimg2";
    private static final String TAG_URLIMG3 = "urlimg3";
    private static final String TAG_URLIMG4 = "urlimg4";

    //value
    ImageView thumb_nail,iv1,iv2,iv3;
    TextView mName,mCategory,mCall,mAddress,mOperation,mMenu;
    JSONArray peoples = null;

    String category;
    String name;
    double cod_x;
    double cod_y;

    RelativeLayout navilayout;

    ImageView detail_img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.food_detail);

        //선언부
        mName = (TextView)findViewById(R.id.food_detail_name);
        mCall = (TextView)findViewById(R.id.food_detail_callnum);
        mMenu = (TextView)findViewById(R.id.food_detail_menu);
        mAddress = (TextView)findViewById(R.id.food_detail_address);
        mOperation = (TextView)findViewById(R.id.food_detail_operation);


        iv1 = (ImageView) findViewById(R.id.food_detail_iv1);
        iv2 = (ImageView) findViewById(R.id.food_detail_iv2);
        iv3 = (ImageView) findViewById(R.id.food_detail_iv3);

        thumb_nail = (ImageView)findViewById(R.id.food_detail_thumb);
        mCategory = (TextView)findViewById(R.id.food_detail_category);
        navilayout = (RelativeLayout)findViewById(R.id.navilayout);
        name = null;
        category = null;

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if( bundle != null ) {

            name = bundle.getString("name");
            category = bundle.getString("category");

        }
        switch (category){
            case "chicken":
                mCategory.setText("치킨");
                break;
            case "korea":
                mCategory.setText("한식");
                break;
            case "japan":
                mCategory.setText("일식");
                break;
            case "west":
                mCategory.setText("양식");
                break;
            case "china":
                mCategory.setText("중식");
                break;

        }
        //String message = intent.getStringExtra("food_detail");
       // mName.setText(name);
       // mCategory.setText(category);

        String sql ="select * from " + category +" where name='"+name+"'";
        getData("http://ec2-52-78-156-173.ap-northeast-2.compute.amazonaws.com/chicken2.php",sql);


        tmapview = new TMapView(this);
        naviDefaultSetting(tmapview,cod_y,cod_x);
//        navilayout.addView(tmapview);
        mark = new TMapMarkerItem();
        tmapview.removeAllMarkerItem();


    }
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.food_detail_iv1:
                ShowDialog(urlimg2);
                break;
            case R.id.food_detail_iv2:
                ShowDialog(urlimg3);
                break;
            case R.id.food_detail_iv3:
                ShowDialog(urlimg4);
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

    // 네비게이션 기본 설정
    public void naviDefaultSetting(TMapView tmapview, double longitude, double latitude) {
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
    String urlimg2 = "";
    String urlimg3 ="";
    String urlimg4 ="";
    protected void showList(){
        String aname;
        String call_num ="";
        String url ="";

        String menu="";
        String address ="";
        String operationtime ="";

        try {
            JSONObject jsonObj = new JSONObject(myJSON);
            peoples = jsonObj.getJSONArray(TAG_RESULTS);
            //mName.setText("바뀌기전");
            for(int i=0;i<peoples.length();i++){
                JSONObject c = peoples.getJSONObject(i);
                aname = c.getString(TAG_ID);
                url = c.getString(TAG_NAME);
                call_num = c.getString(TAG_CALL);
                cod_x = c.getDouble(TAG_COD_X);
                cod_y = c.getDouble(TAG_COD_Y);
                menu = c.getString(TAG_MENU);
                address = c.getString(TAG_ADDRESS);
                operationtime = c.getString(TAG_OPERATIONTIME);
                urlimg2 = c.getString(TAG_URLIMG2);
                urlimg3 = c.getString(TAG_URLIMG3);
                urlimg4 = c.getString(TAG_URLIMG4);
                if(url == null) {
                    thumb_nail.setImageResource(R.mipmap.ic_launcher);
                } else {
                    Glide.with(this).load(url).into(thumb_nail);
                }
                mCall.setText(call_num);
                mName.setText(aname);
                mMenu.setText("대표 메뉴 : "+ menu);

                mAddress.setText(address);

                mOperation.setText(operationtime);
                naviMarking(tmapview, mark, cod_x, cod_y, aname);
                if(urlimg2 == null) {
                    iv1.setImageResource(R.mipmap.ic_launcher);
                } else {
                    Glide.with(this).load(urlimg2).into(iv1);
                }
                if(urlimg3 == null) {
                    iv2.setImageResource(R.mipmap.ic_launcher);
                } else {
                    Glide.with(this).load(urlimg3).into(iv2);
                }
                if(urlimg4 == null) {
                    iv3.setImageResource(R.mipmap.ic_launcher);
                } else {
                    Glide.with(this).load(urlimg4).into(iv3);
                }
                naviDefaultSetting(tmapview,cod_x,cod_y);
                navilayout.addView(tmapview);
            }



        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
    public void getData(String url,String sql){
        class GetDataJSON extends AsyncTask<String, Void, String> {

            @Override
            protected String doInBackground(String... params) {

                String uri = params[0];
                String sql = (String)params[1];
                BufferedReader bufferedReader = null;
                try {
                    String data = URLEncoder.encode("sql","UTF-8") +"="+ URLEncoder.encode(sql,"UTF-8");
                    URL url = new URL(uri);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();

                    con.setDoOutput(true);
                    OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream(),"UTF-8") ;

                    wr.write(data);
                    wr.flush();

                    StringBuilder sb = new StringBuilder();

                    bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));

                    String json;
                    while((json = bufferedReader.readLine())!= null){
                        sb.append(json+"\n");
                    }

                    return sb.toString().trim();

                }catch(Exception e){
                    return null;
                }
            }

            @Override
            protected void onPostExecute(String result){
                myJSON=result;
                //Toast.makeText(getApplicationContext(),myJSON,Toast.LENGTH_SHORT).show();
                showList();
            }
        }
        GetDataJSON g = new GetDataJSON();
        g.execute(url,sql);
    }



}
