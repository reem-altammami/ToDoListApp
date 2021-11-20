package com.example.todolistapp

import model.ToDo

object Argument {
    const val INDEX = "itemPosition"
}

val allTask: MutableList<ToDo> =
    mutableListOf(
        ToDo("Shopping", "rrrrrrrrrrrrrrrrr", "2021-7-5", "2021-6-20", true, true),
        ToDo("studying", "aaaaaaaaaaaaaaaaaa", "2021-4-15", "2020-12-1", false, true),
        ToDo("visit doctor", "vvvvvvvvvvvvvvvvvvv", "2021-12-25", "2021-10-1", true, false)


    )