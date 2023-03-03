public class Employee{
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

public Employee(String name, String surname ,String gender,
                String birthdate, String adress, String district ,
                String city,String phone, String job, int salary) {

    this.employeeName = name;
    this.employeSurname = surname;
    this.employeeGender = gender;
    this.employeeBirthdate = birthdate;
    this.employeeAdress = adress;
    this.district = district;
    this.city = city;
    this.employeePhone = phone;
    this.job = job;
    this.salary = salary;

}



}
