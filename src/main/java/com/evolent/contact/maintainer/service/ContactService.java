package com.evolent.contact.maintainer.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.evolent.contact.maintainer.Entity.Contact;
import com.evolent.contact.maintainer.dao.ContactDAO;
import com.evolent.contact.maintainer.model.ContactDTO;

@Service
public class ContactService {

	@Autowired
	private ContactDAO contactDAO;

	public void saveContact(ContactDTO contactDTO) {
		Contact contact = new Contact();
		BeanUtils.copyProperties(contactDTO, contact);
		contactDAO.saveContact(contact);
	}

	public List<ContactDTO> showAllContacts() {
		List<Contact> contacts = contactDAO.getAllContacts();
		List<ContactDTO> contactsDTO = new ArrayList<ContactDTO>();
		for (Contact contact : contacts) {
			ContactDTO contactDTO = new ContactDTO();
			BeanUtils.copyProperties(contact, contactDTO);
			contactsDTO.add(contactDTO);
		}
		return contactsDTO;
	}

	public ContactDTO getContact(Long id) {
		Contact contact = contactDAO.getContact(id);
		ContactDTO contactDTO = new ContactDTO();
		BeanUtils.copyProperties(contact, contactDTO);
		return contactDTO;
	}

	public void updateContact(ContactDTO contactDTO) { 
		Contact contact = contactDAO.getContact(contactDTO.getId());
		BeanUtils.copyProperties(contactDTO, contact);
		contactDAO.saveContact(contact);
	}

	public void deleteContact(Long id) {
		contactDAO.deleteContact(id);
	}

}
