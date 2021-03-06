package com.example.todolistapp

import adapter.ToDoAdapter
import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.PopupMenu
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.todolistapp.databinding.FragmentTaskListBinding
import data.DataSource
import model.ToDo
import model.ToDoViewModel
import java.text.SimpleDateFormat


class TaskListFragment : Fragment() {
    private val sharedViewModel: ToDoViewModel by activityViewModels()
    private var _binding: FragmentTaskListBinding? = null
    private val binding get() = _binding!!
    private lateinit var recyclerView: RecyclerView
    lateinit var data: List<ToDo>
    lateinit var sortedList: List<ToDo>

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
        binding.sortIcon.setOnClickListener { showSortPopupMenu(binding.sortIcon) }
        binding.filterIcon.setOnClickListener { showFilterPopupMenu(binding.filterIcon) }

        sortedList = data

        binding.apply {
            binding.viewModel = sharedViewModel
            binding.lifecycleOwner = viewLifecycleOwner
            binding.listTaskFragment = this@TaskListFragment
        }

        recyclerView = binding.recyclerView
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = ToDoAdapter(this.requireContext(), sortedList)


    }

    // Add new Task to List
    fun addNewTask() {
        findNavController().navigate(R.id.action_taskListFragment_to_addTaskFragment)
        sharedViewModel.getEmptyFields()
    }

    // create popupMenu for Sort
    @SuppressLint("SetTextI18n")
    private fun showSortPopupMenu(view: View) {
        val popup = PopupMenu(this.requireContext(), view)
        popup.inflate(R.menu.sort_menu)

        popup.setOnMenuItemClickListener(PopupMenu.OnMenuItemClickListener { item: MenuItem? ->

            when (item!!.itemId) {

                R.id.sort_alpha -> {
                    sortedList = data.sortedBy { it.title.toLowerCase() }
                    recyclerView.adapter = ToDoAdapter(this.requireContext(), sortedList)

                }
                R.id.sort_due -> {
                    sortedList = data.sortedBy { SimpleDateFormat("yyyy-MM-dd").parse(it.dueDate) }
                    recyclerView.adapter = ToDoAdapter(this.requireContext(), sortedList)
                }
                R.id.sort_cration -> {
                    sortedList =
                        data.sortedBy { SimpleDateFormat("yyyy-MM-dd").parse(it.creationDate!!) }
                    recyclerView.adapter = ToDoAdapter(this.requireContext(), sortedList)
                }
                R.id.un_sorted -> {
                    sortedList = data
                    recyclerView.adapter = ToDoAdapter(this.requireContext(), sortedList)
                }

            }

            true
        })

        popup.show()
    }
// create popupMenu for Filter

    private fun showFilterPopupMenu(view: View) {
        val popup = PopupMenu(this.requireContext(), view)
        popup.inflate(R.menu.filter_menu)

        popup.setOnMenuItemClickListener(PopupMenu.OnMenuItemClickListener { item: MenuItem? ->

            when (item!!.itemId) {
                R.id.sort_iscomplate -> {
                    sortedList = data.filter { it -> it.isComplete == true }
                    recyclerView.adapter = ToDoAdapter(this.requireContext(), sortedList)
                }

                R.id.is_coming -> {
                    sortedList = data.filter { it -> it.isPast == false }
                    recyclerView.adapter = ToDoAdapter(this.requireContext(), sortedList)
                }
                R.id.sort_isPast -> {
                    sortedList = data.filter { it -> it.isPast == true }
                    recyclerView.adapter = ToDoAdapter(this.requireContext(), sortedList)
                }

                R.id.un_sorted -> {
                    sortedList = data
                    recyclerView.adapter = ToDoAdapter(this.requireContext(), sortedList)
                }

            }

            true
        })

        popup.show()
    }


}


