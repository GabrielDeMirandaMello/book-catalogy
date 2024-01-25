package book.catalogy.api.controller;

import book.catalogy.internal.entity.Livro;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/livros")
@OpenAPIDefinition(info = @Info(
        title = "Api de Sistema de Catálogo de Livros",
        description = "Software para ser utilizado em uma biblioteca e catalogar todos os livros.",
        version = "1.0"),
        servers = @Server(
                url = "http://localhost:8080",
                description = "Este é a url que estár rodando a sua aplicação localmente."
        ))
public interface LivroController {

    @PostMapping
    @Operation(
            summary = "Adicionar Livro",
            description = "Endpoint para adicionar um livro ao sistema.",
            tags = {
                    "Endpoints dos Livros"
            }
    )
    ResponseEntity<?> adicionarLivro(@RequestBody Livro livro);

    @GetMapping("/{id}")
    @Operation(
            summary = "Buscar o Livro por 'id'",
            description = "Endpoint para buscar um livro no sistema por id.",
            tags = {
                    "Endpoints dos Livros"
            }
    )
    ResponseEntity<Livro> buscarLivroPorId(@PathVariable Long id);

    @GetMapping
    @Operation(
            summary = "Buscar o Livro por 'termo'",
            description = "Endpoint para buscar um livro no sistema por termo.",
            tags = {
                    "Endpoints dos Livros"
            }
    )
    ResponseEntity<List<Livro>> buscarLivroPorTermo(@RequestParam String termo);

    @PutMapping("/{id}")
    @Operation(
            summary = "Atualizar Livro",
            description = "Endpoint para atualizar um livro no sistema.",
            tags = {
                    "Endpoints dos Livros"
            }
    )
    ResponseEntity<Livro> atualziarLivroPorId(@PathVariable Long id, @RequestBody Livro livroAtualizado);

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Deletar um Livro",
            description = "Endpoint para excluir um livro do sistema.",
            tags = {
                    "Endpoints dos Livros"
            }
    )
    ResponseEntity<?> deletarLivro(@PathVariable Long id);

    @GetMapping("/contagem")
    @Operation(
            summary = "Buscar a quantidade de Livros",
            description = "Endpoint para trazer a quantidade de livros na base.",
            tags = {
                    "Endpoints dos Livros"
            }
    )
    ResponseEntity<Long> buscarQuantidadeLivro();
}
