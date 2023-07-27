package com.example.shahid

import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.stereotype.Controller

@Controller
class GraphQLSupport {
    @QueryMapping
    fun hello() = "Hello World!"
}
