public class Customer {
    int customerid;
    String customerName;
    String customerSurname;
    String customerGender;
    String customerBirthdate;
    String customerAdress;
    String district;
    String city;
    String customerPhone;
    Date birthdate;

    public Customer(String name, String surname, String gender,
                    String birthdateString, String adress, String district,
                    String city, String phone, Date date) {
        customerName = name;
        customerSurname = surname;
        customerGender = gender;
        customerBirthdate = birthdateString;
        customerAdress = adress;
        this.district = district;
        this.city = city;
        customerPhone = phone;
        birthdate = date;


    }


}
