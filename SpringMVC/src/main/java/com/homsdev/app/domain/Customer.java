package com.homsdev.app.domain;

public class Customer {
	private String customerID;
	private String name;
	private String address;
	private long noOfOrders;

	public Customer() {
		super();
	}

	public String getCustomerID() {
		return customerID;
	}

	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public long getNoOfOrders() {
		return noOfOrders;
	}

	public void setNoOfOrders(long noOfOrders) {
		this.noOfOrders = noOfOrders;
	}
	
	@Override
    public boolean equals(Object obj) {
       if (this == obj)
          return true;
       if (obj == null)
          return false;
       if (getClass() != obj.getClass())
          return false;
       Customer other = (Customer) obj;
       if (customerID == null) {
          if (other.customerID != null)
             return false;
       } else if (!customerID.equals(other.customerID))
          return false;
       return true;
	}
	
	@Override
    public int hashCode() {
       final int prime = 31;
       int result = 1;
       result = prime * result
             + ((customerID == null) ? 0 :
            	 customerID.hashCode());
       return result;
    }

}
