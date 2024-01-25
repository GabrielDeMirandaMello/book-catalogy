package book.catalogy.api.usecase;

import book.catalogy.internal.entity.Livro;
import org.springframework.http.ResponseEntity;

public interface AdicionarLivro {

    ResponseEntity<?> execute(Livro livro);
}
