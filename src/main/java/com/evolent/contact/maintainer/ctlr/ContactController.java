package com.evolent.contact.maintainer.ctlr;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.evolent.contact.maintainer.model.ContactDTO;
import com.evolent.contact.maintainer.service.ContactService;

@Controller
public class ContactController {
	
	@Autowired
	private ContactService contactService;
	
	@GetMapping(path = "/")
	public String index(Model model){
	   return "redirect:/showAllContact";
	}
	
	@GetMapping(path = "/newContact")
	public String addContact(Model model){
		ContactDTO contact = new ContactDTO();
	    model.addAttribute("contact", contact);
	     
	    return "new_contact";
	}
	
	@PostMapping(path = "/saveContact")
	public String saveContact(@ModelAttribute("contact") @Valid ContactDTO contact, BindingResult bindingResult){
		if (bindingResult.hasErrors()) {
            return "new_contact";
        } else {
        	contactService.saveContact(contact);
		     return "redirect:/showAllContact";
        }
	}
	
	@GetMapping(path = "/showAllContact")
	public String showAllContacts(Model model){
		List<ContactDTO> contacts = contactService.showAllContacts();
	    model.addAttribute("contacts", contacts);
	     
	    return "show_contacts";
	}
	
	@GetMapping(path = "/edit/{id}")
	public ModelAndView showEditContactPage(@PathVariable(name = "id") Long id, Model model) {
	    ModelAndView mav = new ModelAndView("edit_contact");
	    ContactDTO contactDTO = contactService.getContact(id);
	    mav.addObject("contact", contactDTO);
	    return mav;
	}
	
	@PostMapping(path = "/updateContact")
	public String updateContact(@ModelAttribute("contact") @Valid ContactDTO contact, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
            System.out.println("BINDING RESULT ERROR");
            return "edit_contact";
        } else {
        	contactService.updateContact(contact);
		     return "redirect:/showAllContact";
        }
	}
	
	@GetMapping(path = "/delete/{id}")
	public String deleteContact(@PathVariable(name = "id") Long id){
		contactService.deleteContact(id);	     
	    return "redirect:/showAllContact";
	}
	
	@GetMapping(path = "/error")
	public String errorMessage(Model model){
	    return "error";
	}
	
}
