package book.catalogy.api.usecase;

import book.catalogy.internal.entity.Livro;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface BuscarLivroPorTermo {

    ResponseEntity<List<Livro>> execute(String termo);
}
