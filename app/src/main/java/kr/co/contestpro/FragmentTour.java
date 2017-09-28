package kr.co.contestpro;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v4.content.ContextCompat;
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


public class FragmentTour extends ListFragment {

    TourAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


//        // Adapter 생성 및 Adapter 지정.
//        adapter = new TourAdapter() ;
//        setListAdapter(adapter) ;
//
//        // 첫 번째 아이템 추가.
//        adapter.addItem("1", ContextCompat.getDrawable(getActivity(), R.mipmap.ic_launcher), "Box", "Account Box Black 36dp", "https://s3.ap-northeast-2.amazonaws.com/kscontest/%EB%94%94%EB%94%94.jpg") ;
//        // 두 번째 아이템 추가.
//        adapter.addItem("1", ContextCompat.getDrawable(getActivity(), R.mipmap.ic_launcher), "Circle", "Account Circle Black 36dp","None") ;
//        // 세 번째 아이템 추가.
//        adapter.addItem("1", ContextCompat.getDrawable(getActivity(), R.mipmap.ic_launcher), "End", "Assignment Ind Black 36dp","https://s3.ap-northeast-2.amazonaws.com/kscontest/%EB%94%94%EB%94%94.jpg") ;

        getData("http://52.78.156.173/tour_list.php");

        return super.onCreateView(inflater, container, savedInstanceState);
    }


    @Override
    public void onListItemClick (ListView l, View v, int position, long id) {
        // get TextView's Text.
        TourItem item = (TourItem) l.getItemAtPosition(position) ;

//        Toast.makeText(getActivity() ,item.getName() , Toast.LENGTH_SHORT).show(); // 토스트
        // Render View first

        Intent intent = new Intent(getContext(),TourDetail.class);
        intent.putExtra("name", item.getName());
        intent.putExtra("content", item.getContent());
        intent.putExtra("address", item.getAddress());
        intent.putExtra("longitude", item.getLongitude());
        intent.putExtra("latitude", item.getLatitude());
        intent.putExtra("sub_url1", item.getSub1());
        intent.putExtra("sub_url2", item.getSub2());
        intent.putExtra("sub_url3", item.getSub3());
        intent.putExtra("sub_url4", item.getSub4());

        startActivity(intent);
    }

    void JSOPParser(String result){
        try {
            JSONArray jarray = new JSONArray(result);   // JSONArray 생성

            // Adapter 생성 및 Adapter 지정.
            adapter = new TourAdapter() ;
            setListAdapter(adapter) ;

            for(int i=0; i < jarray.length(); i++){
                JSONObject jObject = jarray.getJSONObject(i);  // JSONObject 추출
                String id = jObject.getString("id");
                String name = jObject.getString("name");
                String content = jObject.getString("content");
                String address = jObject.getString("address");
                String url = jObject.getString("picture");
                Double longitude = jObject.getDouble("longitude");
                Double latitude = jObject.getDouble("latitude");
                String subUrl1 = jObject.getString("sub_img1");
                String subUrl2 = jObject.getString("sub_img2");
                String subUrl3 = jObject.getString("sub_img3");
                String subUrl4 = jObject.getString("sub_img4");

                // 첫 번째 아이템 추가.
                adapter.addItem(id, ContextCompat.getDrawable(getActivity(), R.mipmap.ic_launcher), name, content, address, longitude, latitude, url, subUrl1, subUrl2, subUrl3, subUrl4) ;
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
}