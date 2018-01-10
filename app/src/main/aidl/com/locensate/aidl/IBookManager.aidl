// IBookManager.aidl
package com.locensate.aidl;
import com.locensate.aidl.Book;
// Declare any non-default types here with import statements

interface IBookManager {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    int getBookId(in Book book);
    boolean addBook(in Book book);
    List<Book> getBookList();
}
