package br.com.tlf.dip.customerdomain.customermanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.tlf.dip.customerdomain.customermanagement.model.ContactMedium;

@Repository
public interface ContactMediumRepository extends JpaRepository<ContactMedium, Integer> {

}
