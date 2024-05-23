package ru.shop.service;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.shop.exception.BadOrderCountException;
import ru.shop.model.Customer;
import ru.shop.model.Order;
import ru.shop.model.Product;
import ru.shop.repository.OrderRepository;

import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

class OrderServiceTest {
    private final OrderRepository repository = Mockito.mock();
    private final OrderService service = new OrderService(repository);

    @Test
    public void shouldAddOrder() {
        // given
        Customer customer = new Customer();
        Product product = new Product();

        // when
        service.add(customer, product, 10);

        // then
        verify(repository).save(any());
    }

    @Test
    public void shouldBadOrderCountException() {
        // given
        Customer customer = new Customer();
        Product product = new Product();

        // when
        Assertions.assertThrows(
                BadOrderCountException.class,
                () -> service.add(customer, product, 0)
        );
    }

    @Test
    public void shouldFindByCustomer() {
        UUID customerId = UUID.randomUUID();
        Customer customer = new Customer(customerId, "name", "phone", 10);
        Order customerOrder = new Order(UUID.randomUUID(), customerId, UUID.randomUUID(), 10,10);
        Order notCustomerOrder = new Order(UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID(), 10,10);

        Mockito.when(repository.findAll()).thenReturn(List.of(customerOrder,notCustomerOrder));

        List<Order> result = service.findByCustomer(customer);

        Assertions.assertEquals(1,result.size());
        Assertions.assertEquals(customerOrder,result.get(0));
    }
    @Test
    public void shouldFindCustomerTotal() {
        // given
        UUID customerId = UUID.randomUUID();
        Customer customer = new Customer(
                customerId, "name", "phone", 20
        );

        Order customerOrder = new Order(
                UUID.randomUUID(), customerId, UUID.randomUUID(), 10, 10
        );
        Order customerOrder2 = new Order(
                UUID.randomUUID(), customerId, UUID.randomUUID(), 10, 20
        );
        Order notCustomerOrder = new Order(
                UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID(), 10, 10
        );
        Mockito.when(repository.findAll())
                .thenReturn(
                        List.of(customerOrder, customerOrder2, notCustomerOrder)
                );

        // when
        long result = service.getTotalCustomerAmount(customer);

        // then
        Assertions.assertEquals(30, result);
    }

}