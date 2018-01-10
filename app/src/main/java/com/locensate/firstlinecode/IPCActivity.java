package com.locensate.firstlinecode;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.locensate.firstlinecode.view.CustomTitleBar;

import butterknife.BindView;

public class IPCActivity extends BaseActivity {
    @BindView(R.id.title_bar)
    CustomTitleBar mTitleBar;
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.activity_ipc)
    LinearLayout mActivityIpc;
    private String[] items = {"Serializable的序列化与反序列化", "Messenger test", "AIDL test"};

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        mTitleBar.setTitle("IPC进程间通信");
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(new IPCRVAdapter(this));

    }

    @Override
    public int getLayout() {
        return R.layout.activity_ipc;
    }

    private class IPCRVAdapter extends RecyclerView.Adapter<MyViewHolder> {
        private final Context context;

        public IPCRVAdapter(Context context) {
            this.context = context;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.layout_rv_item, null));
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, final int position) {
            holder.textView.setText(items[position]);
            holder.textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    switch (position) {
                        case 0:
                            break;
                        case 1:
                            startActivity(new Intent(context,MessengerTestActivity.class));
                            break;
                        case 2:
                            startActivity(new Intent(context,AIDLTestActivity.class));
                            break;
                        case 3:
                            break;
                        case 4:
                            break;
                        default:
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
        TextView textView;

        public MyViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.tv_item_home);
        }
    }
}
