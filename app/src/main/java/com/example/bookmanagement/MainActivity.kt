package com.example.bookmanagement
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity(),Contract.bookView {

    lateinit var presenter : Contract.Presenter
    lateinit var etId: EditText
    lateinit var etName : EditText
    lateinit var etIsbn : EditText
    lateinit var etAuthor : EditText
    lateinit var etYear : EditText
    lateinit var rv: RecyclerView
    lateinit var adapter: BookAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()
        presenter = PresenterImpl()
        presenter.init(this)
    }

    private fun initViews() {
        etId = findViewById(R.id.name)
        etName = findViewById(R.id.name)
        etIsbn = findViewById(R.id.isbn)
        etAuthor = findViewById(R.id.author)
        etYear = findViewById(R.id.year)
        rv = findViewById(R.id.rv)
        adapter = BookAdapter(this,null)
        rv.adapter = adapter
    }

    override fun updateBooks(books : ArrayList<Book>) {
        adapter.updateList(books)
    }

    override fun requestAddBookSuccess() {
        Toast.makeText(this,"AddBookSuccess",Toast.LENGTH_SHORT).show()
    }

    override fun requestDeleteBookSuccess() {
        Toast.makeText(this,"DeleteBookSuccess",Toast.LENGTH_SHORT).show()
    }

    override fun requestUpdateBookSuccess() {
        Toast.makeText(this,"UpdateBookSuccess",Toast.LENGTH_SHORT).show()
    }

    override fun requestSearchBooksSuccess() {
    }

    override fun requestFailed() {
        Toast.makeText(this,"failed!",Toast.LENGTH_SHORT).show()
    }

    fun add(view: View) {
        presenter.addBook(
            etName.text.toString(),
            etIsbn.text.toString(),
            etAuthor.text.toString(),
            etYear.text.toString())
    }

    fun search(view: View){
        presenter.searchBooks(
            if (etId.text.isEmpty()) -1 else etId.text.toString().toInt(),
            etName.text.toString(),
            etIsbn.text.toString(),
            etAuthor.text.toString(),
            etYear.text.toString())
    }

    fun update(view: View) {
        presenter.updateBook(if (etId.text.isEmpty()) -1 else etId.text.toString().toInt(),
            etName.text.toString(),
            etIsbn.text.toString(),
            etAuthor.text.toString(),
            etYear.text.toString())
    }
    fun delete(view: View) {
        presenter.deleteBook(if (etId.text.isEmpty()) -1 else etId.text.toString().toInt())
    }

}