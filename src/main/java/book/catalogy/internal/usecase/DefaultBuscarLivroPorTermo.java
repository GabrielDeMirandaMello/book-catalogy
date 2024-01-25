package book.catalogy.internal.usecase;

import book.catalogy.api.usecase.BuscarLivroPorTermo;
import book.catalogy.internal.entity.Livro;
import book.catalogy.internal.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DefaultBuscarLivroPorTermo implements BuscarLivroPorTermo {
    @Autowired
    private LivroRepository livroRepository;

    @Override
    public ResponseEntity<List<Livro>> execute(String termo) {
        List<Livro> listaExibicao = new ArrayList<>();

        Result result = getResult(termo);
        extracted(result, listaExibicao);

        List<Livro> collectList = listaExibicao.stream().distinct().toList();
        if (collectList.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(collectList);
    }

    private static void extracted(Result result, List<Livro> listaExibicao) {
        result.allByTituloConstains().ifPresent(listaExibicao::addAll);
        result.allByAutorConstains().ifPresent(listaExibicao::addAll);
        result.allByEditoraConstains().ifPresent(listaExibicao::addAll);
    }

    private Result getResult(String termo) {
        Optional<List<Livro>> allByTituloConstains = this.livroRepository.findAllByTituloContainsIgnoreCase(termo);
        Optional<List<Livro>> allByAutorConstains = this.livroRepository.findAllByAutorContainsIgnoreCase(termo);
        Optional<List<Livro>> allByEditoraConstains = this.livroRepository.findAllByEditoraContainsIgnoreCase(termo);
        return new Result(allByTituloConstains, allByAutorConstains, allByEditoraConstains);
    }

    private record Result(Optional<List<Livro>> allByTituloConstains, Optional<List<Livro>> allByAutorConstains, Optional<List<Livro>> allByEditoraConstains) {
    }
}
