package ru.shop.service;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.shop.exception.BadProductReturnCountException;
import ru.shop.exception.EntityNotFoundException;
import ru.shop.model.Order;
import ru.shop.model.ProductReturn;
import ru.shop.repository.ProductReturnRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ProductReturnServiceTest {
    private ProductReturnRepository productReturnRepository = Mockito.mock();
    private ProductReturnService productReturnService = new ProductReturnService(productReturnRepository);

    @Test
    public void shouldAddProductReturn() {
        Order order = new Order(UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID(), 5, 100);
        productReturnService.add(order, 3);
        verify(productReturnRepository, times(1)).save(any(ProductReturn.class));
    }

    @Test
    public void shoulAddThrowsBadProductReturnCountException() {
        Order order = new Order(UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID(), 5, 100);
        assertThrows(BadProductReturnCountException.class, () -> {
            productReturnService.add(order, 6);
        });
    }

    @Test
    public void shouldFindAll() {
        List<ProductReturn> productReturns = List.of(new ProductReturn(UUID.randomUUID(), UUID.randomUUID(), LocalDate.now(), 3));
        when(productReturnRepository.findAll()).thenReturn(productReturns);
        List<ProductReturn> result = productReturnService.findAll();
        assertEquals(1, result.size());
    }

    @Test
    public void shouldFindById() {
        UUID id = UUID.randomUUID();
        ProductReturn productReturn = new ProductReturn(id, UUID.randomUUID(), LocalDate.now(), 3);
        when(productReturnRepository.findById(id)).thenReturn(Optional.of(productReturn));
        ProductReturn result = productReturnService.findById(id);
        assertEquals(productReturn, result);
    }

    @Test
    public void shouldFindNotExceptId() {
        UUID id = UUID.randomUUID();
        when(productReturnRepository.findById(id)).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> {
            productReturnService.findById(id);
        });
    }
}
