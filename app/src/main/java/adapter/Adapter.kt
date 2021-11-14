package adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.todolistapp.R
import model.ToDo

class ToDoAdapter (val context: Context, val dataSet:List<ToDo>):RecyclerView.Adapter<ToDoAdapter.ToDoViewHolder>() {
    class ToDoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val toDoTitle : TextView = view.findViewById(R.id.task_title)
        val toDoDate: TextView = view.findViewById(R.id.task_date)
        val completeImage :ImageView = view.findViewById(R.id.completed)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoViewHolder {
        val adapter= LayoutInflater.from(parent.context).inflate(R.layout.item_todo_list,parent,false)
    return ToDoViewHolder(adapter)
    }

    override fun onBindViewHolder(holder: ToDoViewHolder, position: Int) {
        val item = dataSet[position]
        holder.toDoTitle.text = item.title
        holder.toDoDate.text= item.date
    }

    override fun getItemCount(): Int = dataSet.size
}