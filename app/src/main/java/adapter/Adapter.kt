package adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.todolistapp.R
import com.example.todolistapp.TaskListFragmentDirections
import model.ToDo

class ToDoAdapter (val context: Context, val dataSet:List<ToDo>):RecyclerView.Adapter<ToDoAdapter.ToDoViewHolder>() {

    class ToDoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val toDoTitle : TextView = view.findViewById(R.id.task_title)
        val toDoDate: TextView = view.findViewById(R.id.task_date)
        val card : CardView = view.findViewById(R.id.item_card)
        val edit :ImageView = view.findViewById(R.id.edit_icon)
        val delete :ImageView = view.findViewById(R.id.delete_icon)
        val done :ImageView = view.findViewById(R.id.icon_done)



    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoViewHolder {
        val adapter= LayoutInflater.from(parent.context).inflate(R.layout.item_todo_list,parent,false)
        return ToDoViewHolder(adapter)
    }

    override fun onBindViewHolder(holder: ToDoViewHolder, position: Int) {
        val item = dataSet[position]
        holder.toDoTitle.text = item.title
        holder.toDoDate.text = item.dueDate
        if (item.isComplete){
            holder.done.setImageResource(R.drawable.ic_check)
        }
        holder.card.setOnClickListener{
            val action = TaskListFragmentDirections.actionTaskListFragmentToShowTaskFragment(position)
            holder.card.findNavController().navigate(action)

        }

        holder.edit.setOnClickListener {
            val action = TaskListFragmentDirections.actionTaskListFragmentToEditFragment(position)
            holder.edit.findNavController().navigate(action)
        }
        holder.delete.setOnClickListener {
            val action = TaskListFragmentDirections.actionTaskListFragmentToEditFragment(position)
            holder.delete.findNavController().navigate(action)
        }
    }

    override fun getItemCount(): Int = dataSet.size
}