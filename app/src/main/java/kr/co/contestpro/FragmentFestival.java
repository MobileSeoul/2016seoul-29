package kr.co.contestpro;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class FragmentFestival extends ListFragment {
    Festival_category_Adapter adapter;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.fragment_festival, null) ;
//        ArrayAdapter adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1) ;
//        adapter.add("축제1");
//        adapter.add("축제2");
//        adapter.add("축제3");


/*        adapter = new Festival_category_Adapter();
        setListAdapter(adapter) ;

        // 첫 번째 아이템 추가.
        adapter.addItem("test1","asdf",ContextCompat.getDrawable(getActivity(), R.mipmap.ic_launcher));
        // 두 번째 아이템 추가.
        adapter.addItem("test2", "Account Circle Black 36dp",ContextCompat.getDrawable(getActivity(), R.mipmap.ic_launcher)) ;
        // 세 번째 아이템 추가.
        adapter.addItem("test3", "Assignment Ind Black 36dp",ContextCompat.getDrawable(getActivity(), R.mipmap.ic_launcher)) ;
        ListView listview = (ListView) view.findViewById(R.id.festival_lv) ;
//        setListAdapter(adapter) ;
        listview.setAdapter(adapter) ;
        */

//        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Intent intent = new Intent(getContext(),Intentfield.class);
//                intent.putExtra("Message", (String) ((TextView) view).getText());
//                startActivity(intent);
//            }
//        });

//        adapter = new Festival_category_Adapter() ;
//        setListAdapter(adapter) ;

//        adapter.addItem("test1","asdf",ContextCompat.getDrawable(getActivity(), R.mipmap.ic_launcher));
//        return view;

      //  getData("http://52.78.156.173/festial_list.php");
        getData("http://52.78.156.173/festival_list.php");


        return super.onCreateView(inflater, container, savedInstanceState);
    }

  /*  @Override
    public void onListItemClick (ListView l, View v, int position, long id) {
        // get TextView's Text.
        Festival_category_item item = (Festival_category_item) l.getItemAtPosition(position) ;

        String titleStr = item.getTitle() ;
        String descStr = item.getDesc() ;

        // TODO : use item data.

        Intent intent = new Intent(getContext(),Intentfield.class);
        intent.putExtra("Message", titleStr);
//        intent.putExtra("Message", (String) ((TextView) v).getText());
        startActivity(intent);


    }*/

    void JSOPParser(String result){
        try {
            JSONArray jarray = new JSONArray(result);   // JSONArray 생성

            // Adapter 생성 및 Adapter 지정.
            adapter = new Festival_category_Adapter() ;
            setListAdapter(adapter) ;
//
//            adapter.addItem("test1","asdf",ContextCompat.getDrawable(getActivity(), R.mipmap.ic_launcher));
//            adapter.addItem("test1","asdf","asdf","asdf");

            for(int i=0; i < jarray.length(); i++){
                JSONObject jObject = jarray.getJSONObject(i);  // JSONObject 추출
                String id = jObject.getString("id");
                String name = jObject.getString("name");
                String start_date = jObject.getString("start_date");
                String end_date = jObject.getString("end_date");
                String info = jObject.getString("info");
                String location = jObject.getString("location");
                String url = jObject.getString("url");
                String url_2 = jObject.getString("url_2");
                String url_3 = jObject.getString("url_3");
                String url_4 = jObject.getString("url_4");
                String url_5 = jObject.getString("url_5");

//                String content = jObject.getString("content");
//                String location = jObject.getString("location");
//              String url  = jObject.getString("picture");

//                 첫 번째 아이템 추가.
                adapter.addItem(name,start_date,end_date, url);

                Log.i("asdf",id +" / " + name +" / " + start_date +" / " + end_date );
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

            public void getData(String url){
                class GetDataJSON extends AsyncTask<String, Void, String> {

            @Override
            protected String doInBackground(String... params) {

                String uri = params[0];

                BufferedReader bufferedReader = null;
                try {
                    URL url = new URL(uri);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
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
                JSOPParser(result);
            }
        }
        GetDataJSON g = new GetDataJSON();
        g.execute(url);
    }
    @Override
    public void onListItemClick (ListView l, View v, int position, long id) {
        // get TextView's Text.
        // TODO : use item data.
        Festival_category_item item = (Festival_category_item) l.getItemAtPosition(position) ;

        String titleStr = item.getTitle();


        Intent intent = new Intent(getContext(),Fragment_detail_Festival.class);
        intent.putExtra("Message", titleStr);
        startActivity(intent);
    }
}