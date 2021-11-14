package data

import model.ToDo

class DataSource {
    fun loadData() :List<ToDo> {
         var listOfToDo = mutableListOf<ToDo>(
             ToDo("","","","")
         )
        return listOfToDo

    }
}