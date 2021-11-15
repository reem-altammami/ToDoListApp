package model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.todolistapp.allTask
import data.DataSource

class ToDoViewModel : ViewModel() {
    private var _currentTaskPosition = MutableLiveData<Int>()
    val currentTaskPosition
        get() = _currentTaskPosition


    private val _title = MutableLiveData<String>()
    val title: LiveData<String>
        get() = _title

    private val _description = MutableLiveData<String>()
    val description: LiveData<String>
        get() = _description

    private val _dueDate = MutableLiveData<String>()
    val dueDate: LiveData<String>
        get() = _dueDate

    private val _creationDate = MutableLiveData<String>()
    val creationDate: LiveData<String>
        get() = _creationDate

    private val _isComplete = MutableLiveData<Boolean>()
    val isComplete: LiveData<Boolean>
        get() = _isComplete
//    fun add() {
//        var x = ToDo("mmm", "ggg", "", true)
//        allTask.add(x)
//        _taskToDo.add(ToDo("ff", "ggg", "", true))
//
//        allTask.addAll(_taskToDo)
//        Log.d("Reem", "add: $allTask")
//    }

    fun addTaskToList(info: ToDo) {
        allTask.add(info)
    }
    fun getEmptyFields(){
        _title.value = ""
        _description.value = ""
        _creationDate.value = ""
        _dueDate.value = ""
        _isComplete.value = false
    }

    fun displayInformation() {
        val item = allTask[_currentTaskPosition.value!!]
        _title.value = item.title
        _description.value = item.description
        _creationDate.value = item.creationDate
        _dueDate.value = item.dueDate
        _isComplete.value = item.isComplete
    }
}
