package digitalinnovation.one.experts.shoppingcart.shoppingcart.repository;

import org.springframework.data.repository.CrudRepository;

import digitalinnovation.one.experts.shoppingcart.shoppingcart.model.Cart;

public interface CartRepository extends CrudRepository<Cart, Integer> {
    
}
