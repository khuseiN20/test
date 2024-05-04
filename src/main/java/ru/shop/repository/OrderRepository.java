package ru.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.shop.model.Order;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<Order, UUID> { }
//public class OrderRepository implements IRepository<Order> {
//
//    List<Order> orders = new ArrayList<>();
//
//    @Override
//    public void save(Order order) {
//        orders.add(order);
//    }
//
//    @Override
//    public List<Order> findAll() {
//        return orders;
//    }
//
//    @Override
//    public Optional<Order> findById(UUID id) {
//        return findAll().stream().
//                filter(order -> order.getId().equals(id))
//                .findFirst();
//    }
//}
