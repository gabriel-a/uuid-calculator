package com.uuid.calculator.controller

import com.uuid.calculator.model.CalculatorModel
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.GetMapping


@RestController
class CalculatorController {
    @GetMapping("/uuid/{mostSignBits}/{leastSignBits}")
    fun calculate(@PathVariable("mostSignBits") mostSignBits: Long,
                  @PathVariable("leastSignBits") leastSignBits: Long): CalculatorModel {
        return CalculatorModel(mostSignBits, leastSignBits)
    }
}