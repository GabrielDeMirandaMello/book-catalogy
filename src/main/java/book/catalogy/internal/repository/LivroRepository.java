package book.catalogy.internal.repository;

import book.catalogy.internal.entity.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface LivroRepository extends JpaRepository<Livro, Long> {
    Optional<Livro> findByTituloIgnoreCase(String titulo);
    Optional<List<Livro>> findAllByTituloContainsIgnoreCase(String termo);
    Optional<List<Livro>> findAllByAutorContainsIgnoreCase(String termo);
    Optional<List<Livro>> findAllByEditoraContainsIgnoreCase(String termo);
}
