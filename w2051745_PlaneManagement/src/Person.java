public class Person{
    private String name;
    private String surname;
    private String email;

    //using the constructor as a setter

    public Person(String name ,String surname,String email) {
        this.name = name;
        this.surname = surname;
        this.email = email;

    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    //getters

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }
}