package br.com.eduardo.dscatalog.resources;

import br.com.eduardo.dscatalog.entities.Category;
import br.com.eduardo.dscatalog.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryResource {
    @Autowired
    private CategoryService service;

    @GetMapping
    public ResponseEntity<Page<Category>> listAll() {
        Page<Category> list = service.findAll();
        return ResponseEntity.ok((Page<Category>) list);
    }

    @GetMapping("/search")
    public Page<Category> search(@RequestParam("searchTerm") String searchTerm,
                                 @RequestParam(value = "page",
                                    required = false,
                                    defaultValue = "0") int page,
                                 @RequestParam(value = "size",
                                        required = false,
                                        defaultValue = "10") int size) {
        return service.search(searchTerm, page, size);
    }

}
