package com.example.senakitchnew.send

object PlatosAdmin {
    private var platosId: Int = -1

    fun getPlatosId(): Int {
        return platosId
    }

    fun setPlatosId(id: Int) {
        platosId = id
    }
}