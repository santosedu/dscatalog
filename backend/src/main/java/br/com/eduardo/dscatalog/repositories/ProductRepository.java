package br.com.eduardo.dscatalog.repositories;

import br.com.eduardo.dscatalog.entities.Category;
import br.com.eduardo.dscatalog.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("FROM Product c " +
            "WHERE LOWER(c.name) like %:searchTerm% ")
    Page<Product> search(
            @Param("searchTerm") String searchTerm,
            Pageable pageable);
}
