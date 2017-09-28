package kr.co.contestpro;

import android.app.ActionBar;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.text.Layout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.skp.Tmap.TMapMarkerItem;
import com.skp.Tmap.TMapPoint;
import com.skp.Tmap.TMapView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class FragmentPlace extends Fragment {

    private static final String TAG_RESULT = "result";
    private static final String TAG_LINE1 = "line1";
    private static final String TAG_LINE2 = "line2";
    private static final String TAG_LINE3 = "line3";
    private static final String TAG_F_W_U_1 = "first1_week_up";
    private static final String TAG_F_W_D_1 = "first1_week_down";
    private static final String TAG_L_W_U_1 = "last1_week_up";
    private static final String TAG_L_W_D_1 = "last1_week_down";
    private static final String TAG_F_H_U_1 = "first1_weekend_up";
    private static final String TAG_F_H_D_1 = "first1_weekend_down";
    private static final String TAG_L_H_U_1 = "last1_weekend_up";
    private static final String TAG_L_H_D_1 = "last1_weekend_down";
    private static final String TAG_F_W_U_2 = "first2_week_up";
    private static final String TAG_F_W_D_2 = "first2_week_down";
    private static final String TAG_L_W_U_2 = "last2_week_up";
    private static final String TAG_L_W_D_2 = "last2_week_down";
    private static final String TAG_F_H_U_2 = "first2_weekend_up";
    private static final String TAG_F_H_D_2 = "first2_weekend_down";
    private static final String TAG_L_H_U_2 = "last2_weekend_up";
    private static final String TAG_L_H_D_2 = "last2_weekend_down";
    private static final String TAG_F_W_U_3 = "first3_week_up";
    private static final String TAG_F_W_D_3 = "first3_week_down";
    private static final String TAG_L_W_U_3 = "last3_week_up";
    private static final String TAG_L_W_D_3 = "last3_week_down";
    private static final String TAG_F_H_U_3 = "first3_weekend_up";
    private static final String TAG_F_H_D_3 = "first3_weekend_down";
    private static final String TAG_L_H_U_3 = "last3_weekend_up";
    private static final String TAG_L_H_D_3 = "last3_weekend_down";
    private static final String TAG_EXITINFO = "exitinfo";
    private static final String TAG_EXITCOUNT = "exitcount";

    int row = 1;
    TableLayout table;
    TableLayout table2;

    TextView exitinfotf;

    String myJSON;
    String urlpath = "http://52.78.156.173/sub_info.php?name=";
    String sub_data[] = new String[28];
    String sub_exitcount = "";

    TextView  sub_bt2, sub_bt3, sub_bt4;
    TextView sub_bt1;
    TMapView tmapview;
    TMapMarkerItem mark;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_place, null);

        RelativeLayout navilayout = (RelativeLayout)view.findViewById(R.id.navilayout);

        table = (TableLayout)view.findViewById(R.id.infotable);
        table2 = (TableLayout)view.findViewById(R.id.exitinfotable);

        sub_bt1 = (TextView)view.findViewById(R.id.bt_sub1);
        sub_bt2 = (TextView)view.findViewById(R.id.bt_sub2);
        sub_bt3 = (TextView)view.findViewById(R.id.bt_sub3);
        sub_bt4 = (TextView)view.findViewById(R.id.bt_sub4);

        exitinfotf = (TextView)view.findViewById(R.id.exitinfotf);

        tmapview = new TMapView(getContext());
        naviDefaultSetting(tmapview);
        navilayout.addView(tmapview);

        mark = new TMapMarkerItem();

        sub_bt1.setOnClickListener(subClick);
        sub_bt2.setOnClickListener(subClick);
        sub_bt3.setOnClickListener(subClick);
        sub_bt4.setOnClickListener(subClick);

        return view;
    }

    public void naviDefaultSetting(TMapView tmapview){
        tmapview.setSKPMapApiKey("c523ac9e-d9f3-3541-8c5a-f7e08275aa76");
        tmapview.setLanguage(TMapView.LANGUAGE_KOREAN);
        tmapview.setIconVisibility(false);
        tmapview.setCenterPoint(126.987597, 37.568097);
        tmapview.setLocationPoint(126.987597, 37.568097);
        tmapview.setZoomLevel(15);
        tmapview.setMapType(TMapView.MAPTYPE_STANDARD);
        tmapview.setCompassMode(false);
        tmapview.setTrackingMode(false);
        tmapview.setUserScrollZoomEnable(false);
    }

    public void naviMarking(TMapView tmapview, TMapMarkerItem markeritem, double y, double x, String name){
        TMapPoint tpoint = new TMapPoint(y, x);
        markeritem.setTMapPoint(tpoint);
        markeritem.setVisible(TMapMarkerItem.VISIBLE);
        Bitmap markerbitmap = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.marker);
        markeritem.setIcon(markerbitmap);
        markeritem.setPosition((float)0.5, (float)1.5);
        tmapview.addMarkerItem(name, markeritem);
    }

    public void settingButton(int set){
        if(set == 1){
            sub_bt1.setEnabled(false);
            sub_bt2.setEnabled(false);
            sub_bt3.setEnabled(false);
            sub_bt4.setEnabled(false);
        }else if(set == 2){
            sub_bt1.setEnabled(true);
            sub_bt2.setEnabled(true);
            sub_bt3.setEnabled(true);
            sub_bt4.setEnabled(true);
        }
    }

    Button.OnClickListener subClick = new View.OnClickListener(){
        public void onClick(View v){
            switch(v.getId()){
                case R.id.bt_sub1:
                    settingButton(1);
                    tmapview.removeAllMarkerItem();
                    naviMarking(tmapview, mark, 37.569741, 126.982967, "종각역");
                    initializeData();
                    row = 1;
                    getData(urlpath + "1");
                    new Handler().postDelayed(new Runnable(){
                        @Override
                        public void run(){
                            makeTable(row);
                            exitinfotf.setTextColor(Color.parseColor("#ffffff"));
                            exitinfotf.setText("출구정보");

                            trimExitInfo();
                            settingButton(2);
                        }
                    }, 500);
                    break;
                case R.id.bt_sub2:
                    settingButton(1);
                    tmapview.removeAllMarkerItem();
                    naviMarking(tmapview, mark, 37.571410, 126.991950, "종로3가역");
                    initializeData();
                    row = 3;
                    getData(urlpath + "2");
                    new Handler().postDelayed(new Runnable(){
                        @Override
                        public void run(){
                            makeTable(row);
                            exitinfotf.setText("출구정보");
                            trimExitInfo();
                            settingButton(2);
                        }
                    }, 500);
                    break;
                case R.id.bt_sub3:
                    settingButton(1);
                    tmapview.removeAllMarkerItem();
                    naviMarking(tmapview, mark, 37.565653, 126.982603, "을지로입구역");
                    initializeData();
                    row = 1;
                    getData(urlpath + "3");
                    new Handler().postDelayed(new Runnable(){
                        @Override
                        public void run(){
                            makeTable(row);
                            exitinfotf.setText("출구정보");
                            trimExitInfo();
                            settingButton(2);
                        }
                    }, 500);
                    break;
                case R.id.bt_sub4:
                    settingButton(1);
                    tmapview.removeAllMarkerItem();
                    naviMarking(tmapview, mark, 37.565932, 126.992611, "을지로3가역");
                    initializeData();
                    row = 2;
                    getData(urlpath + "4");
                    new Handler().postDelayed(new Runnable(){
                        @Override
                        public void run(){
                            makeTable(row);
                            exitinfotf.setText("출구정보");
                            trimExitInfo();
                            settingButton(2);
                        }
                    }, 500);
                    break;
            }
        }
    };

    public void initializeData(){
        for(int i=0;i<28;i++){
            sub_data[i] = "";
        }
    }

    public void trimExitInfo(){
        int sub_count = Integer.parseInt(sub_exitcount);
        String[] exitinfoArr = sub_data[27].split(":::");

        table2.removeAllViews();
        TableRow.LayoutParams rowLayout2 = new TableRow.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        TableRow row2[] = new TableRow[sub_count];
        TextView text2[][] = new TextView[sub_count][2];


        int sub_exitinfo_count = 0;


        for(int i = 0; i<sub_count;i++){
            row2[i] = new TableRow(getActivity());

            for(int j = 0; j<2;j++){
                text2[i][j] = new TextView(getActivity());
                text2[i][j].setTextColor(Color.parseColor("#ffffff"));
                if(j==0){
                    text2[i][j].setGravity(Gravity.CENTER);
                }else if(j==1){
                    text2[i][j].setGravity(Gravity.LEFT);
                }


                text2[i][j].setText(exitinfoArr[sub_exitinfo_count]+"    ");
                sub_exitinfo_count++;
                row2[i].addView(text2[i][j]);
            }

            table2.addView(row2[i], rowLayout2);
        }
    }

    protected void insertData(){
        try{
            JSONObject jsonObj = new JSONObject(myJSON);
            JSONArray datas = jsonObj.getJSONArray(TAG_RESULT);
            JSONObject c = datas.getJSONObject(0);
            int i=0;
            sub_data[i++] = c.getString(TAG_LINE1);
            sub_data[i++] = c.getString(TAG_F_W_U_1);
            sub_data[i++] = c.getString(TAG_F_W_D_1);
            sub_data[i++] = c.getString(TAG_L_W_U_1);
            sub_data[i++] = c.getString(TAG_L_W_D_1);
            sub_data[i++] = c.getString(TAG_F_H_U_1);
            sub_data[i++] = c.getString(TAG_F_H_D_1);
            sub_data[i++] = c.getString(TAG_L_H_U_1);
            sub_data[i++] = c.getString(TAG_L_H_D_1);
            sub_data[i++] = c.getString(TAG_LINE2);
            sub_data[i++] = c.getString(TAG_F_W_U_2);
            sub_data[i++] = c.getString(TAG_F_W_D_2);
            sub_data[i++] = c.getString(TAG_L_W_U_2);
            sub_data[i++] = c.getString(TAG_L_W_D_2);
            sub_data[i++] = c.getString(TAG_F_H_U_2);
            sub_data[i++] = c.getString(TAG_F_H_D_2);
            sub_data[i++] = c.getString(TAG_L_H_U_2);
            sub_data[i++] = c.getString(TAG_L_H_D_2);
            sub_data[i++] = c.getString(TAG_LINE3);
            sub_data[i++] = c.getString(TAG_F_W_U_3);
            sub_data[i++] = c.getString(TAG_F_W_D_3);
            sub_data[i++] = c.getString(TAG_L_W_U_3);
            sub_data[i++] = c.getString(TAG_L_W_D_3);
            sub_data[i++] = c.getString(TAG_F_H_U_3);
            sub_data[i++] = c.getString(TAG_F_H_D_3);
            sub_data[i++] = c.getString(TAG_L_H_U_3);
            sub_data[i++] = c.getString(TAG_L_H_D_3);
            sub_data[i++] = c.getString(TAG_EXITINFO);
            sub_exitcount = c.getString(TAG_EXITCOUNT);

        }catch(JSONException e){
            e.printStackTrace();
        }
    }

    public void makeTable(int count){
        int data_tmp = 0;
        table.removeAllViews();
        count = count * 2 + 2;
        TableRow.LayoutParams rowLayout = new TableRow.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        TableRow row[] = new TableRow[count];
        TextView text[][] = new TextView[count][6];

        for(int i = 0; i <count;i++){
            row[i] = new TableRow(getActivity());

            // 텍스트뷰 초기화
            for(int j = 0;j<6;j++){
                text[i][j] = new TextView(getActivity());
                text[i][j].setGravity(Gravity.CENTER);
                text[i][j].setTextColor(Color.parseColor("#ffffff"));
            }

            // 텍스트뷰 내용 변경
            if(i==0){
                text[i][2].setText("첫");
                text[i][3].setText("차");
                text[i][4].setText("막");
                text[i][5].setText("차");
            }else if(i==1){
                text[i][2].setText("상행");
                text[i][3].setText("하행");
                text[i][4].setText("상행");
                text[i][5].setText("하행");
            }else{
                if(i==2 || i==4 || i==6) {
                    String str_line = null;
                    if(i==2){
                        str_line = sub_data[0];
                    }else if(i==4){
                        str_line = sub_data[9];
                    }else{
                        str_line = sub_data[18];
                    }
                    text[i][0].setText(str_line + "호선 ");
                    text[i][1].setText("평일 ");
                }else if(i==3 || i==5 || i==7){
                    text[i][1].setText("주말");
                }
                for(int j = 2;j<6;j++){
                    if(data_tmp == 0 || data_tmp == 9 || data_tmp == 18){
                        data_tmp++;
                    }
                    text[i][j].setText(" " + sub_data[data_tmp] + " ");
                    data_tmp ++;
                }
            }

            // tablerow에 텍스트뷰 추가
            for(int j = 0;j<6;j++){
                row[i].addView(text[i][j]);
            }

            table.addView(row[i], rowLayout);
        }
    }

    public void getData(String url){
        class GetDataJSON extends AsyncTask<String, Void, String> {
            @Override
            protected String doInBackground(String... params){
                String uri = params[0];

                BufferedReader bufferedReader = null;
                try{
                    URL url = new URL(uri);
                    HttpURLConnection con = (HttpURLConnection)url.openConnection();
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
                myJSON = result;
                insertData();
            }
        }

        GetDataJSON g = new GetDataJSON();
        g.execute(url);
    }
}