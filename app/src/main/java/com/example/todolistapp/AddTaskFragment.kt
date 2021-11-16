package com.example.todolistapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.todolistapp.databinding.FragmentAddTaskBinding
import com.google.android.material.datepicker.MaterialDatePicker
import model.ToDo
import model.ToDoViewModel

class AddTaskFragment : Fragment() {
    private var _binding: FragmentAddTaskBinding? = null
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
        _binding = FragmentAddTaskBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.viewModel = sharedViewModel
        binding.lifecycleOwner = viewLifecycleOwner
        binding.addTaskFragment = this@AddTaskFragment

    }

    fun saveNewTask() {
        sharedViewModel.addTaskToList()
        findNavController().navigate(R.id.action_addTaskFragment_to_taskListFragment)

    }
}
    //
//    fun date() {
//        val builder = MaterialDatePicker.Builder.datePicker()
//        val picker = builder.build()
//        picker.show(requireFragmentManager(), picker.toString())
//        picker.addOnPositiveButtonClickListener {
//            binding.textInputEditDate.text = it
//        }
//    }


//    fun editTask(){
//
//    }
//
//    fun setDate(): String {
//        val date = binding.textInputEditDate.text.toString()
//        return date
//
//    }
//
//    fun setDescription(): String {
//        val description = binding.textInputEditDescription.text.toString()
//        return description
//    }

//    fun setTaskInformation() {
////        val info = ToDo(setDescription(), setDate(), setDate(), false)
//        sharedViewModel.addTaskToList()
//    }
