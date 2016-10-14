package com.easy.make.tenantmaker.core.tenant.data.model;

public class Tenant {

    public static final String OWNER_ID = "ownId";
    public static final String FIRST_NAME = "firstName";

    private String id;
    private BasicInfo basicInfo;
    private PgFlatInfo pgFlatInfo;
    private PaymentInfo paymentInfo;

    public static class BasicInfo {

        private String firstName;
        private String lastName;
        private String mobile;
        private String email;
        private String address;

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

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }
    }

    public static class PgFlatInfo {
        public String pgOrFlatId;
        public String pgOrFlatNum;

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
    }

    public static class PaymentInfo {
        private String rentDate;

        public String getRentDate() {
            return rentDate;
        }

        public void setRentDate(String rentDate) {
            this.rentDate = rentDate;
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BasicInfo getBasicInfo() {
        return basicInfo;
    }

    public void setBasicInfo(BasicInfo basicInfo) {
        this.basicInfo = basicInfo;
    }

    public PgFlatInfo getPgFlatInfo() {
        return pgFlatInfo;
    }

    public void setPgFlatInfo(PgFlatInfo pgFlatInfo) {
        this.pgFlatInfo = pgFlatInfo;
    }

    public PaymentInfo getPaymentInfo() {
        return paymentInfo;
    }

    public void setPaymentInfo(PaymentInfo paymentInfo) {
        this.paymentInfo = paymentInfo;
    }
}
