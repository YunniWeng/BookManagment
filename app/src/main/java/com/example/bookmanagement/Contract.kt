package com.example.bookmanagement
interface Contract {

    interface bookView{

       fun updateBooks(books: ArrayList<Book>)

       fun requestAddBookSuccess()
        fun requestDeleteBookSuccess()
        fun requestUpdateBookSuccess()
        fun requestSearchBooksSuccess()

       fun requestFailed()
    }

    interface Presenter {
        fun init(view: bookView)

        fun addBook(name: String?,
                    isbn: String?,
                    author: String?,
                    year: String?)

        fun searchBooks(id: Int?,
                       name: String?,
                       des: String?,
                       author: String?,
                       year: String?)

        fun updateBook(id: Int?,
                       name: String?,
                       isbn: String?,
                       author: String?,
                       year: String?)

        fun deleteBook(id: Int?)
    }
}