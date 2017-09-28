package kr.co.contestpro;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


public class FragmentFood extends ListFragment {

    Food_category_Adapter adapter ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Adapter 생성 및 Adapter 지정.
        adapter = new Food_category_Adapter() ;
        setListAdapter(adapter) ;

        // 첫 번째 아이템 추가.
        adapter.addItem(R.drawable.category_chicken, "치      킨") ;
        // 두 번째 아이템 추가.
        adapter.addItem(R.drawable.category_korean, "한      식") ;
        adapter.addItem(R.drawable.category_japan, "일      식") ;
        adapter.addItem(R.drawable.category_china, "중      식") ;
        adapter.addItem(R.drawable.category_west, "양      식") ;

        return super.onCreateView(inflater, container, savedInstanceState);
    }
    @Override
    public void onListItemClick (ListView l, View v, int position, long id) {
        // get TextView's Text.
        Food_category_item item = (Food_category_item) l.getItemAtPosition(position) ;

        String titleStr = item.getTitle() ;
        int iconDrawable = item.getIcon() ;

        // TODO : use item data.

        Intent intent = new Intent(getContext(),Intentfield.class);
        intent.putExtra("Message", titleStr);
        startActivity(intent);


    }
}