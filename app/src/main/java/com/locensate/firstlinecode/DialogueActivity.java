package com.locensate.firstlinecode;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.locensate.firstlinecode.bean.ChatBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DialogueActivity extends AppCompatActivity {

    @BindView(R.id.rv_record_chart)
    RecyclerView rvRecordChart;
    @BindView(R.id.et_input)
    EditText etInput;
    @BindView(R.id.btt_send)
    Button bttSend;
    @BindView(R.id.activity_dialogue)
    LinearLayout activityDialogue;
    @BindView(R.id.btt_receive)
    Button bttReceive;
    private DialogueAdapter adapter;
    private List<ChatBean> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialogue);
        ButterKnife.bind(this);
        init();
    }

    private void init() {


        rvRecordChart.setLayoutManager(new LinearLayoutManager(this));
        data = getData();
        adapter = new DialogueAdapter(this, data);
        rvRecordChart.setAdapter(adapter);
    }

    public List<ChatBean> getData() {
        List<ChatBean> chatBeans = new ArrayList<>();
        chatBeans.add(new ChatBean("在吗？", ChatBean.RECEVIED));
        chatBeans.add(new ChatBean("who are you ?", ChatBean.SENDED));
        return chatBeans;
    }

    @OnClick({R.id.btt_receive, R.id.btt_send})
    public void onClick(View view) {
        String input = etInput.getText().toString();
        if (!"".equals(input)) {
            switch (view.getId()) {
                case R.id.btt_receive:
                    data.add(new ChatBean(etInput.getText().toString(), ChatBean.RECEVIED));
                    adapter.notifyItemInserted(data.size() - 1);
                    rvRecordChart.scrollToPosition(data.size() - 1);
                    break;
                case R.id.btt_send:
                    data.add(new ChatBean(etInput.getText().toString(), ChatBean.SENDED));
                    adapter.notifyItemInserted(data.size() - 1);
                    rvRecordChart.scrollToPosition(data.size() - 1);
                    break;
            }
            etInput.setText("");
        }

    }


    private class DialogueAdapter extends RecyclerView.Adapter<DialogueViewHolder> {
        private final List<ChatBean> data;
        private final Context context;

        public DialogueAdapter(Context context, List<ChatBean> data) {
            this.context = context;
            this.data = data;
        }

        @Override
        public DialogueViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            DialogueViewHolder holder = new DialogueViewHolder(LayoutInflater.from(context).inflate(R.layout.item_chat_record, parent, false));
            return holder;
        }

        @Override
        public void onBindViewHolder(DialogueViewHolder holder, int position) {
            if (data.get(position).getType() == ChatBean.RECEVIED) {
                holder.llChatLeft.setVisibility(View.VISIBLE);
                holder.tvChatLeft.setText(data.get(position).getContent());
                holder.llChatRight.setVisibility(View.GONE);
            } else {
                holder.llChatLeft.setVisibility(View.GONE);
                holder.tvChatRight.setText(data.get(position).getContent());
                holder.llChatRight.setVisibility(View.VISIBLE);
            }
            holder.llChatLeft.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    view.setVisibility(View.GONE);
                }
            });
            holder.llChatRight.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    view.setVisibility(View.GONE);
                }
            });
        }

        @Override
        public int getItemCount() {
            return data.size();
        }
    }

    private class DialogueViewHolder extends RecyclerView.ViewHolder {

        public LinearLayout llChatLeft;
        public LinearLayout llChatRight;
        public TextView tvChatLeft;
        public TextView tvChatRight;

        public DialogueViewHolder(View itemView) {
            super(itemView);
            llChatLeft = (LinearLayout) itemView.findViewById(R.id.ll_chat_left);
            tvChatLeft = (TextView) itemView.findViewById(R.id.tv_chat_left);
            llChatRight = (LinearLayout) itemView.findViewById(R.id.ll_chat_right);
            tvChatRight = (TextView) itemView.findViewById(R.id.tv_chat_right);
        }
    }
}
