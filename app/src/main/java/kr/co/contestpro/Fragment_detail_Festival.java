package kr.co.contestpro;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;


public class Fragment_detail_Festival extends Activity {
    TextView mTitle;
    String message;
    ImageView Main_imageView;
    ImageView Sub_imageView_1;
    ImageView Sub_imageView_2;
    ImageView Sub_imageView_3;
    ImageView Sub_imageView_4 ;

    TextView titleTextView;
    TextView Start_TextView;
    TextView End_TextView;
    TextView Location ;
    TextView Info;

    // get Json data
    String id;
    String name;
    String start_date;
    String end_date;
    String info;
    String location;
    String url;
    String url_2;
    String url_3;
    String url_4;
    String url_5;

    //dialog
    ImageView detail_img;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.festival_intent_field);
        Intent intent = getIntent();
        message = intent.getStringExtra("Message");
        mTitle = (TextView)findViewById(R.id.intent_text_title);

        mTitle.setText(message);
        Main_imageView = (ImageView) findViewById(R.id.Festival_intent_main_imageView) ;
        Sub_imageView_1 = (ImageView) findViewById(R.id.intent_imageView_1) ;
        Sub_imageView_2 = (ImageView) findViewById(R.id.intent_imageView_2) ;
        Sub_imageView_3 = (ImageView) findViewById(R.id.intent_imageView_3) ;
        Sub_imageView_4 = (ImageView) findViewById(R.id.intent_imageView_4) ;

        titleTextView = (TextView) findViewById(R.id.intent_text_title) ;
        Start_TextView = (TextView) findViewById(R.id.intent_text_start_date) ;
        End_TextView = (TextView) findViewById(R.id.intent_text_end_date) ;
        Location = (TextView) findViewById(R.id.intent_location) ;
        Info = (TextView) findViewById(R.id.intent_info) ;


        String sql = "select * from festival where name ='"+mTitle.getText().toString()+"'";
        getData("http://ec2-52-78-156-173.ap-northeast-2.compute.amazonaws.com/festival_detail.php",sql);
//        getData("http://52.78.156.173/festival_detail.php",sql);
        //getData("http://52.78.156.173/festival_list.php");
    }

    public void onClickFestival(View v) {
        switch (v.getId()){
            case R.id.intent_imageView_1:
                ShowDialog(url_2);
                break;
            case R.id.intent_imageView_2:
                ShowDialog(url_3);
                break;
            case R.id.intent_imageView_3:
                ShowDialog(url_4);
                break;
            case R.id.intent_imageView_4:
                ShowDialog(url_5);
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

    protected void JSON_setting(String result){
        final JSONArray peoples;
        try {
            JSONObject jsonObj = new JSONObject(result);
            peoples = jsonObj.getJSONArray("result");

            JSONObject jObject = peoples .getJSONObject(0);

            id = jObject.getString("id");
            name = jObject.getString("name");
            start_date = jObject.getString("start_date");
            end_date = jObject.getString("end_date");
            info = jObject.getString("info");
            location = jObject.getString("location");
            url = jObject.getString("url");
            url_2 = jObject.getString("url_2");
            url_3 = jObject.getString("url_3");
            url_4 = jObject.getString("url_4");
            url_5 = jObject.getString("url_5");


            Glide.with(getApplicationContext()).load(url).into(Main_imageView);
//            Glide.with(getApplicationContext()).load(url_2).into(Sub_imageView_1);
//            Glide.with(getApplicationContext()).load(url_3).into(Sub_imageView_2);
//            Glide.with(getApplicationContext()).load(url_4).into(Sub_imageView_3);
//            Glide.with(getApplicationContext()).load(url_5).into(Sub_imageView_4);

//            Picasso.with(getApplicationContext()).load(url).placeholder(R.mipmap.ic_launcher).into(Main_imageView);
            Picasso.with(getApplicationContext()).load(url_2).placeholder(R.mipmap.ic_launcher).resize(100,100).into(Sub_imageView_1);
            Picasso.with(getApplicationContext()).load(url_3).placeholder(R.mipmap.ic_launcher).resize(100,100).into(Sub_imageView_2);
            Picasso.with(getApplicationContext()).load(url_4).placeholder(R.mipmap.ic_launcher).resize(100,100).into(Sub_imageView_3);
            Picasso.with(getApplicationContext()).load(url_5).placeholder(R.mipmap.ic_launcher).resize(100,100).into(Sub_imageView_4);


            titleTextView.setText(name);
            Start_TextView.setText(start_date);
            End_TextView.setText(end_date);
            Location.setText(location);
            Info.setText(info);


            Log.i("asdf",id +" / " + name +" / " + start_date +" / " + end_date + " / " + info + "/ "
                    + location + " / "+  url +" / " + url_2 + " / " + url_3 + " / " + url_4 + " / " + url_5);

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
                JSON_setting(result);
            }
        }
        GetDataJSON g = new GetDataJSON();
        g.execute(url,sql);
    }

}