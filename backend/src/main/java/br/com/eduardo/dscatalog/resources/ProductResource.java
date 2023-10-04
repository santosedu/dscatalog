package br.com.eduardo.dscatalog.resources;

import br.com.eduardo.dscatalog.dto.CategoryDTO;
import br.com.eduardo.dscatalog.dto.ProductDTO;
import br.com.eduardo.dscatalog.entities.Product;
import br.com.eduardo.dscatalog.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/products")
public class ProductResource {
    @Autowired
    private ProductService service;

    @GetMapping
    public ResponseEntity<Page<ProductDTO>> listAll(
            @RequestParam(value ="page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "12") Integer linesPerPage,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction,
            @RequestParam(value = "orderBy", defaultValue = "name") String orderBy) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), String.valueOf(orderBy));

        Page<ProductDTO> list = service.findAllPaged(pageRequest);
        return ResponseEntity.ok().body(list);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> findById(@PathVariable("id") Long id) {
        ProductDTO dto = service.findBYId(id);
       return ResponseEntity.ok().body(dto);
    }

    @PostMapping
    public ResponseEntity<ProductDTO> insert(@RequestBody ProductDTO dto) {
        dto = service.insert(dto);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(dto.getId())
                .toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> update(@PathVariable ("id") Long id, @RequestBody ProductDTO dto) {
        ProductDTO obj = service.update(id, dto);
        return  ResponseEntity.ok().body(obj);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable ("id") Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/search")
    public Page<Product> search(@RequestParam("searchTerm") String searchTerm,
                                 @RequestParam(value = "page",
                                    required = false,
                                    defaultValue = "0") int page,
                                 @RequestParam(value = "size",
                                        required = false,
                                        defaultValue = "10") int size) {
        return service.search(searchTerm, page, size);
    }

}
