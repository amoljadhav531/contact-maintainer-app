package com.evolent.contact.maintainer.controller;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import com.evolent.contact.maintainer.ctlr.ContactController;
import com.evolent.contact.maintainer.model.ContactDTO;
import com.evolent.contact.maintainer.service.ContactService;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ContactController.class,ContactControllerTest.class})
class ContactControllerTest {

	@Mock
	private ContactController contactController;
	
	@Mock
	private ContactService contactService;
	
	@Mock
	private Model model;
	
	@Mock
	private BindingResult bindingResult;
	
	@Mock
	private ModelAndView mav;
	
	@Test
	public void testAddContact() {
		PowerMockito.when(model.addAttribute("contact", Mockito.any(ContactDTO.class))).thenReturn(model);		
		Assert.assertEquals("new_contact", contactController.addContact(model));
	}

	@Test
	public void testSaveContact() {
		ContactDTO contact =  new ContactDTO();
		PowerMockito.when(bindingResult.hasErrors()).thenReturn(false);	
		PowerMockito.doNothing().when(contactService).saveContact(contact);
		Assert.assertEquals("redirect:/showAllContact",contactController.saveContact(contact, bindingResult));
	}
	
	@Test
	public void testShowAllContacts() {
		List<ContactDTO> contacts = new ArrayList<ContactDTO>();
		ContactDTO contact =  new ContactDTO();
		contacts.add(contact);
		PowerMockito.when(model.addAttribute("contacts", Mockito.any(List.class))).thenReturn(model);		
		PowerMockito.when(contactService.showAllContacts()).thenReturn(contacts);
		Assert.assertEquals("show_contacts",contactController.showAllContacts(model));
	}
	
	@Test
	public void testShowEditContactPage() {
		ContactDTO contact =  new ContactDTO();
		PowerMockito.when(mav.addObject("contact", Mockito.any(ContactDTO.class))).thenReturn(mav);		
		PowerMockito.when(contactService.getContact(1000L)).thenReturn(contact);
		contactController.showEditContactPage(1000L,model);
	}

	@Test
	public void testUpdateContact() {
		ContactDTO contact =  new ContactDTO();
		PowerMockito.when(bindingResult.hasErrors()).thenReturn(false);	
		PowerMockito.doNothing().when(contactService).updateContact(contact);
		Assert.assertEquals("redirect:/showAllContact",contactController.updateContact(contact, bindingResult));
	}
	
	@Test
	public void testUpdateContactForValidationError() {
		ContactDTO contact =  new ContactDTO();
		PowerMockito.when(bindingResult.hasErrors()).thenReturn(true);	
		Assert.assertEquals("edit_contact",contactController.updateContact(contact, bindingResult));
	}
}
