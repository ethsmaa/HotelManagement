public class Employee {
    int employeeid;
    String job;
    String employeeName;
    String employeSurname;
    String employeeBirthdate;
    String employeeGender;
    String employeeAdress;
    String district;
    String city;
    String employeePhone;

    int salary;

    public Employee(String name, String surname, String gender,
                    String birthdate, String adress, String district,
                    String city, String phone, String job, int salary) {

        employeeName = name;
        employeSurname = surname;
        employeeGender = gender;
        employeeBirthdate = birthdate;
        employeeAdress = adress;
        this.district = district;
        this.city = city;
        employeePhone = phone;
        this.job = job;
        this.salary = salary;

    }


}
