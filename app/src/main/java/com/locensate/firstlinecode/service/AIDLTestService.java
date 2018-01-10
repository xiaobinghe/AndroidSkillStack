package com.locensate.firstlinecode.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;

import com.locensate.aidl.Book;
import com.locensate.aidl.IBookManager;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;


/**
 * AIDL服务
 *
 * @author xiaobinghe
 */

public class AIDLTestService extends Service {


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
        mBooks.add(new Book(2, "水浒传"));
        mBooks.add(new Book(3, "红楼梦"));
        mBooks.add(new Book(4, "西游记"));
        mBooks.add(new Book(1, "三国演义"));
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }
}
