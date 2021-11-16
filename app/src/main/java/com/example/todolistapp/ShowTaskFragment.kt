package com.example.todolistapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.todolistapp.databinding.FragmentShowTaskBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import model.ToDoViewModel

class ShowTaskFragment : Fragment() {


    private var _binding: FragmentShowTaskBinding? = null
    private val binding get() = _binding!!
    private val sharedViewModel: ToDoViewModel by activityViewModels()
    var taskIndex: Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            taskIndex = it.getInt(Argument.INDEX)

        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentShowTaskBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        val item = allTask[taskIndex]
//        binding.textTitle.text = item.title
        sharedViewModel.currentTaskPosition.value = taskIndex
        binding.viewModel = sharedViewModel
        binding.lifecycleOwner = viewLifecycleOwner
        binding.showTaskFragment = this@ShowTaskFragment
        sharedViewModel.displayInformation()

    }
    fun editTask(){
        findNavController().navigate(R.id.action_showTaskFragment_to_editFragment)
        sharedViewModel.displayInformation()

    }
    fun deleteTask() {
        sharedViewModel.removeTask()
//        allTask.removeAt(taskIndex)
        findNavController().navigate(R.id.action_showTaskFragment_to_taskListFragment)
    }
    //Dialog
    fun showConfirmDeletionDialog(){
        MaterialAlertDialogBuilder(requireContext())
            .setTitle(getString(R.string.dialog_title))
            .setMessage(getString(R.string.dialog_message)).setCancelable(false)
            .setNegativeButton(getString(R.string.confirm)){_,_ ->
                deleteTask()
            }
            .setPositiveButton(getString(R.string.cancel)){_,_->
findNavController().navigate(R.id.action_showTaskFragment_to_taskListFragment)
            }
            .show()
    }


    fun checkComplete() {

//        if (binding.complete.isChecked)
//        sharedViewModel.maketComplete(true)
//        else
//            sharedViewModel.maketComplete(false)
//    }
    }
}