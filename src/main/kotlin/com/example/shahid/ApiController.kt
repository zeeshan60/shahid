package com.example.shahid

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class ApiController {
    @GetMapping("/api/hello")
    fun hello() = "Hello World!"
}
