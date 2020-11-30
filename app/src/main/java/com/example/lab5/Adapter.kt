package com.example.lab5

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.list_item.view.*


class Adapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var taskList = emptyList<Task>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return ContactViewHolder(v)
    }

    override fun getItemCount(): Int {
        return taskList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder as ContactViewHolder
        val currentItem = taskList[position]
        holder.itemView.titleTextView.text = currentItem.title
        holder.itemView.statusTextView.text = currentItem.completed.toString()

        holder.itemView.toDoItemLayout.setOnClickListener {
            val action = ListFragmentDirections.navigateToDetail(position)
            Navigation.findNavController(it).navigate(action)
        }
    }
    fun setData(task: List<Task>){

        this.taskList = task
        notifyDataSetChanged()
    }
    inner class ContactViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    }
}