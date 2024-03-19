package com.Myrestaurant.Myrestaurant.Service;

import com.Myrestaurant.Myrestaurant.Entity.CustomerEntity;
import com.Myrestaurant.Myrestaurant.Repositery.CustomerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService  {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    
    public List<CustomerEntity> getAllCustomers() {
        return customerRepository.findAll();
    }

    public CustomerEntity getCustomerById(Long id) {
        Optional<CustomerEntity> optionalCustomer = customerRepository.findById(id);
        return optionalCustomer.orElse(null);
    }

  
    public CustomerEntity createCustomer(CustomerEntity customer) {
        return customerRepository.save(customer);
    }

    
    public CustomerEntity updateCustomer(Long id, CustomerEntity customer) {
        if (customerRepository.existsById(id)) {
            customer.setCustId(id); // Ensure the correct ID is set
            return customerRepository.save(customer);
        } else {
            return null;
        }
    }

   
    public boolean deleteCustomer(Long id) {
        if (customerRepository.existsById(id)) {
            customerRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
