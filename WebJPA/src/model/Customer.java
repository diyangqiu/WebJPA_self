package model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the CUSTOMERS database table.
 * 
 */
@Entity
@Table(name="CUSTOMERS",schema = "GUITARSHOP3")
@NamedQuery(name="Customer.findAll", query="SELECT c FROM Customer c")
public class Customer implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="CUSTOMER_ID")
	private long customerId;

	@Column(name="BILLING_ADDRESS_ID")
	private BigDecimal billingAddressId;

	@Column(name="EMAIL_ADDRESS")
	private String emailAddress;

	@Column(name="FIRST_NAME")
	private String firstName;

	@Column(name="LAST_NAME")
	private String lastName;

	private String password;

	@Column(name="SHIPPING_ADDRESS_ID")
	private BigDecimal shippingAddressId;

	public Customer() {
	}

	public long getCustomerId() {
		return this.customerId;
	}

	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}

	public BigDecimal getBillingAddressId() {
		return this.billingAddressId;
	}

	public void setBillingAddressId(BigDecimal billingAddressId) {
		this.billingAddressId = billingAddressId;
	}

	public String getEmailAddress() {
		return this.emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public BigDecimal getShippingAddressId() {
		return this.shippingAddressId;
	}

	public void setShippingAddressId(BigDecimal shippingAddressId) {
		this.shippingAddressId = shippingAddressId;
	}

}