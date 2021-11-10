package br.com.tlf.dip.customerdomain.customermanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.tlf.dip.customerdomain.customermanagement.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

}
