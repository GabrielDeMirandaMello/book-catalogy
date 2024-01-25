package book.catalogy.api.usecase;

import book.catalogy.internal.entity.Livro;
import org.springframework.http.ResponseEntity;

public interface AtualizarLivro {

    ResponseEntity<Livro> execute(Long id,  Livro livroAtualizado);
}
