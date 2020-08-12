package com.evolent.contact.maintainer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.evolent.contact.maintainer.Entity.Contact;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long>{

}
