package com.example.todolistapp

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.todolistapp.databinding.FragmentEditBinding
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.dialog.MaterialAlertDialogBuilder
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
        binding.apply {
            viewModel = sharedViewModel
            lifecycleOwner = viewLifecycleOwner
            editFragment = this@EditFragment
        }

        var pos = 0
        arguments?.let {
            pos = it?.getInt("itemPos")
        }
        sharedViewModel.displayInformation()
//        sharedViewModel.displayInformation(pos)
        showIfComplete()
        showIsPast()



    }
// if date of task past change text
    fun showIsPast() {
        sharedViewModel.isPast.observe(viewLifecycleOwner, {

            if (it) {
                binding.pastComing.setTextColor(Color.parseColor("#E6392D"))
                binding.pastComing.text = getString(R.string.past)
            }
        })
    }

    private fun showIfComplete() {
        sharedViewModel.isComplete.observe(viewLifecycleOwner, {
            if (it) {
                binding.iconDone.setImageResource(R.drawable.ic_check)
            }
        })
    }

// after user insert his change, this function will call to update values in dataSet
    fun updateTask() {
        sharedViewModel.updatedTaskInfo()
        findNavController().navigate(R.id.action_editFragment_to_taskListFragment)

    }

fun backWithOutUpdate(){
    findNavController().navigate(R.id.action_editFragment_to_taskListFragment)
}

    //Dialog to confirm delete task
    fun showConfirmDeletionDialog() {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle(getString(R.string.dialog_title))
            .setMessage(getString(R.string.dialog_message)).setCancelable(false)
            .setNegativeButton(getString(R.string.confirm)) { _, _ ->
                deleteTask()
            }
            .setPositiveButton(getString(R.string.cancel)) { _, _ ->
                findNavController().navigate(R.id.action_editFragment_to_taskListFragment)
            }
            .show()
    }

// after user click delete, the Task will delete from List then user navigate to TaskListFragment
    fun deleteTask() {
        sharedViewModel.removeTask()
        findNavController().navigate(R.id.action_editFragment_to_taskListFragment)
    }

// show date Dialog to add due date for Task, this value is type of  Long so we send it to another function
    fun dateDialog() {
        val builder = MaterialDatePicker.Builder.datePicker()
        val picker = builder.build()
        picker.show(requireFragmentManager(), picker.toString())

        picker.addOnNegativeButtonClickListener {
        }
        picker.addOnPositiveButtonClickListener {
            sharedViewModel.formatDate(it)
        }
    }

}
