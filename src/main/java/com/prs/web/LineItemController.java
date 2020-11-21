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


import com.prs.business.LineItem;
import com.prs.business.Product;
import com.prs.business.Request;
import com.prs.db.LineItemRepo;
import com.prs.db.RequestRepo;

@CrossOrigin
@RestController
@RequestMapping("/line-items")
public class LineItemController {
	
	@Autowired
	private LineItemRepo lineItemRepo;
	
	@Autowired
	private RequestRepo requestRepo;
	
	// Get all LineItems
	@GetMapping("/")
	public List<LineItem> getAll() {
		return lineItemRepo.findAll();
	}
	
	// Get a LineItem by id
	@GetMapping("/{id}")
	public Optional<LineItem> getById(@PathVariable int id) {
		return lineItemRepo.findById(id);
	}
	
	// Add a LineItem
	@PostMapping("/")
	public LineItem addLineItem(@RequestBody LineItem litem) {
		litem = lineItemRepo.save(litem);
		recalculateTotal(litem);
		return litem;
	}
	
	// Update a LineItem
	@PutMapping("/")
	public LineItem updateLineItem(@RequestBody LineItem litem) {
		litem = lineItemRepo.save(litem);
	//recalculate total	
		recalculateTotal(litem);
		return litem;
	}
	
	// Delete a LineItem by id
	@DeleteMapping("{id}")
	public LineItem deleteLineItem(@PathVariable int id) {
		Optional<LineItem> litem = lineItemRepo.findById(id);

		if (litem.isPresent()) {
			lineItemRepo.deleteById(id);
			recalculateTotal(litem.get());
		} else {
			System.out.println("Error - line item not found for id " + id);
		}
		return litem.get();
	}
	
	// Get all LineItems by Request ID
	@GetMapping("/lines-for-pr/{id}")
	public List<LineItem> getAllLineItemsByRequestId(@PathVariable int id) {
		return lineItemRepo.findByRequestId(id);
	}
	
	//Method Recalculates the Requests total based on CRUD functions LineItem Add, Update or Delete
	public void recalculateTotal(LineItem litem) {	
		List<LineItem> lineItems = lineItemRepo.findByRequestId(litem.getRequest().getId());
		
		//loop through the list of line items
		double total = 0.0;
		for(LineItem lineItem : lineItems) {
			Product p = lineItem.getProduct();
			total += (p.getPrice() * lineItem.getQuantity());
		}
		
		Request request = litem.getRequest();
		request.setTotal(total);
		requestRepo.save(request);
	}
	
}