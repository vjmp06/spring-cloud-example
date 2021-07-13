package one.digitalinnovation.experts.productcatalog.repository;

import org.springframework.data.repository.CrudRepository;

import one.digitalinnovation.experts.productcatalog.model.Product;

public interface ProductRepository extends CrudRepository<Product, Integer> {
}
