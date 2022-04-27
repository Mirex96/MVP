package com.example.mvp

object IdGenerator {
    private var id = 0
    fun generatorId(): Int {
        id += 1
        return id
    }
}