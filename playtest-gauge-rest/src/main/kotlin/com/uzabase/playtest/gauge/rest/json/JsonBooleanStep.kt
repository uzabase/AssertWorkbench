package com.uzabase.playtest.gauge.rest.json

import com.thoughtworks.gauge.Step
import com.uzabase.playtest.gauge.rest.DataStore
import com.uzabase.playtest.json.JsonNode
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotEquals

class JsonBooleanStep {
    @Step("レスポンスのJSONの<jsonPath>が真偽値の<expected>である")
    fun assertEquals(jsonPath: String, expected: Boolean) {
        val json = DataStore.loadResponseBodyFromScenario().string
        val actual = JsonNode.of(json).get<Boolean>(jsonPath)
        assertEquals(expected, actual)
    }

    @Step("レスポンスのJSONの<jsonPath>が真偽値の<expected>でない")
    fun assertNotEquals(jsonPath: String, expected: Boolean) {
        val json = DataStore.loadResponseBodyFromScenario().string
        val actual = JsonNode.of(json).get<Boolean>(jsonPath)
        assertNotEquals(expected, actual)
    }

    @Step("レスポンスのJSONの<jsonPath>が真偽値のtrueである")
    fun assertTrue(jsonPath: String) {
        val json = DataStore.loadResponseBodyFromScenario().string
        val actual = JsonNode.of(json).get<Boolean>(jsonPath)
        assertEquals(true, actual)
    }

    @Step("レスポンスのJSONの<jsonPath>が真偽値のtrueでない")
    fun assertNotTrue(jsonPath: String) {
        val json = DataStore.loadResponseBodyFromScenario().string
        val actual = JsonNode.of(json).get<Boolean>(jsonPath)
        assertNotEquals(true, actual)
    }

    @Step("レスポンスのJSONの<jsonPath>が真偽値のfalseである")
    fun assertFalse(jsonPath: String) {
        val json = DataStore.loadResponseBodyFromScenario().string
        val actual = JsonNode.of(json).get<Boolean>(jsonPath)
        assertEquals(false, actual)
    }

    @Step("レスポンスのJSONの<jsonPath>が真偽値のfalseでない")
    fun assertNotFalse(jsonPath: String) {
        val json = DataStore.loadResponseBodyFromScenario().string
        val actual = JsonNode.of(json).get<Boolean>(jsonPath)
        assertNotEquals(false, actual)
    }

    @Step("レスポンスのJSONの<jsonPath>の配列の、UniqueKey<uniqueKey>の値が<filterValue>である要素の<key>が、真偽値の<expected>である")
    fun assertEqualsInUniqueObject(
        jsonPath: String,
        uniqueKey: String,
        filterValue: String,
        key: String,
        expected: Boolean
    ) {
        val json = DataStore.loadResponseBodyFromScenario().string
        val element = JsonNode.of(json).getUniqElementInArray(jsonPath, uniqueKey, filterValue)
        assertEquals(expected, element[key])
    }
}
