package com.locensate.firstlinecode;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends AppCompatActivity {

    @BindView(R.id.recyclerView_hone)
    RecyclerView recyclerViewHone;
    @BindView(R.id.activity_home)
    RelativeLayout activityHome;
    private static final String TAG = "HomeActivity";

    /**
     *
     */
    public static String[] items = {"Intent的高级用法", "体验Activity的生命周期", "自定义标题栏", "RecyclerView实现对话功能", "初识Fragment"
            , "大喇叭BroadCast", "数据持久化", "手机多媒体", "百度地图", "多媒体++", "RxJava + Retrofit", "Custom View Test","IPC进程间通信"};

    @BindView(R.id.srl_home)
    SwipeRefreshLayout srlHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        initData();
    }

    private void initData() {
        Log.e(TAG, "initData: +++++++++++++=========" + recyclerViewHone.getId(), null);
        srlHome.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                srlHome.setRefreshing(false);
            }
        });
        recyclerViewHone.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewHone.setAdapter(new HomeAdapter(this));
        recyclerViewHone.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
                return false;
            }

            @Override
            public void onTouchEvent(RecyclerView rv, MotionEvent e) {
                Log.e(TAG, "onTouchEvent: +++++++++=========" + e.toString(), null);
                switch (e.getAction()) {
                    case MotionEvent.ACTION_UP:
                        break;
                }
            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
                Log.e(TAG, "onRequestDisallowInterceptTouchEvent: ++++====" + disallowIntercept, null);
            }
        });
    }

    private class HomeAdapter extends RecyclerView.Adapter<MyViewHolder> {
        private final Context context;

        public HomeAdapter(Context context) {
            this.context = context;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new MyViewHolder(View.inflate(App.getApplication(), R.layout.layout_rv_item, null));
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, final int position) {
            holder.textView.setText(items[position]);
            holder.textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    switch (position) {
                        case 0:
                            startActivity(new Intent(context, IntentHighFunctionActivity.class));
                            break;
                        case 1:
                            startActivity(new Intent(context, LifeCyclerActivity.class));
                            break;
                        case 2:
                            startActivity(new Intent(context, CustomTitleBarActivity.class));
                            break;
                        case 3:
                            startActivity(new Intent(context, DialogueActivity.class));
                            break;
                        case 4:
                            startActivity(new Intent(context, FragmentTestActivity.class));
                            break;
                        case 5:
                            startActivity(new Intent(context, BroadCastActivity.class));
                            break;
                        case 6:
                            startActivity(new Intent(context, DataDurableActivity.class));
                            break;
                        case 7:
                            startActivity(new Intent(context, MultiMediaActivity.class));
                            break;
                        case 8:
                            startActivity(new Intent(context, BaiduMapActivity.class));
                            break;
                        case 9:
                            startActivity(new Intent(context, MainActivity.class));
                            break;
                        case 10:
                            startActivity(new Intent(context, RxDataBindingTestActivity.class));
                            break;
                        case 11:
                            startActivity(new Intent(context, CustomViewTestActivity.class));
                            break;
                        case 12:
                            startActivity(new Intent(context, IPCActivity.class));
                            break;
                    }
                }
            });
        }


        @Override
        public int getItemCount() {
            return items.length;
        }
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;

        public MyViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.tv_item_home);
        }
    }
}
