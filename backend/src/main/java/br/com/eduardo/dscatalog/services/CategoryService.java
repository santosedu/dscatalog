package br.com.eduardo.dscatalog.services;

import br.com.eduardo.dscatalog.dto.CategoryDTO;
import br.com.eduardo.dscatalog.entities.Category;
import br.com.eduardo.dscatalog.repositories.CategoryRepository;
import br.com.eduardo.dscatalog.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository repository;


    public List<CategoryDTO> findAll() {
        List<Category> list = repository.findAll();
        return list.stream().map(CategoryDTO::new).collect(Collectors.toList());
    }


    public CategoryDTO findBYId(Long id) {
        Optional<Category> obj = repository.findById(id);
        Category entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found with id: " + id));
        return new CategoryDTO(entity);
    }


    public Page<Category> search(String searchTerm, int page, int size) {
        PageRequest request = PageRequest.of(page, size, Sort.Direction.ASC, "name");
        return repository.search(searchTerm.toLowerCase(), request);
    }
    @Transactional
    public CategoryDTO insert(CategoryDTO dto) {
        Category entity = new Category();
        entity.setName(dto.getName());

        repository.save(entity);
        return new CategoryDTO(entity);

    }
    @Transactional
    public CategoryDTO update(Long id, CategoryDTO dto) {
        try {
            Category entity = repository.getOne(id);
            entity.setName(dto.getName());
            entity = repository.save(entity);
            return new CategoryDTO(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Id not found " + id);
        }
    }
}
