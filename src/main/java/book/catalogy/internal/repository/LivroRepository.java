package book.catalogy.internal.repository;

import book.catalogy.internal.entity.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface LivroRepository extends JpaRepository<Livro, Long> {

//    @Query(value = "SELECT * FROM livro " +
//            "WHERE titulo LIKE LOWER(concat('%', :termo, '%')) " +
//            "OR autor LIKE LOWER(concat('%', :termo, '%')) " +
//            "OR editora LIKE LOWER(concat('%', :termo, '%'))")
//    List<Livro> findByTituloAndAutorAndEditoraContainsIgnoreCase(String termo);

    Optional<Livro> findByTituloIgnoreCase(String titulo);
}
