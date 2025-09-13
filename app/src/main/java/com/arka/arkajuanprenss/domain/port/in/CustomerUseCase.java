package com.arka.arkajuanprenss.domain.port.in;
import com.arka.arkajuanprenss.domain.model.Customer;

import java.util.List;

public interface CustomerUseCase {
    List<Customer> getAllCustomers();
    Customer getCustomerById(Long id);
    Customer createCustomer(Customer customer);
    Customer updateCustomer(Long id, Customer customer); 
    void deleteCustomer(Long id);
}
