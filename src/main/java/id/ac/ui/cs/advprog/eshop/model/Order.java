package id.ac.ui.cs.advprog.eshop.model;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.Builder;

@Builder
@Getter
public class Order {
    String id;
    List<Product> products;
    Long orderTime;
    String author;
    @Setter
    String status;

    public Order(String id, List<Product> products, Long orderTime, String author) {
    }

    public Order(String id, List<Product> products, Long orderTime, String author, String status){
    }
}
