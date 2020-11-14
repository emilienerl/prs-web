package com.prs.business;

import java.time.LocalDate;
import javax.persistence.*;

@Entity
public class Request {
	
	// Fields
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private int id;
		
		@ManyToOne
		@JoinColumn(name = "UserId")
		private User user;
		
		private String description;
		private String justification;
		private LocalDate dateNeeded;
		private String deliveryMode;
		private String status;
		private double total;
		private String submittedDate;
		private String reasonForRejection;
		
		
		// Open Constructor
		
		public Request() {
			status = "new";
			submittedDate = java.time.LocalDateTime.now().toString();
		}
		
		//Constructors

		public Request(int id, User user, String description, String justification, LocalDate dateNeeded,
				String deliveryMode, String status, double total, String submittedDate,
				String reasonForRejection) {
			super();
			this.id = id;
			this.user = user;
			this.description = description;
			this.justification = justification;
			this.dateNeeded = dateNeeded;
			this.deliveryMode = deliveryMode;
			this.status = status;
			this.total = total;
			this.submittedDate = submittedDate;
			this.reasonForRejection = reasonForRejection;
			
			if (status.equals(""))
				status = "new";
			if(submittedDate.equals(""));
				submittedDate = java.time.LocalDateTime.now().toString();
		}

		//Getter and Setters
		
		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public User getUser() {
			return user;
		}

		public void setUser(User user) {
			this.user = user;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public String getJustification() {
			return justification;
		}

		public void setJustification(String justification) {
			this.justification = justification;
		}

		public LocalDate getDateNeeded() {
			return dateNeeded;
		}

		public void setDateNeeded(LocalDate dateNeeded) {
			this.dateNeeded = dateNeeded;
		}

		public String getDeliveryMode() {
			return deliveryMode;
		}

		public void setDeliveryMode(String deliveryMode) {
			this.deliveryMode = deliveryMode;
		}

		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			if(!status.equals(""))
				this.status = status;
		}

		public double getTotal() {
			return total;
		}

		public void setTotal(double total) {
			this.total = total;
		}

		public String getSubmittedDate() {
			return submittedDate;
		}

		public void setSubmittedDate(String submittedDate) {
			if(!submittedDate.equals(""))
				this.submittedDate = submittedDate;
		}

		public String getReasonForRejection() {
			return reasonForRejection;
		}

		public void setReasonForRejection(String reasonForRejection) {
			this.reasonForRejection = reasonForRejection;
		}
		
		
		
		

}
