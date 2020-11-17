package com.prs.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prs.business.Request;
import com.prs.db.RequestRepo;

@CrossOrigin
@RestController
@RequestMapping("/requests")
public class RequestController {
	
	@Autowired
	private RequestRepo requestRepo;

	// Get all requests
	
	@GetMapping("/")
	public List<Request> getAllRequests() {
		return requestRepo.findAll();
	}

	// Get a request by id
	
	@GetMapping("/{id}")
	public Optional<Request> getRequestById(@PathVariable int id) {
		return requestRepo.findById(id);
	}
	
	// Review list of requests by id
	
	 @GetMapping("/requests/list-review/{id}")
	 	public List<Request> getAllRequestsByIdAndStatus(@PathVariable int id ) {
		 	return requestRepo.findByUserIdNotAndStatus(id, "Review");
	 }

	// Add a Request
		@PostMapping("/")
		public Request addRequest(@RequestBody Request r)
		{	
			if(r != null)
			{
				System.out.println("New request created");
				System.out.println("Total price =: " + r.getTotal());
				return requestRepo.save(r);
			}
			else
			{
				System.out.println("No request added");
				return null;
			}
		}
		
		// Delete a request
		
		@DeleteMapping("/{id}")
		public Request deleteRequest(@PathVariable int id) {
			// Optional type will wrap a request
			Optional<Request> r = requestRepo.findById(id);
			// isPresent() will return true if a request was found
			if (r.isPresent()) {
				requestRepo.deleteById(id);
			} else {
				System.out.println("Error - request not found for id " + id);
			}
			return r.get();
		}

	

		// Set a Request to be under review for $50
		@PutMapping("/review")
		public Request SetRequests(@RequestBody Request r)
		{
			if(r.getTotal() >= 50.00)
			{
				r.setStatus("Review");
			} else {
				r.setStatus("Approved");
			}
				r.setSubmittedDate(java.time.LocalDateTime.now().toString());
				return requestRepo.save(r);
			
		}
		
		// Set a Request to be approved
		
		@PutMapping("/approve")
		public Request approveRequest(@RequestBody Request r) {
			r.setStatus("Approved");
			r = requestRepo.save(r);
			return r;
		}
		
		// Set a Request to be rejected
		
		@PutMapping("/reject")
		public Request rejectRequest(@RequestBody Request r) {
				r.setStatus("Rejected");
				r = requestRepo.save(r);
				return r;
		}
}
	