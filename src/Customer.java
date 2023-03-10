public class Customer {
    int customerid;
    String customerName;
    String customerSurname;
    String customerGender;
    String customerBirthdate;
    String customerPhone;
    Date birthdate;
    Address address;

    public Customer(String name, String surname, String gender,
                    String birthdateString, String phone, Date date, Address address) {
        customerName = name;
        customerSurname = surname;
        customerGender = gender;
        customerBirthdate = birthdateString;
        customerPhone = phone;
        birthdate = date;
        this.address = address;
    }


}
