package application;

public class Contacts_Values {
String contactfirstname,contactlastname,day,healthyfirstname,healthylastname,health;

public String getHealth() {
	return health;
}

public void setHealth(String health) {
	this.health = health;
}

public Contacts_Values(String contactfirstname, String contactlastname, String day, String health) {
	this.contactfirstname=contactfirstname;
	this.contactlastname=contactlastname;
	this.day=day;
	this.health=health;
	
}

public String getContactfirstname() {
	return contactfirstname;
}

public void setContactfirstname(String contactfirstname) {
	this.contactfirstname = contactfirstname;
}

public String getContactlastname() {
	return contactlastname;
}

public void setContactlastname(String contactlastname) {
	this.contactlastname = contactlastname;
}

public String getDay() {
	return day;
}

public void setDay(String day) {
	this.day = day;
}

public String getHealthyfirstname() {
	return healthyfirstname;
}

public void setHealthyfirstname(String healthyfirstname) {
	this.healthyfirstname = healthyfirstname;
}

public String getHealthylastname() {
	return healthylastname;
}

public void setHealthylastname(String healthylastname) {
	this.healthylastname = healthylastname;
}
}
