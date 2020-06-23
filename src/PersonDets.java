import java.time.LocalDateTime;

public class PersonDets extends Details{
    private final String birthDate;
    private final String gender;
    private final String firstName;
    private final String lastName;
    public PersonDets(String firstName, String lastName, String number, String birthDate, String gender, LocalDateTime created, LocalDateTime lastEdited) {
        super(firstName + " " + lastName, number, created, lastEdited);
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.gender = gender;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public String getGender() {
        return gender;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
    public String getNumber(){
        return super.number;
    }
    
    @Override
    public Details edit() {
        PersonDetailsBuilder builder = new PersonDetailsBuilder();
        builder.edit(this);
        return builder.getDetails();
    }
    
    @Override
    public String getInfo() {
        return "Name: " + firstName + "\n" +
                "Surname: " + lastName + "\n" +
                "Birth date: " + birthDate + "\n" +
                "Gender: " + gender + "\n" +
                "Number: " + super.number + "\n" +
                "Time created: " + super.created + "\n" +
                "Time last edit: " + super.lastEdited;
    }
}
