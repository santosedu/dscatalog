package br.com.eduardo.dscatalog.services;

import br.com.eduardo.dscatalog.dto.CategoryDTO;
import br.com.eduardo.dscatalog.entities.Category;
import br.com.eduardo.dscatalog.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository repository;
//    public Page<Category> findAll() {
//        int page = 0;
//        int size = 10;
//        PageRequest pageRequest = PageRequest.of(page, size, Sort.Direction.ASC, "name");
//        return  new PageImpl<Category>(repository.findAll(), pageRequest, size);
//
//    }

    public List<CategoryDTO> findAll() {
        List<Category> list = repository.findAll();
        return list.stream().map(CategoryDTO::new).collect(Collectors.toList());
    }

    /*public Page<Category> listSorted(@PathVariable("id") Long id) {
        int page = 0;
        int size = 10;
        PageRequest pageRequest = PageRequest.of(page, size, Sort.Direction.ASC);
        return new PageImpl<Category>((List<Category>) repository.listSorted(id, pageRequest), pageRequest,size);
    }*/
    public Page<Category> search(String searchTerm, int page, int size) {
        PageRequest request = PageRequest.of(page, size, Sort.Direction.ASC, "name");
        return repository.search(searchTerm.toLowerCase(), request);
    }

    public CategoryDTO findBYId(Long id) {
        Category entity = new Category();
        Optional<Category> obj = repository.findById(id);
        if(obj.isPresent()) {
            entity = obj.get();
        }
        return new CategoryDTO(entity);

    }
}
