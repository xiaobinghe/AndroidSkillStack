// IBookManager.aidl
package com.locensate.firstlinecode;
import com.locensate.firstlinecode.Book;
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
