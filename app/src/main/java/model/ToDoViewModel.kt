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

    fun getEmptyFields() {
        title.value = ""
        description.value = ""
        _creationDate.value = ""
        dueDate.value = ""
        isComplete.value = false
        _isPast.value = false
    }


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


    fun removeTask() {
        allTask.removeAt(_currentTaskPosition.value!!)
    }


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


    fun formatDate(date: Long) {
        val formatter = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val selectedDate = date
        dueDate.value = formatter.format(selectedDate).toString()
        val calendar = Calendar.getInstance()
        _creationDate.value = formatter.format(calendar.time)
    }

//
//    fun isDateNotPast(taskDate: String) {
//        try {
//            val taskDueDate = SimpleDateFormat("yyyy-MM-dd").parse(taskDate)
//
//            _isNotPast.value = !(taskDueDate.before(Date()))
//        } catch (ignored: java.text.ParseException) {
//            _isNotPast.value = false
//        }
//    }
    fun isDatePast(taskDate: String) {
        try {
            val taskDueDate = SimpleDateFormat("yyyy-MM-dd").parse(taskDate)

            _isPast.value = (taskDueDate.before(Date()))
        } catch (ignored: java.text.ParseException) {
            _isPast.value = false
        }
    }

}
