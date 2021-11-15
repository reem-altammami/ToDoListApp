package com.example.todolistapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.todolistapp.databinding.FragmentShowTaskBinding
import model.ToDoViewModel

class ShowTaskFragment : Fragment() {
    companion object {
        const val INDEX = "itemPosition"
    }

    private var _binding: FragmentShowTaskBinding? = null
    private val binding get() = _binding!!
    private val sharedViewModel: ToDoViewModel by activityViewModels()
    var taskIndex: Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            taskIndex = it.getInt(INDEX)

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
//        sharedViewModel.displayInformation()
    }

}