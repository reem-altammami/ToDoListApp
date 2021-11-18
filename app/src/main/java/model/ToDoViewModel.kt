package model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.todolistapp.allTask
import data.DataSource
import java.text.SimpleDateFormat
import java.util.*

class ToDoViewModel : ViewModel() {
    private var _currentTaskPosition = MutableLiveData<Int>()
    val currentTaskPosition
        get() = _currentTaskPosition


    val title = MutableLiveData<String>()

    val description = MutableLiveData<String>()


    val dueDate = MutableLiveData<String>()


    private val _creationDate = MutableLiveData<String>()
    val creationDate: LiveData<String>
        get() = _creationDate

    private val _isPast = MutableLiveData<Boolean>(true)
    val isPast: LiveData<Boolean>
        get() = _isPast

    val isComplete = MutableLiveData<Boolean>()

// this function will calling from AddTaskFragment to add new Task to List
    fun addTaskToList() {
        isDatePast(dueDate.value!!)
        var info = ToDo(
            title.value!!,
            description.value!!,
            dueDate.value!!,
            _creationDate.value!!,
            isComplete.value!!,
            _isPast.value!!
        )
        allTask.add(info)
    }
// this function calling to get empty fields for adding new Task
    fun getEmptyFields() {
        title.value = ""
        description.value = ""
        _creationDate.value = ""
        dueDate.value = ""
        isComplete.value = false
        _isPast.value = false
    }

// this function calling to show details for specific Task based on the index in List
    fun displayInformation() {

        val item = allTask[_currentTaskPosition.value!!]
        title.value = item.title
        description.value = item.description
        _creationDate.value = item.creationDate
        dueDate.value = item.dueDate
        isComplete.value = item.isComplete
        isDatePast(item.dueDate)
    }
    fun displayInformation(pos:Int) {

        val item = allTask[pos]
        title.value = item.title
        description.value = item.description
        _creationDate.value = item.creationDate
        dueDate.value = item.dueDate
        isComplete.value = item.isComplete
        isDatePast(item.dueDate)
    }

// remove task based on specific index
    fun removeTask() {
        allTask.removeAt(_currentTaskPosition.value!!)

    }

// update the task details in list
    fun updatedTaskInfo() {
        isDatePast(dueDate.value!!)
        var info = ToDo(
            title.value!!,
            description.value!!,
            dueDate.value!!,
            creationDate.value!!,
            isComplete.value!!,
            _isPast.value!!
        )
        allTask[_currentTaskPosition.value!!] = info
    }

// this function take date with type of long then convert it to String based date pattern
    fun formatDate(date: Long) {
        val formatter = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val selectedDate = date
        dueDate.value = formatter.format(selectedDate).toString()
        val calendar = Calendar.getInstance()
        _creationDate.value = formatter.format(calendar.time)
    }

    fun formatDueDate(date: Long) {
        val formatter = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val selectedDate = date
        dueDate.value = formatter.format(selectedDate).toString()

    }

    // this function check if date of task past or not based on current date
    fun isDatePast(taskDate: String) {
        try {
            val taskDueDate = SimpleDateFormat("yyyy-MM-dd").parse(taskDate)

            _isPast.value = (taskDueDate.before(Date()))
        } catch (ignored: java.text.ParseException) {
            _isPast.value = false
        }
    }




}
