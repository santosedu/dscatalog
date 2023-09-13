package br.com.eduardo.dscatalog.resources;

import br.com.eduardo.dscatalog.entities.Category;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryResource {

    @GetMapping
    public ResponseEntity<List<Category>> listAll() {
        List<Category> list = Arrays.asList(new Category(1L, "Eletronics")
                                          , new Category(2L, "Computers"));
        return ResponseEntity.ok(list);
    }
}
