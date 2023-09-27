package br.com.eduardo.dscatalog.repositories;

import br.com.eduardo.dscatalog.entities.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    @Query("FROM Category c " +
            "WHERE LOWER(c.name) like %:searchTerm% ")
    Page<Category> search(
            @Param("searchTerm") String searchTerm,
            Pageable pageable);

//    Page<Category> listSorted(Long id, Pageable pageable);
}
