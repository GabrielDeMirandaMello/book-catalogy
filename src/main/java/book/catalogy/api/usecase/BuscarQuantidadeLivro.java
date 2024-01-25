package book.catalogy.api.usecase;

import org.springframework.http.ResponseEntity;

public interface BuscarQuantidadeLivro {

    ResponseEntity<Long> execute();
}
