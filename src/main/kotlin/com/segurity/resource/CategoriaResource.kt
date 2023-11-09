package com.segurity.resource


import com.segurity.model.Categoria
import com.segurity.repository.CategoriaRepository
import jakarta.servlet.http.HttpServletResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.support.ServletUriComponentsBuilder


@RestController
@RequestMapping("/categorias")
class CategoriaResource {

    @Autowired
    private lateinit var categoriaRepository: CategoriaRepository

    @GetMapping
    fun listar(): List<Categoria> {
        return categoriaRepository.findAll()
    }

    @PostMapping
    fun criar(@RequestBody categoria: Categoria, response: HttpServletResponse): ResponseEntity<Categoria> {
        val categoriaSalva = categoriaRepository.save(categoria)

        val uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
            .path("/{codigo}")
            .buildAndExpand(categoriaSalva.codigo)
            .toUri()

        response.setHeader("Location", uri.toASCIIString())

        return ResponseEntity.created(uri).body(categoriaSalva)
    }

    @GetMapping("/{codigo}")
    fun buscarPeloCodigo(@PathVariable codigo: Long): Categoria? {
        return categoriaRepository.findById(codigo).orElse(null)
    }
}