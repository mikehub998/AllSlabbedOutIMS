package com.qa.ims.controllers;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.qa.ims.controller.ProductController;
import com.qa.ims.persistence.dao.ProductDAO;
import com.qa.ims.persistence.domain.Product;
import com.qa.ims.utils.Utils;

@RunWith(MockitoJUnitRunner.class)
public class ProductControllerTest {

    @Mock
    private Utils utils;

    @Mock
    private ProductDAO dao;

    @InjectMocks
    private ProductController controller;

    @Test
    public void testCreate() {
        final String PRO_NAME = "big slab";
        final Long STO_QUANTITY = 10L;
        final double PRI = 20.00;
        final Product created = new Product(PRO_NAME, STO_QUANTITY, PRI);

        Mockito.when(utils.getString()).thenReturn(PRO_NAME);
        Mockito.when(utils.getLong()).thenReturn(STO_QUANTITY);
        Mockito.when(utils.getDouble()).thenReturn(PRI);
        Mockito.when(dao.create(created)).thenReturn(created);

        assertEquals(created, controller.create());

        Mockito.verify(utils, Mockito.times(1)).getString();
        Mockito.verify(utils, Mockito.times(1)).getLong();
        Mockito.verify(utils, Mockito.times(1)).getDouble();
        Mockito.verify(dao, Mockito.times(1)).create(created);
    }

    @Test
    public void testReadAll() {
        List<Product> products = new ArrayList<>();
        products.add(new Product(1L, "black slab", 20L, 30.00));

        Mockito.when(dao.readAll()).thenReturn(products);

        assertEquals(products, controller.readAll());

        Mockito.verify(dao, Mockito.times(1)).readAll();
    }
// removed second Mockito.verify and mockito.when as causing an error on testing
    @Test
    public void testUpdate() {
        Product updated = new Product(1L, "pink slab", 300L, 18.00);

        Mockito.when(this.utils.getLong()).thenReturn(1L, updated.getStockQuantity());
        Mockito.when(this.utils.getString()).thenReturn(updated.getProductName());
        Mockito.when(this.utils.getDouble()).thenReturn(updated.getPrice());
//        Mockito.when(this.utils.getLong()).thenReturn(updated.getStockQuantity(), updated.getPrice());
        Mockito.when(this.dao.update(updated)).thenReturn(updated);

        assertEquals(updated, this.controller.update());

        Mockito.verify(this.utils, Mockito.times(2)).getLong();
        Mockito.verify(this.utils, Mockito.times(1)).getString();
        Mockito.verify(this.utils, Mockito.times(1)).getDouble();
        Mockito.verify(this.dao, Mockito.times(1)).update(updated);
    }

    @Test
    public void testDelete() {
        final long productId = 1L;

        Mockito.when(utils.getLong()).thenReturn(productId);
        Mockito.when(dao.delete(productId)).thenReturn(1);

        assertEquals(1L, this.controller.delete());

        Mockito.verify(utils, Mockito.times(1)).getLong();
        Mockito.verify(dao, Mockito.times(1)).delete(productId);
    }

}
