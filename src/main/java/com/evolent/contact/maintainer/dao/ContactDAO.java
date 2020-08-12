package com.evolent.contact.maintainer.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.evolent.contact.maintainer.Entity.Contact;
import com.evolent.contact.maintainer.repository.ContactRepository;

@Component
public class ContactDAO {

	@Autowired
	private ContactRepository contactRepository;
	
	public void saveContact(Contact contact) {
		contactRepository.save(contact);
	}

	public List<Contact> getAllContacts() {
		List<Contact> contacts = contactRepository.findAll();
		return contacts;
	}
	
	public Contact getContact(Long id) {
		return contactRepository.getOne(id);
	}
	
	public void deleteContact(Long id) {
		contactRepository.deleteById(id);
	}
}
