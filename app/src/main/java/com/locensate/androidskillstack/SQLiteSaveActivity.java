package com.locensate.androidskillstack;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.locensate.androidskillstack.bean.UserInfo;
import com.locensate.androidskillstack.utils.ToastUtil;
import com.locensate.androidskillstack.view.CustomTitleBar;

import org.litepal.crud.DataSupport;
import org.litepal.tablemanager.Connector;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SQLiteSaveActivity extends AppCompatActivity {

    @BindView(R.id.title_bar)
    CustomTitleBar titleBar;
    @BindView(R.id.btn_sql_add)
    Button btnSqlAdd;
    @BindView(R.id.btn_create_sql)
    Button btnCreateSql;
    @BindView(R.id.btn_sql_delete)
    Button btnSqlDelete;
    @BindView(R.id.btn_sql_update)
    Button btnSqlUpdate;
    @BindView(R.id.btn_sql_query)
    Button btnSqlQuery;
    @BindView(R.id.tv_sql_des)
    TextView tvSqlDes;
    @BindView(R.id.activity_sqlite_save)
    LinearLayout activitySqliteSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite_save);
        ButterKnife.bind(this);
        ActionBar bar = getSupportActionBar();
        if (bar != null) bar.hide();
        titleBar.setAddIsAble(false);
        titleBar.setTitle("SQLite数据库操作");
    }

    @OnClick({R.id.btn_create_sql, R.id.btn_sql_add, R.id.btn_sql_delete, R.id.btn_sql_update, R.id.btn_sql_query})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_create_sql:
                SQLiteDatabase database = Connector.getDatabase();
                if (database != null) ToastUtil.showToast("数据库创建成功");
                tvSqlDes.setText("本操作使用LitePal操作数据库，使用简单，方便，不用考虑逻辑的问题和那些繁多的SQLite语句，只需要添加litepal的依赖，简单配置即可；具体操作如下：\r\n 1、" +
                        "在app目录下的build.gradle文件中添加依赖：compile 'org.litepal.android:core:1.5.0' 具体版本号到开源项目中查看；2、在main目录下新建assets文件目录，本在" +
                        "目录下创建litepal.xml文件，文件内容很简单，参照项目Readme；3、配置Application，在AndroidManifest文件中配置Application为LitePal的Application，自己的项目Application可以继承" +
                        "LitePal的application，止此，配置完成，是否觉得so easy？\r\n 接下来就是创建数据库：首先我们创建一个bean类继承DataSupport，包括你想要创建的表单的所有字段，在litepal.xml文件中的list标签下，添加mapping标签" +
                        "，将bean类的全类名配置给mapping的class；应该不晕吧？好了，接下来在任何地方只要调用Connector.getDatabase(),Okay！数据库已经创建完成了！初始化的表单也创建了！\r\n 数据库创建很简单，那数据库升级是否简单呢？tell you：更简单！" +
                        "1、如果你想添加一个表单，只需创建一个bean类，将其配置到litepal.xml中，将version值+1，然后呢？没有然后了，自动升级了！\r\n 2、那如果我改动了原先表单中的数据呢？也只需将version+1，震撼吧？之前我们升级数据库要考虑的东西，现在是不是都不用考虑了？后面还有更震撼的，期待吧");
                break;
            case R.id.btn_sql_add:
                tvSqlDes.setText("只需new一个bean对象，bean.save()方法，就完成了添加数据");
                UserInfo userInfo = new UserInfo("admin", "admin", false, 17, String.valueOf(SystemClock.currentThreadTimeMillis()), "test");
                userInfo.save();
                break;
            case R.id.btn_sql_delete:
                tvSqlDes.setText("删除操作通过DataSupport.delete*()方法操作不同的删除");
                UserInfo info = DataSupport.find(UserInfo.class, 124);
                DataSupport.findAll(UserInfo.class);
                break;
            case R.id.btn_sql_update:
            case R.id.btn_sql_query:
                tvSqlDes.setText("改查操作通过DataSupport调用不同的方法即可");
                break;
        }
    }
}
