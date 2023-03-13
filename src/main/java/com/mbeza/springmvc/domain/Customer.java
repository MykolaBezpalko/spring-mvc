package com.mbeza.springmvc.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Version;
import java.util.Objects;

@Entity
public class Customer implements DomainObject{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Version
    private Integer version;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String addressLineOne;
    private String addressLineTwo;
    private String city;
    private String zipCode;

    @OneToOne
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddressLineOne() {
        return addressLineOne;
    }

    public void setAddressLineOne(String addressLineOne) {
        this.addressLineOne = addressLineOne;
    }

    public String getAddressLineTwo() {
        return addressLineTwo;
    }

    public void setAddressLineTwo(String addressLineTwo) {
        this.addressLineTwo = addressLineTwo;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public static CustomerBuilder newBuilder() {
        return new Customer().new CustomerBuilder();
    }
    @Override
    public boolean equals(Object obj){
        if(obj == null || obj.getClass() != this.getClass())
            return false;

        Customer comparingCustomer = (Customer) obj;

        return  Objects.equals(this.getFirstName(), comparingCustomer.getFirstName())
                && Objects.equals(this.getLastName(), comparingCustomer.getLastName())
                && Objects.equals(this.getEmail(), comparingCustomer.getEmail())
                && Objects.equals(this.getPhoneNumber(), comparingCustomer.getPhoneNumber());
    }

    public class CustomerBuilder {
        private CustomerBuilder() {

        }


        public CustomerBuilder setId(Integer id) {
            Customer.this.id = id;

            return this;
        }

        public CustomerBuilder setFirstName(String firstName) {
            Customer.this.firstName = firstName;

            return this;
        }

        public CustomerBuilder setLastName(String lastName) {
            Customer.this.lastName = lastName;

            return this;
        }

        public CustomerBuilder setEmail(String email) {
            Customer.this.email = email;

            return this;
        }

        public CustomerBuilder setPhoneNumber(String phoneNumber) {
            Customer.this.phoneNumber = phoneNumber;

            return this;
        }

        public CustomerBuilder setAddressLineOne(String addressLineOne) {
            Customer.this.addressLineOne = addressLineOne;

            return this;
        }

        public CustomerBuilder setAddressLineTwo(String addressLineTwo) {
            Customer.this.addressLineTwo = addressLineTwo;

            return this;
        }

        public CustomerBuilder setCity(String city) {
            Customer.this.city = city;

            return this;
        }

        public CustomerBuilder setZipCode(String zipCode) {
            Customer.this.zipCode = zipCode;

            return this;
        }

        public Customer build() {
            return Customer.this;
        }
    }
}