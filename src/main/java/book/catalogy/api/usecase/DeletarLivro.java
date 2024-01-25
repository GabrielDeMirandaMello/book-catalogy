package book.catalogy.api.usecase;

import org.springframework.http.ResponseEntity;

public interface DeletarLivro {

    ResponseEntity<?> execute(Long id);
}
