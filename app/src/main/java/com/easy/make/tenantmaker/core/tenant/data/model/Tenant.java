package com.easy.make.tenantmaker.core.tenant.data.model;

public class Tenant {

    public static final String OWNER_ID = "ownId";
    public static final String FIRST_NAME = "firstName";

    private String id;
    private String firstName;
    private String lastName;
    private String mobile;
    private String email;
    private String rentDate;
    private String address;
    public String modifiedAt;
    public String ownId;
    public String pgOrFlatId;
    public String pgOrFlatNum;

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

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRentDate() {
        return rentDate;
    }

    public void setRentDate(String rentDate) {
        this.rentDate = rentDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getModifiedAt() {
        return modifiedAt;
    }

    public void setModifiedAt(String modifiedAt) {
        this.modifiedAt = modifiedAt;
    }

    public String getOwnId() {
        return ownId;
    }

    public void setOwnId(String ownId) {
        this.ownId = ownId;
    }

    public String getPgOrFlatId() {
        return pgOrFlatId;
    }

    public void setPgOrFlatId(String pgOrFlatId) {
        this.pgOrFlatId = pgOrFlatId;
    }

    public String getPgOrFlatNum() {
        return pgOrFlatNum;
    }

    public void setPgOrFlatNum(String pgOrFlatNum) {
        this.pgOrFlatNum = pgOrFlatNum;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
