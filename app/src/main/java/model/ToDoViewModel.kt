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

    private val _isNotPast = MutableLiveData<Boolean>()
    val isNotPast: LiveData<Boolean>
        get() = _isNotPast

     val isComplete = MutableLiveData<Boolean>()


    fun addTaskToList() {
        isDateNotPast(dueDate.value!!)
        var info=ToDo(title.value!!,description.value!!,dueDate.value!!,_creationDate.value!!,isComplete.value!!,_isNotPast.value!!)
        allTask.add(info)
    }
    fun getEmptyFields(){
        title.value = ""
        description.value = ""
        _creationDate.value = ""
        dueDate.value = ""
        isComplete.value = false
        _isNotPast.value = false
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
        isDateNotPast(dueDate.value!!)
        var info=ToDo(title.value!!,description.value!!,dueDate.value!!,creationDate.value!!,isComplete.value!!,_isNotPast.value!!)
allTask[_currentTaskPosition.value!!] = info
    }



    fun formatDate(date : Long){
        val formatter = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val selectedDate = date
        dueDate.value =formatter.format(selectedDate).toString()
        val calendar = Calendar.getInstance()
        _creationDate.value = formatter.format(calendar.time)
    }


    fun isDateNotPast(taskDate: String)  {
        try {
            val todayDate = SimpleDateFormat("yyyy-MM-dd").parse(taskDate)

            _isNotPast.value = !todayDate.before(Date())
        } catch(ignored: java.text.ParseException) {
            _isNotPast.value = false
        }
    }


}
