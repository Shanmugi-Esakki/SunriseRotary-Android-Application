package com.example.SunriseSignup.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import com.example.SunriseSignup.Entity.Contact;
import com.example.SunriseSignup.Entity.Donation;
import com.example.SunriseSignup.Entity.User;
import com.example.SunriseSignup.Service.ContactService;
import com.example.SunriseSignup.Service.DonationService;
import com.example.SunriseSignup.Service.SignupService;


@Controller

public class SignupController {
	
	
	@Autowired
    private SignupService userService;
	 @Autowired
	    private ContactService contactService;
	 @Autowired
	    private DonationService donationService;
	 
	

    @GetMapping("api/about/")
    public ModelAndView getAbout() {
    	 System.out.println("Hello in about");
        //userService.createUser(user);
        return new ModelAndView("about-us");
    }
    @GetMapping("api/news/")
    public ModelAndView getNews() {
    	 System.out.println("Hello in news");
        //userService.createUser(user);
        return new ModelAndView("news-media");
    }
    @GetMapping("api/serviceprojects/")
    public ModelAndView getserviceProjects() {
    	 System.out.println("Hello in service");
        //userService.createUser(user);
        return new ModelAndView("service-projects");
    }
    @GetMapping("api/contact/")
    public ModelAndView getContact() {
    	 System.out.println("Hello in contact");
        //userService.createUser(user);
        return new ModelAndView("contactus");
    }
    @GetMapping("api/donate/")
    public ModelAndView getDonate() {
    	 System.out.println("Hello in donate");
        //userService.createUser(user);
        return new ModelAndView("donate");
    }
    @GetMapping("api/insta/")
    public ModelAndView getInsta() {
    	 System.out.println("Hello in insta");
        //userService.createUser(user);
        return new ModelAndView("insta");
    }
 @GetMapping("api/Facebook/")
    public ModelAndView getFacebook() {
    	 System.out.println("Hello in fb");
        //userService.createUser(user);
        return new ModelAndView("Facebook");
    }
 @GetMapping("api/login")
 public ModelAndView getlogin() {
 	 System.out.println("Hello in login");
     //userService.createUser(user);
     return new ModelAndView("login");
 }
 
 @GetMapping("api/{lang}/{page}")
 public ModelAndView getPage(@PathVariable String lang, @PathVariable String page) {
     // For debugging
     System.out.println("Hello in " + page + " in language " + lang);
     
     // Return the appropriate view based on the language and page
     String viewName = String.format("translated_html/%s/%s", lang, page);
     return new ModelAndView(viewName);
 }
    @PostMapping("/api/contactform/")
    public String saveContact(WebRequest request) {
    	System.out.println("saving contact");
    	String name = request.getParameter("name");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        String subject = request.getParameter("subject");
        String message = request.getParameter("message");
       
        Contact contact = new Contact();
        contact.setName(name);
        contact.setEmail(email);
        contact.setPhone(phone);
        contact.setAddress(address);
        contact.setSubject(subject);
        contact.setMessage(message);

        // Save the contact to the database
        contactService.saveContact(contact);
         System.out.print("worked");
         return "Contact form submitted successfully";
    }
    
   

    @PostMapping("/api/donateDetails/")
    public String saveDonation(WebRequest request) {
        System.out.println("Saving donation details");
        
        String fund = request.getParameter("fund[]");
        String country = request.getParameter("country");
        String currency = request.getParameter("currency");
        String amount = request.getParameter("amount");
        String firstName = request.getParameter("first_name");
        String familyName = request.getParameter("family_name");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String pan = request.getParameter("pan");
        String addressLine1 = request.getParameter("address_line_1");
        String addressLine2 = request.getParameter("address_line_2");
        String addressLine3 = request.getParameter("address_line_3");
        String city = request.getParameter("city");
        String postalCode = request.getParameter("postal_code");

        Donation donation = new Donation();
        donation.setCountry(country);
        donation.setCurrency(currency);
        donation.setAmount(amount);
        donation.setFirstName(firstName);
        donation.setFamilyName(familyName);
        donation.setPhone(phone);
        donation.setEmail(email);
        donation.setPan(pan);
        donation.setAddressLine1(addressLine1);
        donation.setAddressLine2(addressLine2);
        donation.setAddressLine3(addressLine3);
        donation.setCity(city);
        donation.setPostalCode(postalCode);

        // Save the donation to the database
        donationService.saveDonation(donation);
        System.out.println("Donation details saved successfully");

        return "Donation details submitted successfully";
    }
    
    
    @PostMapping("api/members-only/")
    public ModelAndView getmembersonly(WebRequest request,Model m) {
    	String username = request.getParameter("username");
        String password = request.getParameter("password");
    	 System.out.println("Hello in members-only");
    	 if(username.equals("Shanmugi") && password.equals("@123")) {
    		 List<Contact> contacts = contactService.getAllContacts();
    	        if (contacts.isEmpty()) {
    	            System.out.println("No contacts found.");
    	        } else {
    	        contacts.forEach(contact -> System.out.println(contact));
    	        }
    	        m.addAttribute("contacts", contacts);
    	        
    		 return new ModelAndView("members-only"); 
    	 }else {
    		
    		  m.addAttribute("msg", "Invalid Login Credencials");
    		 return new ModelAndView("login");
    	 }
        
    }
    
    
    
    
//    @GetMapping("api/login")
//    public String login() {
//        return "login"; // Returns login.html view
//    }

//    @GetMapping("api/members-only")
//    public String membersOnly() {
//        return "members-only"; // Returns members-only.html view
//    }
//
//	    @GetMapping("/api/users")	    
//	    public List<User> getAllUsers() {
// 	        return userService.getAllUsers();
// 	    }
// 
// 	    @GetMapping("/{id}")
// 	    public User getUserById(@PathVariable Long id) {
// 	        return userService.getUserById(id);
// 	    }
// 
// 	    @PutMapping("/{id}")
// 	    public User updateUser(@PathVariable Long id, @RequestBody User user) {
// 	        user.setId(id);
// 	        return userService.updateUser(user);
// 	    }
// 
// 	    @DeleteMapping("/{id}")
// 	    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
// 	        userService.deleteUser(id);
// 	        return ResponseEntity.ok("User deleted successfully");
//	     }
    
//    @GetMapping("/login")
//    public ModelAndView showLogin() {
//        return new ModelAndView("login");
//    }
//    @PostMapping("/api/login")
//    public ModelAndView login(@RequestBody User user) {
//        // Validate the user's email and password
//        // If they are valid, redirect the user to the home page
//        // Otherwise, return an error message
//        if (userService.isValidUser(user.getEmail(), user.getPassword())) {
//        	System.out.println("Valid login");
//            return new ModelAndView("redirect:/");
//        } else {
//        	System.out.println("Not Valid login");
//            return new ModelAndView("redirect:/login");
//        }
//    }
	}
	

