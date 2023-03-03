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

    public Customer(String name, String surname, String gender,
                    String birthdate, String adress, String district,
                    String city, String phone) {
        customerName = name;
        customerSurname = surname;
        customerGender = gender;
        customerBirthdate = birthdate;
        customerAdress = adress;
        this.district = district;
        this.city = city;
        customerPhone = phone;

    }


}
