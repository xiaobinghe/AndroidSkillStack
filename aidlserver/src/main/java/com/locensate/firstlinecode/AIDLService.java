package com.locensate.firstlinecode;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
/**
 *-------------------------------------
 * <p>
 * 项目名称： ${PROJECT_NAME}
 * <p>
 * 版权：locensate.com 版权所有 2017
 * <p>
 * 公司主页：http://www.locensate.com/
 * <p>
 * 描述：
 * 时间： ${DATE} ${TIME}
 * <p>
 * 修改历史：
 * <p>
 * 修改时间：
 * <p>
 * 修改描述：
 * <p>
 * @author：xiaobinghe
 *-------------------------------------
 */

public class AIDLService extends Service {
    private CopyOnWriteArrayList<Book> mBooks = new CopyOnWriteArrayList<>();


    private Binder mBinder = new IBookManager.Stub() {


        @Override
        public int getBookId(Book book) throws RemoteException {
            return book.getId();
        }

        @Override
        public boolean addBook(Book book) throws RemoteException {
            return mBooks.add(book);
        }

        @Override
        public List<Book> getBookList() throws RemoteException {
            return mBooks;
        }
    };


    @Override
    public void onCreate() {
        super.onCreate();
        mBooks.add(new Book(1, "Android 开发与艺术探索"));
        mBooks.add(new Book(2, "IOS 入门"));
        mBooks.add(new Book(3, "JavaScript 从入门到精通"));
    }


    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }
}
