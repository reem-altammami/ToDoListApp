package com.example.todolistapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.todolistapp.databinding.FragmentAddTaskBinding
import com.example.todolistapp.databinding.FragmentEditBinding
import model.ToDo
import model.ToDoViewModel


class EditFragment : Fragment() {
    private var _binding: FragmentEditBinding? = null
    private val binding get() = _binding!!
    private val sharedViewModel: ToDoViewModel by activityViewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentEditBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.viewModel = sharedViewModel
        binding.lifecycleOwner = viewLifecycleOwner
        binding.editFragment = this@EditFragment

    }
    fun updateTask() {
        setTaskInformation()
       findNavController().navigate(R.id.action_addTaskFragment_to_taskListFragment)

    }
    fun editTask(){

    }

    fun setTitle(): String {
        val title = binding.textInputEditTitle.text.toString()
        return title

    }

    fun setDate(): String {
        val date = binding.textInputEditDate.text.toString()
        return date

    }

    fun setDescription(): String {
        val description = binding.textInputEditDescription.text.toString()
        return description
    }

    fun setTaskInformation() {
        val info = ToDo(setTitle(), setDescription(), setDate(), setDate(), false)
        sharedViewModel.addTaskToList(info)
    }
}