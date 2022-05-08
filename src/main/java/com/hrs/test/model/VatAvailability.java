package com.hrs.test.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="vat_availability")
public class VatAvailability {

	@Id
	@Column(name = "id")
	protected String id;
	
	@Column(name = "country_code") 
	private String countryCode;
	
	@Column(name = "vat_number") 
    private String vatNumber;
	
	@Column(name = "business_name") 
    private String businessName;
	
	@Column(name = "business_address") 
    private String businessAddress;
	
	@Column(name = "business_building") 
    private String businessBuilding;
	
	@Column(name = "business_street_number") 
    private String businessStreetNumber;
	
	@Column(name = "business_street") 
    private String businessStreet;
	@Column(name = "business_city") 
    private String businessCity;
	@Column(name = "business_state_or_province") 
    private String businessStateOrProvince;
	@Column(name = "business_postal_code") 
    private String businessPostalCode;
	@Column(name = "business_country") 
    private String businessCountry;
	
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getCountryCode() {
        return countryCode;
    }
    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }
    public String getVatNumber() {
        return vatNumber;
    }
    public void setVatNumber(String vatNumber) {
        this.vatNumber = vatNumber;
    }
    public String getBusinessName() {
        return businessName;
    }
    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }
    public String getBusinessAddress() {
        return businessAddress;
    }
    public void setBusinessAddress(String businessAddress) {
        this.businessAddress = businessAddress;
    }
    public String getBusinessBuilding() {
        return businessBuilding;
    }
    public void setBusinessBuilding(String businessBuilding) {
        this.businessBuilding = businessBuilding;
    }
    public String getBusinessStreetNumber() {
        return businessStreetNumber;
    }
    public void setBusinessStreetNumber(String businessStreetNumber) {
        this.businessStreetNumber = businessStreetNumber;
    }
    public String getBusinessStreet() {
        return businessStreet;
    }
    public void setBusinessStreet(String businessStreet) {
        this.businessStreet = businessStreet;
    }
    public String getBusinessCity() {
        return businessCity;
    }
    public void setBusinessCity(String businessCity) {
        this.businessCity = businessCity;
    }
    public String getBusinessStateOrProvince() {
        return businessStateOrProvince;
    }
    public void setBusinessStateOrProvince(String businessStateOrProvince) {
        this.businessStateOrProvince = businessStateOrProvince;
    }
    public String getBusinessPostalCode() {
        return businessPostalCode;
    }
    public void setBusinessPostalCode(String businessPostalCode) {
        this.businessPostalCode = businessPostalCode;
    }
    public String getBusinessCountry() {
        return businessCountry;
    }
    public void setBusinessCountry(String businessCountry) {
        this.businessCountry = businessCountry;
    }
	
}
