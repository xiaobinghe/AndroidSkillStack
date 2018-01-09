package com.locensate.firstlinecode;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.locensate.firstlinecode.service.AIDLTestService;
import com.locensate.firstlinecode.utils.ToastUtil;

import java.util.List;

import butterknife.BindView;

/**
 * AIDL测试界面
 *
 * @author xiaobinghe
 */
public class AIDLTestActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.btt_add_book)
    Button mBttAddBook;
    @BindView(R.id.et_book_id)
    EditText mEtBookId;
    @BindView(R.id.et_book_name)
    EditText mEtBookName;
    @BindView(R.id.btt_get_books)
    Button mBttGetBooks;
    @BindView(R.id.tv_books_names)
    TextView mTvBooksNames;
    @BindView(R.id.activity_aidltest)
    LinearLayout mActivityAidltest;

    private List<Book> mBooks;
    private IBookManager mAsInterface;
    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mAsInterface = IBookManager.Stub.asInterface(service);
            try {
                mAsInterface.addBook(new Book(5, "诗经"));
                mBooks = mAsInterface.getBookList();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            ToastUtil.showToast("连接服务失败");
        }
    };

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        bindService(new Intent(this, AIDLTestService.class), mConnection, Context.BIND_AUTO_CREATE);
        mBttAddBook.setOnClickListener(this);
        mBttGetBooks.setOnClickListener(this);
    }

    @Override
    public int getLayout() {
        return R.layout.activity_aidltest;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btt_add_book:
                int asInt = Integer.parseInt(mEtBookId.getText().toString());
                if (TextUtils.isEmpty(mEtBookId.getText())) {
                    ToastUtil.showToast("请输入整数型ID");
                    return;
                }
                try {
                    mAsInterface.addBook(new Book(asInt, mEtBookName.getText().toString()));
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.btt_get_books:
                try {
                    List<Book> bookList = mAsInterface.getBookList();
                    StringBuilder builder = new StringBuilder("Books:");
                    for (Book book : bookList) {
                        builder.append("bookId=").append(book.getId()).append("-bookName=").append(book.getBookName()).append(";");
                    }
                    mTvBooksNames.setText(builder.toString());

                } catch (RemoteException e) {
                    e.printStackTrace();
                }

                break;
            default:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        unbindService(mConnection);
        super.onDestroy();
    }
}
