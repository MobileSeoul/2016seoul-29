package kr.co.contestpro;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import static android.graphics.Color.*;


public class MainActivity extends ActionBarActivity implements OnClickListener{

    LinearLayout btn[] = new LinearLayout[4];
    ViewPager viewPager = null;
    Thread thread = null;
    Handler handler = null;
    int p=0;   //페이지번호
    int v=1;   //화면 전환 뱡향
    static LinearLayout festival_ll;
    LinearLayout food_ll;
    LinearLayout tour_ll;
    LinearLayout place_ll;
    RelativeLayout start_ll;


    TextView food_tv;
    TextView festival_tv;
    TextView tour_tv;
    TextView place_tv;

    static int flagStart = 0;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //viewPager
        viewPager = (ViewPager)findViewById(R.id.viewPager);
        MyViewPagerAdapter adapter = new MyViewPagerAdapter(getSupportFragmentManager());

        viewPager.setAdapter(adapter);

        start_ll = (RelativeLayout)findViewById(R.id.start_ll);

        Handler mHandler = new Handler();

        if(flagStart ==0) {
            mHandler.postDelayed(new Runnable() {
                //Do Something
                @Override
                public void run() {
                    start_ll.setVisibility(View.GONE);
                }

            }, 4000); // 1000ms
            flagStart =1;
        }


        festival_ll = (LinearLayout) findViewById(R.id.festival_ll);
        food_ll = (LinearLayout) findViewById(R.id.food_ll);
        tour_ll = (LinearLayout) findViewById(R.id.tour_ll);
        place_ll = (LinearLayout) findViewById(R.id.place_ll);

        food_tv = (TextView) findViewById(R.id.food_tv);
        festival_tv = (TextView) findViewById(R.id.festival_tv);
        tour_tv = (TextView) findViewById(R.id.tour_tv);
        place_tv = (TextView) findViewById(R.id.place_tv);

        btn[0] = festival_ll;
        btn[1] = tour_ll;
        btn[2] = food_ll;

        /*
        btn[1] = food_ll;
        btn[2] = tour_ll;
        */
        btn[3] = place_ll;

        for(int i=0;i<btn.length; i++){
            btn[i].setOnClickListener(this);
        }

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener(){
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        /*
                        festival_ll.setBackgroundColor(Color.rgb(255, 255, 255));
                        food_ll.setBackgroundColor(Color.rgb(236, 236, 236));
                        tour_ll.setBackgroundColor(Color.rgb(236, 236, 236));
                        place_ll.setBackgroundColor(Color.rgb(236, 236, 236));
                        */
                        festival_ll.setBackgroundColor(Color.parseColor("#000000"));
                        tour_ll.setBackgroundColor(Color.parseColor("#FFFFFF"));
                        food_ll.setBackgroundColor(Color.parseColor("#FFFFFF"));
                        place_ll.setBackgroundColor(Color.parseColor("#FFFFFF"));

                        festival_tv.setTextColor(Color.parseColor("#FFFFFF"));
                        tour_tv.setTextColor(Color.parseColor("#000000"));
                        food_tv.setTextColor(Color.parseColor("#000000"));
                        place_tv.setTextColor(Color.parseColor("#000000"));


                        break;
                    case 1:
//                        festival_ll.setBackgroundColor(Color.rgb(236, 236, 236));
//                        food_ll.setBackgroundColor(Color.rgb(236, 236, 236));
//                        tour_ll.setBackgroundColor(Color.rgb(255, 255, 255));
//                        place_ll.setBackgroundColor(Color.rgb(236, 236, 236));
                        festival_ll.setBackgroundColor(Color.parseColor("#FFFFFF"));
                        tour_ll.setBackgroundColor(Color.parseColor("#000000"));
                        food_ll.setBackgroundColor(Color.parseColor("#FFFFFF"));
                        place_ll.setBackgroundColor(Color.parseColor("#FFFFFF"));

                        festival_tv.setTextColor(Color.parseColor("#000000"));
                        tour_tv.setTextColor(Color.parseColor("#FFFFFF"));
                        food_tv.setTextColor(Color.parseColor("#000000"));
                        place_tv.setTextColor(Color.parseColor("#000000"));
                        break;
                    case 2:
//                        festival_ll.setBackgroundColor(Color.rgb(236, 236, 236));
//                        food_ll.setBackgroundColor(Color.rgb(255, 255, 255));
//                        tour_ll.setBackgroundColor(Color.rgb(236, 236, 236));
//                        place_ll.setBackgroundColor(Color.rgb(236, 236, 236));
                        festival_ll.setBackgroundColor(Color.parseColor("#FFFFFF"));
                        tour_ll.setBackgroundColor(Color.parseColor("#FFFFFF"));
                        food_ll.setBackgroundColor(Color.parseColor("#000000"));
                        place_ll.setBackgroundColor(Color.parseColor("#FFFFFF"));

                        festival_tv.setTextColor(Color.parseColor("#000000"));
                        tour_tv.setTextColor(Color.parseColor("#000000"));
                        food_tv.setTextColor(Color.parseColor("#FFFFFF"));
                        place_tv.setTextColor(Color.parseColor("#000000"));
                        break;
                    case 3:
//                        festival_ll.setBackgroundColor(Color.rgb(236, 236, 236));
//                        food_ll.setBackgroundColor(Color.rgb(236, 236, 236));
//                        tour_ll.setBackgroundColor(Color.rgb(236, 236, 236));
//                        place_ll.setBackgroundColor(Color.rgb(255, 255, 255));
                        festival_ll.setBackgroundColor(Color.parseColor("#FFFFFF"));
                        tour_ll.setBackgroundColor(Color.parseColor("#FFFFFF"));
                        food_ll.setBackgroundColor(Color.parseColor("#FFFFFF"));
                        place_ll.setBackgroundColor(Color.parseColor("#000000"));

                        festival_tv.setTextColor(Color.parseColor("#000000"));
                        tour_tv.setTextColor(Color.parseColor("#000000"));
                        food_tv.setTextColor(Color.parseColor("#000000"));
                        place_tv.setTextColor(Color.parseColor("#FFFFFF"));
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }


    @Override
    public void onClick(View v) {

        switch(v.getId()){
            case R.id.festival_ll:
                viewPager.setCurrentItem(0);
                festival_ll.setBackgroundColor(Color.parseColor("#000000"));
                tour_ll.setBackgroundColor(Color.parseColor("#FFFFFF"));
                food_ll.setBackgroundColor(Color.parseColor("#FFFFFF"));
                place_ll.setBackgroundColor(Color.parseColor("#FFFFFF"));

                festival_tv.setTextColor(Color.parseColor("#FFFFFF"));
                tour_tv.setTextColor(Color.parseColor("#000000"));
                food_tv.setTextColor(Color.parseColor("#000000"));
                place_tv.setTextColor(Color.parseColor("#000000"));
                break;
            case R.id.tour_ll:
                viewPager.setCurrentItem(1);
                festival_ll.setBackgroundColor(Color.parseColor("#FFFFFF"));
                tour_ll.setBackgroundColor(Color.parseColor("#000000"));
                food_ll.setBackgroundColor(Color.parseColor("#FFFFFF"));
                place_ll.setBackgroundColor(Color.parseColor("#FFFFFF"));

                festival_tv.setTextColor(Color.parseColor("#000000"));
                tour_tv.setTextColor(Color.parseColor("#FFFFFF"));
                food_tv.setTextColor(Color.parseColor("#000000"));
                place_tv.setTextColor(Color.parseColor("#000000"));
                break;
            case R.id.food_ll:
                viewPager.setCurrentItem(2);
                festival_ll.setBackgroundColor(Color.parseColor("#FFFFFF"));
                tour_ll.setBackgroundColor(Color.parseColor("#FFFFFF"));
                food_ll.setBackgroundColor(Color.parseColor("#000000"));
                place_ll.setBackgroundColor(Color.parseColor("#FFFFFF"));

                festival_tv.setTextColor(Color.parseColor("#000000"));
                tour_tv.setTextColor(Color.parseColor("#000000"));
                food_tv.setTextColor(Color.parseColor("#FFFFFF"));
                place_tv.setTextColor(Color.parseColor("#000000"));
                break;
            case R.id.place_ll:
                viewPager.setCurrentItem(3);
                festival_ll.setBackgroundColor(Color.parseColor("#FFFFFF"));
                tour_ll.setBackgroundColor(Color.parseColor("#FFFFFF"));
                food_ll.setBackgroundColor(Color.parseColor("#FFFFFF"));
                place_ll.setBackgroundColor(Color.parseColor("#000000"));

                festival_tv.setTextColor(Color.parseColor("#000000"));
                tour_tv.setTextColor(Color.parseColor("#000000"));
                food_tv.setTextColor(Color.parseColor("#000000"));
                place_tv.setTextColor(Color.parseColor("#FFFFFF"));
                break;
            default:
                break;

        }

    }
}