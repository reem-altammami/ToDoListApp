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


     val title = MutableLiveData<String>()

     val description = MutableLiveData<String>()
//    val description: LiveData<String>
//        get() = _description

    val dueDate = MutableLiveData<String>()
//    val dueDate: LiveData<String>
//        get() = _dueDate

    private val _creationDate = MutableLiveData<String>()
    val creationDate: LiveData<String>
        get() = _creationDate

     val isComplete = MutableLiveData<Boolean>()
//    val isComplete: LiveData<Boolean>
//        get() = _isComplete
//    fun add() {
//        var x = ToDo("mmm", "ggg", "", true)
//        allTask.add(x)
//        _taskToDo.add(ToDo("ff", "ggg", "", true))
//
//        allTask.addAll(_taskToDo)
//        Log.d("Reem", "add: $allTask")
//    }

    fun addTaskToList() {
        var info=ToDo(title.value!!,description.value!!,dueDate.value!!,creationDate.value!!,isComplete.value!!)
        allTask.add(info)
    }
    fun getEmptyFields(){
        title.value = ""
        description.value = ""
        _creationDate.value = ""
        dueDate.value = ""
        isComplete.value = false
    }

    fun displayInformation() {
        val item = allTask[_currentTaskPosition.value!!]
        title.value = item.title
        description.value = item.description
        _creationDate.value = item.creationDate
        dueDate.value = item.dueDate
        isComplete.value = item.isComplete
    }
    fun removeTask(){
        allTask.removeAt(_currentTaskPosition.value!!)
    }
    fun updatedTaskInfo(){
        var info=ToDo(title.value!!,description.value!!,dueDate.value!!,creationDate.value!!,isComplete.value!!)
allTask[_currentTaskPosition.value!!] = info
    }

//    fun maketComplete(isComp : Boolean){
//        _isComplete.value = isComp
//    }
}
