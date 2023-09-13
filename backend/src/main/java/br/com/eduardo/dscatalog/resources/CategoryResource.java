package br.com.eduardo.dscatalog.resources;

import br.com.eduardo.dscatalog.entities.Category;
import br.com.eduardo.dscatalog.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryResource {
    @Autowired
    private CategoryService service;

    @GetMapping
    public ResponseEntity<List<Category>> listAll() {
        List<Category> list = service.findAll();
        return ResponseEntity.ok(list);
    }


}
