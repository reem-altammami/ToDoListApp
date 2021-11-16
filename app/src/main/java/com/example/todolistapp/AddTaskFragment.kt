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
import java.text.SimpleDateFormat
import java.util.*

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
    fun dateDialog(){
        val builder = MaterialDatePicker.Builder.datePicker()
        val picker = builder.build()
        picker.show(requireFragmentManager(), picker.toString())

        picker.addOnNegativeButtonClickListener {
        }
        picker.addOnPositiveButtonClickListener {
            sharedViewModel.formatDate(it)
        }

    }
//    fun formatDate(date : Long){
//        val formatter = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
//        val selectedDate = date
//        val dauDate =formatter.format(selectedDate)
//        binding.o.text = isDateNotPast(dauDate).toString()
//        val calendar = Calendar.getInstance()
//        val x = formatter.format(calendar.time)
//        binding.t.text=dauDate
//        binding.c.text = x




//    }
//    fun isDateNotPast(taskDate: String) : Boolean {
//        try {
//            val todayDate = SimpleDateFormat("yyyy-MM-dd").parse(taskDate)
//
//            return !todayDate.before(Date())
//        } catch(ignored: java.text.ParseException) {
//            return false
//        }
//    }
}



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
