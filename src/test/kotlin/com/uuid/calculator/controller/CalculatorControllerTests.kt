package com.uuid.calculator.controller

import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.http.HttpHeaders
import org.skyscreamer.jsonassert.JSONAssert
import org.springframework.http.HttpEntity
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus


@RunWith(SpringRunner::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CalculatorControllerTests {

    @LocalServerPort
    private val port: Int = 0

    var restTemplate = TestRestTemplate()

    var headers = HttpHeaders()

    @Test
    fun should_return_correct_uuid() {
        val entity = HttpEntity<String>(null, headers)
        val mostSignBits = "4644419904557499469"
        val leastSignBits = "-9155120914563656942"
        val response = restTemplate.exchange(createURLWithPort("/uuid/$mostSignBits/$leastSignBits"),
                HttpMethod.GET, entity, String::class.java)

        val expected = "\n" +
                "{\"key\":\"40744b4b-c6e7-444d-80f2-7a08bbaf1312\",\"text\":\"This key was generated from -9155120914563656942 to 4644419904557499469\"}"

        JSONAssert.assertEquals(expected, response.body, false)
    }

    @Test
    fun should_fail_with_wrong_input() {
        val entity = HttpEntity<String>(null, headers)
        val mostSignBits = "4644419904557499469L"
        val leastSignBits = "-9155120914563656942L"
        val response = restTemplate.exchange(createURLWithPort("/uuid/$mostSignBits/$leastSignBits"),
                HttpMethod.GET, entity, String::class.java)

        val expected = HttpStatus.BAD_REQUEST

        assertEquals(expected, response.statusCode)
    }

    @Test
    fun should_fail_with_wrong_text_input() {
        val entity = HttpEntity<String>(null, headers)
        val mostSignBits = "a"
        val leastSignBits = "select"
        val response = restTemplate.exchange(createURLWithPort("/uuid/$mostSignBits/$leastSignBits"),
                HttpMethod.GET, entity, String::class.java)

        val expected = HttpStatus.BAD_REQUEST

        assertEquals(expected, response.statusCode)
    }

    private fun createURLWithPort(uri: String): String {
        return "http://localhost:$port$uri"
    }
}

