package com.example.senakitchnew.send

object UserAdmin {
    private var userId: Int = -1

    fun getUserId(): Int {
        return userId
    }

    fun setUserId(id: Int) {
        userId = id
    }
}