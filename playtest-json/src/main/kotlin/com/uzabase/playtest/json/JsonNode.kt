package com.uzabase.playtest.json

import com.nfeld.jsonpathkt.JsonPath
import com.nfeld.jsonpathkt.extension.read
import com.fasterxml.jackson.databind.JsonNode as JN

data class JsonNode(val json: JN) {
    companion object {
        fun of(json: String) = JsonPath.parse(json)?.let(::JsonNode) ?: throw RuntimeException()
    }

    inline fun <reified T : Any> get(path: String): T? = json.read(path)

    fun getArrayLength(arrayJsonPath: String): Int? = json.read<List<Any>>(arrayJsonPath)?.size

    fun getFilteredList(arrayJsonPath: String, filterKey: String, filterValue: String): List<Map<Any, Any>>? =
        json.read<List<Map<Any, Any>>>(arrayJsonPath)?.filter { it[filterKey] == filterValue }

    fun toJsonString() = json.toString()
}