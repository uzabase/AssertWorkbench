package com.uzabase.playtest.gauge.rest

import com.thoughtworks.gauge.Step
import com.uzabase.playtest.gauge.rest.DataStore.loadResponseBodyFromScenario
import com.uzabase.playtest.json.JsonNode
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldBeGreaterThan

class JsonStep {
    @Step("レスポンスのJSONの<jsonPath>が文字列の<expected>である")
    fun assertJson(jsonPath: String, expected: String) {
        JsonNode.of(loadResponseBodyFromScenario().string).get<String>(jsonPath) shouldBeEqualTo expected
    }

    @Step("レスポンスのJSONの<jsonPath>が整数の<expected>である")
    fun assertJson(jsonPath: String, expected: Int) {
        JsonNode.of(loadResponseBodyFromScenario().string).get<Int>(jsonPath) shouldBeEqualTo expected
    }

    @Step("レスポンスのJSONの<jsonPath>が小数の<expected>である")
    fun assertJson(jsonPath: String, expected: Double) {
        JsonNode.of(loadResponseBodyFromScenario().string).get<Double>(jsonPath) shouldBeEqualTo expected
    }

    @Step("レスポンスのJSONの<jsonPath>が真偽値の<expected>である")
    fun assertJson(jsonPath: String, expected: Boolean) {
        JsonNode.of(loadResponseBodyFromScenario().string).get<Boolean>(jsonPath) shouldBeEqualTo expected
    }

    @Step("レスポンスのJSONの<jsonPath>の配列の、UniqueKey<uniqueKey>の値が<filterValue>である要素の<key>が、文字列の<expected>である")
    fun assertJsonByUniqueKey(jsonPath: String, uniqueKey: String, filterValue: String, key: String, expected: String) {
        val element =
            JsonNode.of(loadResponseBodyFromScenario().string).getUniqElementInArray(jsonPath, uniqueKey, filterValue)
        element[key] shouldBeEqualTo expected
    }

    @Step("レスポンスのJSONの<jsonPath>の配列の、UniqueKey<uniqueKey>の値が<filterValue>である要素の<key>が、整数値の<expected>である")
    fun assertJsonByUniqueKey(jsonPath: String, uniqueKey: String, filterValue: String, key: String, expected: Int) {
        val element =
            JsonNode.of(loadResponseBodyFromScenario().string).getUniqElementInArray(jsonPath, uniqueKey, filterValue)
        element[key] shouldBeEqualTo expected
    }

    @Step("レスポンスのJSONの<jsonPath>の配列の、UniqueKey<uniqueKey>の値が<filterValue>である要素の<key>が、小数値の<expected>である")
    fun assertJsonByUniqueKey(jsonPath: String, uniqueKey: String, filterValue: String, key: String, expected: Double) {
        val element =
            JsonNode.of(loadResponseBodyFromScenario().string).getUniqElementInArray(jsonPath, uniqueKey, filterValue)
        element[key] shouldBeEqualTo expected
    }

    @Step("レスポンスのJSONの<jsonPath>の配列の、UniqueKey<uniqueKey>の値が<filterValue>である要素の<key>が、真偽値の<expected>である")
    fun assertJsonByUniqueKey(
        jsonPath: String,
        uniqueKey: String,
        filterValue: String,
        key: String,
        expected: Boolean
    ) {
        val element =
            JsonNode.of(loadResponseBodyFromScenario().string).getUniqElementInArray(jsonPath, uniqueKey, filterValue)
        element[key] shouldBeEqualTo expected
    }

    @Step("レスポンスのJSONの<jsonPath>の配列の長さが<length>である")
    fun assertJsonLength(jsonPath: String, length: Int) {
        JsonNode.of(loadResponseBodyFromScenario().string).getArrayLength(jsonPath) shouldBeEqualTo length
    }

    @Step("レスポンスのJSONの<jsonPath>の配列に、Key<key>の値が<value>である要素が存在する")
    fun assertJsonExistElement(jsonPath: String, key: String, value: String) {
        (JsonNode.of(loadResponseBodyFromScenario().string).getFilteredList(jsonPath, key, value)?.size
            ?: 0) shouldBeGreaterThan 0
    }

    @Step("レスポンスのJSONの<jsonPath>が存在しない")
    fun nonExist(jsonPath: String) {
        JsonNode.of(loadResponseBodyFromScenario().string).get<Any>(jsonPath) shouldBeEqualTo null
    }

    @Step("レスポンスのJSONの配列<arrayJsonPath>が、数値<orderKey>の昇順に並んでいる")
    fun assertOrderByAsc(arrayJsonPath: String, sortKey: String) {
        val response =
            JsonNode.of(loadResponseBodyFromScenario().string).get<List<Map<String, Any>>>(arrayJsonPath)!!
        val sorted = JsonList(response).sortByNumber(sortKey, Order.Asc)
        response shouldBeEqualTo sorted
    }

    @Step("レスポンスのJSONの配列<arrayJsonPath>が、数値<orderKey>の降順に並んでいる")
    fun assertOrderByDesc(arrayJsonPath: String, sortKey: String) {
        val response =
            JsonNode.of(loadResponseBodyFromScenario().string).get<List<Map<String, Any>>>(arrayJsonPath)!!
        val sorted = JsonList(response).sortByNumber(sortKey, Order.Desc)
        response shouldBeEqualTo sorted
    }

    private fun JsonNode.getUniqElementInArray(
        arrayJsonPath: String,
        uniqueKey: String,
        filterValue: String
    ) = this.getFilteredList(arrayJsonPath, uniqueKey, filterValue)
        .takeIf { it?.size == 1 }
        ?.first()
        ?: throw IllegalArgumentException("filter: $uniqueKey == $filterValue can not specify element in $arrayJsonPath")
}
