package model

data class ToDo (val title: String, val description:String, val dueDate :String, val creationDate:String, val isComplete:Boolean = false, val isPast : Boolean ){
}