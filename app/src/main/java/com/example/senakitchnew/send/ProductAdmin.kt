package com.example.senakitchnew.send

object ProductAdmin {
    private var productId: Int = -1

    fun getProductId(): Int {
        return productId
    }

    fun setProductId(id: Int) {
        productId = id
    }
}