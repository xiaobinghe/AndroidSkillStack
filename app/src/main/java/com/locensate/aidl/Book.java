package com.locensate.aidl;

import android.os.Parcel;
import android.os.Parcelable;


/**
 * Book
 *
 * @author xiaobinghe
 */

public class Book implements Parcelable {

    private int id;
    private String bookName;

    public Book(int id, String bookName) {
        this.id = id;
        this.bookName = bookName;
    }

    protected Book(Parcel in) {
        id = in.readInt();
        bookName = in.readString();
    }

    public static final Creator<Book> CREATOR = new Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel in) {
            return new Book(in);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }


    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(bookName);
    }
}
