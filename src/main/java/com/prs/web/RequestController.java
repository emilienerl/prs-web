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
	public List<Request> getAll() {
		return requestRepo.findAll();
	}

	// Get a request by id
	
	@GetMapping("/{id}")
	public Optional<Request> getById(@PathVariable int id) {
		return requestRepo.findById(id);
	}

	// Add a request
	
	@PostMapping("/")
	public Request addRequest(@RequestBody Request r) {
		r = requestRepo.save(r);
		return r;
	}

	// Update a request
	
	@PutMapping("/")
	public Request updateRequest(@RequestBody Request r) {
		r = requestRepo.save(r);
		return r;
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

}
