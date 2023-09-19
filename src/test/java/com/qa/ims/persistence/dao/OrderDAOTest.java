package com.qa.ims.persistence.dao;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import com.qa.ims.persistence.domain.Order;
import org.junit.Before;
import org.junit.Test;

import com.qa.ims.utils.DBUtils;

public class OrderDAOTest {

    private final OrderDAO DAO = new OrderDAO();

    @Before
    public void setup() {
        DBUtils.connect();
        DBUtils.getInstance().init("src/test/resources/sql-schema.sql", "src/test/resources/sql-data.sql");
    }

    @Test
    public void testCreate() {
        final Order created = new Order(2L, 4L, 3L, "25.07.23", 17.0);
        assertEquals(created, DAO.create(created));
    }

    @Test
    public void testReadAll() {
        List<Order> expected = new ArrayList<>();
        expected.add(new Order(1L, 2L, "13.06.23", 19.24));
        assertEquals(expected, DAO.readAll());
    }

    @Test
    public void testReadLatest() {
        assertEquals(new Order(1L, 2L, 1L, "13.06.23", 19.24), DAO.readLatest());
    }

    @Test
    public void testRead() {
        final long orderId = 1L;
        assertEquals(new Order(orderId, 2L, 1L, "13.06.23", 19.24), DAO.read(orderId));
    }

    @Test
    public void testUpdate() {
        final Order updated = new Order(2L, 1L, "13.06.23", 19.24);
        assertEquals(updated, DAO.update(updated));

    }

    @Test
    public void testDelete() {
        assertEquals(1, DAO.delete(1));
    }
}
