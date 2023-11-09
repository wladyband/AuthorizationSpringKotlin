package com.segurity.resource

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/alou-mundo")
class AloMundoController {

    @GetMapping
    fun aloMundo(): String {
        return "Alô Mundo!"
    }
}