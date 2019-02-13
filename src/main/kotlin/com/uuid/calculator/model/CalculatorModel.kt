package com.uuid.calculator.model

import java.util.*

class CalculatorModel(mostSignBits: Long, leastSignBits: Long) {
    val key = UUID(mostSignBits, leastSignBits)
    val text = "This key was generated from $leastSignBits to $mostSignBits"
}