package book.catalogy.internal.usecase;

import book.catalogy.api.usecase.BuscarLivroPorTermo;
import book.catalogy.internal.entity.Livro;
import book.catalogy.internal.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultBuscarLivroPorTermo implements BuscarLivroPorTermo {
    @Autowired
    private LivroRepository livroRepository;
    @Override
    public ResponseEntity<List<Livro>> execute(String termo) {
        return null;
    }
}
