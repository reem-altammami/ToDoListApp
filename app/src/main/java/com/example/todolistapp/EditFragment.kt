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
        binding.viewModel = sharedViewModel
        binding.lifecycleOwner = viewLifecycleOwner
        binding.editFragment = this@EditFragment

    }
    fun updateTask() {
        sharedViewModel.updatedTaskInfo()
       findNavController().navigate(R.id.action_editFragment_to_taskListFragment)

    }
//    fun editTask(){
//
//    }
//
//    fun setTitle(): String {
//        val title = binding.textInputEditTitle.text.toString()
//        return title
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
//
//    fun setTaskInformation() {
//        val info = ToDo(setTitle(), setDescription(), setDate(), setDate(), false)
//        sharedViewModel.updatedTaskInfo(info)
//    }
    fun showConfirmDeletionDialog(){
        MaterialAlertDialogBuilder(requireContext())
            .setTitle(getString(R.string.dialog_title))
            .setMessage(getString(R.string.dialog_message)).setCancelable(false)
            .setNegativeButton(getString(R.string.confirm)){_,_ ->
                deleteTask()
            }
            .setPositiveButton(getString(R.string.cancel)){_,_->
                findNavController().navigate(R.id.action_editFragment_to_taskListFragment)
            }
            .show()
    }
    fun deleteTask() {
        sharedViewModel.removeTask()
        findNavController().navigate(R.id.action_editFragment_to_taskListFragment)
    }
}