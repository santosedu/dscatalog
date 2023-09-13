package br.com.eduardo.dscatalog.services;

import br.com.eduardo.dscatalog.entities.Category;
import br.com.eduardo.dscatalog.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository repository;
    public List<Category> findAll() {
        List<Category> categoryList = repository.findAll();
        return categoryList;
    }
}
