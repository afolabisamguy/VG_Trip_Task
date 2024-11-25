package com.alphatech.vg_trip_task.model

import com.alphatech.vg_trip_task.view.MyTrip
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.client.call.*
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive


val client = HttpClient(CIO) {
    install(ContentNegotiation) {
        json(Json { ignoreUnknownKeys = true })
    }
}

suspend fun createTrip(trip: MyTrip): HttpResponse {
    val response: HttpResponse = client.post("https://triptask.free.beeceptor.com/trips") {
        contentType(ContentType.Application.Json)
        setBody(trip)
    }
    
    return response

}

suspend fun fetchTrips(): List<MyTrip> {
    return try {
        val response = client.get("https://triptask.free.beeceptor.com/trips").body<String>()
        val jsonArray = Json.parseToJsonElement(response).jsonArray

        jsonArray.map { jsonElement ->
            val jsonObject = jsonElement.jsonObject
            MyTrip(
                city = jsonObject["city"]?.jsonPrimitive?.content ?: "",
                startDate = jsonObject["startDate"]?.jsonPrimitive?.content ?: "",
                endDate = jsonObject["endDate"]?.jsonPrimitive?.content ?: "",
                tripName = jsonObject["tripName"]?.jsonPrimitive?.content ?: "",
                tripDescription = jsonObject["tripDescription"]?.jsonPrimitive?.content ?: "",
                travelStyle = jsonObject["travelStyle"]?.jsonPrimitive?.content ?: "",
                hotels = listOf(),
                flights = listOf(),
                activities = listOf()
            )
        }
    } catch(e: Exception){
        
        emptyList()
    }
}
