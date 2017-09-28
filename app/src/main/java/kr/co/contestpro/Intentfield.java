package kr.co.contestpro;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

/**
 * Created by admin on 2016-09-11.
 */
public class Intentfield extends Activity {
    ArrayList<Group_setting> al = new ArrayList<Group_setting>();
    ListView list;
    KakaoAdapter adapter;
    Group_setting m1;
    String myJSON;

    TextView mTitle;

    private static final String TAG_RESULTS="result";
    private static final String TAG_ID = "name";
    private static final String TAG_NAME = "urlimg";



    String message;

    JSONArray peoples = null;


    String mIntent_Value;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.intentfield);
        Intent intent = getIntent();
        message = intent.getStringExtra("Message");
        mTitle = (TextView)findViewById(R.id.myMessage);
        switch (message){
            case "치      킨":
                mIntent_Value = "chicken";

                break;
            case "한      식":

                mIntent_Value = "korea";
                break;
            case "일      식":

                mIntent_Value = "japan";
                break;
            case "양      식":

                mIntent_Value = "west";
                break;
            case "중      식":

                mIntent_Value = "china";
                break;
        }
        mTitle.setText(message);

        String sql ="select name,urlimg from " + mIntent_Value;
        getData("http://ec2-52-78-156-173.ap-northeast-2.compute.amazonaws.com/chicken2.php",sql);
        list = (ListView)findViewById(R.id.food_detail_lv);
        m1 = new Group_setting();


        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Group_setting item = (Group_setting) parent.getItemAtPosition(position);
                String titleStr = item.getName();

                Intent intent = new Intent(Intentfield.this,Food_detail.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("name", item.getName());
                    bundle.putString("category", mIntent_Value);
                    intent.putExtras(bundle);
                    setResult(1, intent);
                    startActivity(intent);
            }
        });
    }


        protected void showList(){
            adapter = new KakaoAdapter(getApplicationContext(), // 현재 화면의 제어권자
                    R.layout.list_item,             // 한행을 그려줄 layout
                    al);
            list.setAdapter(adapter);
            try {
                JSONObject jsonObj = new JSONObject(myJSON);
                peoples = jsonObj.getJSONArray(TAG_RESULTS);

                for(int i=0;i<peoples.length();i++){
                    JSONObject c = peoples.getJSONObject(i);
                    String name = c.getString(TAG_ID);
                    String url = c.getString(TAG_NAME);
                    adapter.addItem(name,url) ;
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
                    showList();
                }
            }
            GetDataJSON g = new GetDataJSON();
            g.execute(url,sql);
        }
}





