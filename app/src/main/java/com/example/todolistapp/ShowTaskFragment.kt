package com.example.todolistapp

import android.graphics.Color
import android.os.Bundle
import android.util.Log
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

// receive Argument from from ListFragment based on index of task in DatasetList to access task details
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
        binding.apply {
            viewModel = sharedViewModel
            lifecycleOwner = viewLifecycleOwner
            showTaskFragment = this@ShowTaskFragment
        }
        sharedViewModel.currentTaskPosition.value = taskIndex

        showIfComplete()
        showIsPast()
        sharedViewModel.displayInformation()
    }


// when user click on the EDIT ICON, navigate to edit fragment and call function to display his task details
    fun editTask() {
    val action = ShowTaskFragmentDirections.actionShowTaskFragmentToEditFragment(taskIndex)
    findNavController().navigate(action)
        sharedViewModel.displayInformation()

    }
// delete task from List
    fun deleteTask() {
        sharedViewModel.removeTask()
        findNavController().navigate(R.id.action_showTaskFragment_to_taskListFragment)
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
                findNavController().navigate(R.id.action_showTaskFragment_to_taskListFragment)
            }
            .show()
    }
// show tag if task completed
    fun showIfComplete() {
        sharedViewModel.isComplete.observe(viewLifecycleOwner, {
            if (it) {
                binding.iconDone.setImageResource(R.drawable.ic_check)
            }
        })
    }
// show tag if task is past or coming
    fun showIsPast() {
        sharedViewModel.isPast.observe(viewLifecycleOwner, {

            if (it) {
                binding.pastComing.setTextColor(Color.parseColor("#E6392D"))
                binding.pastComing.text = getString(R.string.past)
            }
        })
    }

}