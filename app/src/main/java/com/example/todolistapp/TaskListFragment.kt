package com.example.todolistapp

import adapter.ToDoAdapter
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.todolistapp.databinding.FragmentTaskListBinding
import data.DataSource
import model.ToDo
import model.ToDoViewModel


class TaskListFragment : Fragment() {
    private val sharedViewModel: ToDoViewModel by activityViewModels()
    private var _binding: FragmentTaskListBinding? = null
    private val binding get() = _binding!!
    private lateinit var recyclerView: RecyclerView
    lateinit var data: List<ToDo>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        data = DataSource().loadData()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentTaskListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        sharedViewModel.add()
        recyclerView = binding.recyclerView
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = ToDoAdapter(this.requireContext(), data)
        binding.viewModel = sharedViewModel
        binding.lifecycleOwner = viewLifecycleOwner
        binding.listTaskFragment = this@TaskListFragment



    }
//fun deleteTask(){
//    allTask.removeAt()
//}

    fun addNewTask(){
    findNavController().navigate(R.id.action_taskListFragment_to_addTaskFragment)
    sharedViewModel.getEmptyFields()
    }

}


