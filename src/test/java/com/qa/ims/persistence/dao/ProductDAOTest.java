package com.qa.ims.persistence.dao;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.qa.ims.persistence.domain.Product;
import com.qa.ims.utils.DBUtils;

public class ProductDAOTest {

    private final ProductDAO DAO = new ProductDAO();

    @Before
    public void setup() {
        DBUtils.connect();
        DBUtils.getInstance().init("src/test/resources/sql-schema.sql", "src/test/resources/sql-data.sql");
    }

    @Test
    public void testCreate() {
        final Product created = new Product(2L, "brown slate", 200L, 17.0);
        assertEquals(created, DAO.create(created));
    }

    @Test
    public void testReadAll() {
        List<Product> expected = new ArrayList<>();
        expected.add(new Product(1L,"pink slate", 300L, 18.0));
        assertEquals(expected, DAO.readAll());
    }

    @Test
    public void testReadLatest() {
        assertEquals(new Product(1L, "pink slate",300L, 18.0), DAO.readLatest());
    }

    @Test
    public void testRead() {
        final long productId = 1L;
        assertEquals(new Product(productId, "pink slate", 300L, 18.0), DAO.read(productId));
    }

    @Test
    public void testUpdate() {
        final Product updated = new Product(1L, "black granite slab", 600L, 19.0);
        assertEquals(updated, DAO.update(updated));

    }

    @Test
    public void testDelete() {
        assertEquals(1, DAO.delete(1));
    }
}
