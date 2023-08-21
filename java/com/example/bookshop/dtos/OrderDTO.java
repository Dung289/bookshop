package com.example.bookshop.dtos;

public class OrderDTO {
	private Long id;
	private String userGmail;
	private String userFullname;
	private String bookTitle;
	private Double totalCost;
	private int quantity;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getUserGmail() {
		return userGmail;
	}
	public void setUserGmail(String userGmail) {
		this.userGmail = userGmail;
	}
	public String getBookTitle() {
		return bookTitle;
	}
	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}
	
	
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	
	public Double getTotalCost() {
		return totalCost;
	}
	public void setTotalCost(Double totalCost) {
		this.totalCost = totalCost;
	}
	
	
	public String getUserFullname() {
		return userFullname;
	}
	public void setUserFullname(String userFullname) {
		this.userFullname = userFullname;
	}
	public OrderDTO() {
		
	}
	public OrderDTO(String userGmail, String bookTitle, int quantity, Double totalCost, String userFullname) {
		super();
		this.userGmail = userGmail;
		this.bookTitle = bookTitle;
		this.quantity = quantity;
		this.totalCost = totalCost;
		this.userFullname = userFullname;
	}
	
	
	
}
