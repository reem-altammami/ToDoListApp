package data

import model.ToDo

class DataSource {
    fun loadData() :List<ToDo> {
         var listOfToDo = mutableListOf<ToDo>(
             ToDo("going to shop","20/2020","20/20202",true),
             ToDo("going to shop","20/2020","20/20202",true),ToDo("going to shop","20/2020","20/20202",true),
             ToDo("going to shop","20/2020","20/20202",true),ToDo("going to shop","20/2020","20/20202",true),
             ToDo("going to shop","20/2020","20/20202",true),ToDo("going to shop","20/2020","20/20202",true),
             ToDo("going to shop","20/2020","20/20202",true)
         )
        return listOfToDo

    }
}