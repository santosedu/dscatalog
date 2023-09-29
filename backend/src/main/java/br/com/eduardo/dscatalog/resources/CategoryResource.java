package br.com.eduardo.dscatalog.resources;

import br.com.eduardo.dscatalog.dto.CategoryDTO;
import br.com.eduardo.dscatalog.entities.Category;
import br.com.eduardo.dscatalog.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryResource {
    @Autowired
    private CategoryService service;

    @GetMapping
    public ResponseEntity<List<CategoryDTO>> listAll() {
        List<CategoryDTO> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }
    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTO> findById(@PathVariable("id") Long id) {
       CategoryDTO dto = service.findBYId(id);
       return ResponseEntity.ok().body(dto);
    }

    @PostMapping
    public ResponseEntity<CategoryDTO> insert(@RequestBody CategoryDTO dto) {
        dto = service.insert(dto);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(dto.getId())
                .toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryDTO> update(@PathVariable ("id") Long id, @RequestBody CategoryDTO dto) {
        CategoryDTO obj = service.update(id, dto);
        return  ResponseEntity.ok().body(obj);
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
