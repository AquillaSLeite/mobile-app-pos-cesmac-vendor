package com.example.mobileappposcesmacvendor.core.domain

import java.math.BigDecimal

private val HUNDRED_BIG_DECIMAL = BigDecimal(100)

data class User(
    val email: String,
    val name: String,
    val password: String,
    val balance: Int = 0,
    val balanceHistory: Map<String, Int> = mutableMapOf()
) {

    fun balanceToString(): String =
        BigDecimal(balance).divide(HUNDRED_BIG_DECIMAL).toString()
}
