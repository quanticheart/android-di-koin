package quanticheart.com.kodein.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_list.view.*
import quanticheart.com.kodein.R

class RecyclerAdapter(context: Context, recyclerView: RecyclerView) : RecyclerView.Adapter<RecyclerAdapter.Holder>() {

    init {
        recyclerView.apply {
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            adapter = this@RecyclerAdapter
        }
    }

    private var list = ArrayList<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder = Holder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)
    )

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bindData(list[position])
    }

    class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindData(textFromList: String) {
            itemView.text.text = textFromList
        }
    }

    // ** Utils Adapter
    //==============================================================================================

    fun addTask(text: String) {
        list.add(text)
        notifyDataSetChanged()
    }

    fun addList(listText: ArrayList<String>) {
        list.clear()
        list.addAll(listText)
        notifyDataSetChanged()
    }

    fun clearList() {
        list.clear()
        notifyDataSetChanged()
    }

}