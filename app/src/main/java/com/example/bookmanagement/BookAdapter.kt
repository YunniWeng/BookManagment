package com.example.bookmanagement
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class BookAdapter(val context: Context, var books : ArrayList<Book>?) : RecyclerView.Adapter<BookAdapter.ItemHolder>() {

    class ItemHolder(private val view: View) : RecyclerView.ViewHolder(view){
        val tvName = view.findViewById<TextView>(R.id.name)
        val tvIsbn = view.findViewById<TextView>(R.id.isbn)
        val tvAuthor = view.findViewById<TextView>(R.id.author)
        val tvYear = view.findViewById<TextView>(R.id.year)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
       val inflater = LayoutInflater.from(context).inflate(R.layout.item_rv,parent,false)
        return ItemHolder(inflater)
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        books?.apply {
            val item = books!![position]
            holder.tvName.text = item.name
            holder.tvIsbn.text = item.isbn
            holder.tvAuthor.text = item.author
            holder.tvYear.text = item.year
        }
    }

    override fun getItemCount(): Int {
        if(books == null) return 0
        return books!!.size
    }

    fun updateList(books: ArrayList<Book>){
        this.books?.clear()
        this.books = books
        notifyDataSetChanged()
    }
}