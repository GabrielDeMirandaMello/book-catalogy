package book.catalogy.internal.controller;

import book.catalogy.api.controller.LivroController;
import book.catalogy.api.usecase.*;
import book.catalogy.internal.entity.Livro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DefaultLivroController implements LivroController {

    @Autowired
    private AdicionarLivro adicionarLivro;
    @Autowired
    private BuscarLivroPorId buscarLivroPorId;
    @Autowired
    private BuscarLivroPorTermo buscarLivroPorTermo;
    @Autowired
    private AtualizarLivro atualizarLivro;
    @Autowired
    private DeletarLivro deletarLivro;
    @Autowired
    private BuscarQuantidadeLivro buscarQuantidadeLivro;

    @Override
    public ResponseEntity<?> adicionarLivro(Livro livro) {
        return this.adicionarLivro.execute(livro);
    }

    @Override
    public ResponseEntity<Livro> buscarLivroPorId(Long id) {
        return this.buscarLivroPorId.execute(id);
    }

    @Override
    public ResponseEntity<List<Livro>> buscarLivroPorTermo(String termo) {
        return this.buscarLivroPorTermo.execute(termo);
    }

    @Override
    public ResponseEntity<Livro> atualziarLivroPorId(Long id) {
        return this.atualizarLivro.execute(id);
    }

    @Override
    public ResponseEntity<?> deletarLivro(Long id) {
        return this.deletarLivro.execute(id);
    }

    @Override
    public ResponseEntity<Long> buscarQuantidadeLivro() {
        return this.buscarQuantidadeLivro.execute();
    }
}
