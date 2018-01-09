package com.locensate.firstlinecode;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.locensate.firstlinecode.view.CustomTitleBar;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FileSaveTestActivity extends AppCompatActivity {

    @BindView(R.id.title_bar)
    CustomTitleBar titleBar;
    @BindView(R.id.tv_file_dir)
    TextView tvFileDir;
    @BindView(R.id.et_file_content)
    EditText etFileContent;
    @BindView(R.id.btn_file_input)
    Button btnFileInput;
    @BindView(R.id.btn_file_output)
    Button btnFileOutput;
    @BindView(R.id.tv_file_output)
    TextView tvFileOutput;
    @BindView(R.id.activity_file_sava_test)
    LinearLayout activityFileSavaTest;
    private BufferedWriter writer;
    private static final String TAG = "FileSaveTestActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_sava_test);
        ButterKnife.bind(this);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) actionBar.hide();
        titleBar.setTitle("文件存储");
        titleBar.setAddIsAble(false);
    }

    @OnClick({R.id.btn_file_input, R.id.btn_file_output})
    public void onClick(View view) {
        String content = etFileContent.getText().toString();
        switch (view.getId()) {
            case R.id.btn_file_input:
                if (!TextUtils.isEmpty(content)) saveFile(content);
                etFileContent.setText("");
                break;
            case R.id.btn_file_output:
                readFile();
                break;
        }
    }

    private void readFile() {
        FileInputStream input;
        BufferedReader reader;
        StringBuilder builder;
        try {
            input = openFileInput("data.txt");
            reader = new BufferedReader(new InputStreamReader(input));
            builder = new StringBuilder();
            String line = "";
            while ((line = reader.readLine()) != null) {
                Log.e(TAG, "readFile: " + line, null);
                builder.append(line);
                Log.e(TAG, "readFile: " + line, null);
            }
            tvFileOutput.setText(builder.toString());
            tvFileDir.setText(getFilesDir() + "/data.txt");
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveFile(String content) {
        FileOutputStream output;
        BufferedWriter writer;
        try {
            output = openFileOutput("data.txt", Context.MODE_PRIVATE);
            writer = new BufferedWriter(new OutputStreamWriter(output));
            writer.write(content);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
