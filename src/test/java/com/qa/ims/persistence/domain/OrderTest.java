package com.qa.ims.persistence.domain;

import com.qa.ims.persistence.domain.Customer;
import org.junit.Test;

import nl.jqno.equalsverifier.EqualsVerifier;

public class OrderTest {

    @Test
    public void testEquals() {
        EqualsVerifier.simple().forClass(Customer.class).verify();
    }

}