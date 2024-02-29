package com.example.bookmanagement

import com.example.bookmanagement.retrofit.ApiService
import com.example.bookmanagement.retrofit.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import kotlin.random.Random

class PresenterImpl : Contract.Presenter {

    lateinit var view: Contract.bookView

    lateinit var retrofit: Retrofit
    lateinit var api: ApiService
    var BookList = arrayListOf<Book>()

    override fun init(view: Contract.bookView) {
        this.view = view
        api = RetrofitClient.getInstance()
    }

    override fun addBook(name: String?, isbn: String?, author: String?, year: String?) {
        api.addBook(name, isbn, author, year).enqueue(object : Callback<Any> {
            override fun onResponse(call: Call<Any>, response: Response<Any>) {
                view.requestAddBookSuccess()
            }

            override fun onFailure(call: Call<Any>, t: Throwable) {
                view.requestFailed()
            }
        })
        //MOCK DATA
        BookList.add(Book(Random.nextInt(20).toString(), name.toString(), isbn.toString(), author.toString(), year.toString()))
        view.updateBooks(BookList)
        view.requestAddBookSuccess()
    }

    override fun searchBooks(
        id: Int?,
        name: String?,
        isbn: String?,
        author: String?,
        year: String?
    ) {

        api.searchBooks(id, name, isbn, author, year).enqueue(object : Callback<ArrayList<Book>> {
            override fun onResponse(
                call: Call<ArrayList<Book>>,
                response: Response<ArrayList<Book>>
            ) {
                view.requestSearchBooksSuccess()
            }

            override fun onFailure(call: Call<ArrayList<Book>>, t: Throwable) {
                view.requestFailed()

            }
        })
    }

    override fun updateBook(
        id: Int?,
        name: String?,
        isbn: String?,
        author: String?,
        year: String?
    ) {
        api.updateBook(id, name, isbn, author, year).enqueue(object : Callback<Any> {
            override fun onResponse(call: Call<Any>, response: Response<Any>) {
                view.requestUpdateBookSuccess()
            }

            override fun onFailure(call: Call<Any>, t: Throwable) {
                view.requestFailed()
            }
        })

    }

    override fun deleteBook(id: Int?) {
        api.deleteBook(id).enqueue(object : Callback<Any> {
            override fun onResponse(call: Call<Any>, response: Response<Any>) {
                view.requestDeleteBookSuccess()
            }

            override fun onFailure(call: Call<Any>, t: Throwable) {
                view.requestFailed()
            }
        })
        //MOCK DATA
        BookList.forEach { book ->
            if (book.id.equals(id)) {
                BookList.remove(book)
                return@forEach
            }
        }
        view.updateBooks(BookList)
    }

}