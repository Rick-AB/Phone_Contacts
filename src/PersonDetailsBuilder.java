import java.time.LocalDateTime;
import java.util.Scanner;
import java.util.regex.Pattern;

public class PersonDetailsBuilder extends DetailsBuilder implements PersonBuilder {
    private String firstName;
    private String lastName;
    private String number;
    private String birthDate;
    private String gender;
    private LocalDateTime created;
    private LocalDateTime edited;


    @Override
    public void setFirstName(String name) {
        this.firstName = name;
    }

    @Override
    public void setLastName(String name) {
        this.lastName = name;
    }

    @Override
    public void setNumber(String number) {
        boolean valid = checkNumberValidity(number);
        if (valid){
            this.number = number;
        }else {
            this.number = "[no number]";
        }

    }

    @Override
    public void setBirthDate(String birthDate) {
        boolean valid = checkBirthDate(birthDate);
        if (valid){
            this.birthDate = birthDate;
        }else {
            this.birthDate = "[no data]";
        }

    }

    @Override
    public void setGender(String gender) {
        boolean valid = checkGenderValidity(gender);
        if (valid){
            this.gender = gender;
        }else {
            this.gender = "[no data]";
        }
    }

    @Override
    public void setCreated(LocalDateTime created) {
        this.created  = created;
    }

    @Override
    public void setEdited(LocalDateTime edited) {
        this.edited = edited;
    }
    

    public void build(String firstName, String lastName, String birthDate, String gender, String number, LocalDateTime created, LocalDateTime edited){
        setFirstName(firstName);
        setLastName(lastName);
        setBirthDate(birthDate);
        setGender(gender);
        setNumber(number);
        setCreated(created);
        setEdited(edited);
    }
    
    public boolean checkNumberValidity(String number){
        Pattern regex = Pattern.compile("([+]?[\\w\\d]+)");
        Pattern regex1 = Pattern.compile("[+]*?[(]?[\\w\\d]+[)]?+([-\\s]?[\\w\\d]{2,})+");
        Pattern regex2 = Pattern.compile("[+]*?[\\w\\d]+([-\\s]?[(]?[\\w\\d]{2,}[)]?)+");
        Pattern regex3 = Pattern.compile("([+]?[([\\w\\d])]+)");
        return regex.matcher(number).matches() || regex1.matcher(number).matches() || regex2.matcher(number).matches() || regex3.matcher(number).matches();
    }
    
    public boolean checkGenderValidity(String gender){
        return gender.equals("M") || gender.equals("F") || gender.equals("m") || gender.equals("f");
    }
    
    public boolean checkBirthDate(String birthDate){
        return birthDate.matches("[\\d]{4}-[\\d]{1,2}-[\\d]{1,2}");
    }

    public PersonDets getDetails(){
        return new PersonDets(this.firstName , this.lastName,this.number,this.birthDate, this.gender, created, edited);
    }
    
    @Override
    void edit(Details details) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Select a field (name, surname, birth, gender, number): > ");
        String field = scanner.nextLine();
        executeUpdate(field, details);
    }
    
    private void executeUpdate(String field, Details details) {
        Scanner scanner = new Scanner(System.in);
        String name = ((PersonDets) details).getFirstName();
        String lastName = ((PersonDets) details).getLastName();
        String birthDate = ((PersonDets) details).getBirthDate();
        String gender = ((PersonDets) details).getGender();
        String number = ((PersonDets) details).getNumber();
        LocalDateTime created = details.created;
        if ("name".equals(field)){
            System.out.print("Enter name: > ");
            String edited = scanner.nextLine();
            LocalDateTime now = LocalDateTime.now();
            build(edited,lastName,birthDate,gender,number,created,now);
        }else if ("surname".equals(field)){
            System.out.println("Enter surname: > ");
            String edited = scanner.nextLine();
            LocalDateTime now = LocalDateTime.now();
            build(name,edited,birthDate,gender,number,created,now);
        }else if ("birth".equals(field)){
            System.out.println("Enter birth date: > ");
            String edited = scanner.nextLine();
            LocalDateTime now = LocalDateTime.now();
            build(name,lastName,edited,gender,number,created,now);
        }else if ("gender".equals(field)){
            System.out.println("Enter gender (M, F): > ");
            String edited = scanner.nextLine();
            LocalDateTime now = LocalDateTime.now();
            build(name,lastName,birthDate,edited,number,created,now);
        }else if ("number".equals(field)){
            System.out.print("Enter number: > ");
            String edited = scanner.nextLine();
            LocalDateTime now = LocalDateTime.now();
            boolean valid = checkNumberValidity(edited);
            if (valid) {
                build(name,lastName,birthDate,gender,edited,created,now);
            } else {
                System.out.println("Wrong number format!");
                build(name,lastName,birthDate,gender,"",created,now);
            }
        }
    }
    
    @Override
    public void execute(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the name of the person: > ");
        String firstName = scanner.nextLine();
        System.out.print("Enter the surname of the person: > ");
        String lastName = scanner.nextLine();
        System.out.println("Enter the birth date: > ");
        String birthDate = scanner.nextLine();
        System.out.println("Enter the gender (M, F): > ");
        String gender = scanner.nextLine();
        System.out.print("Enter the number: > ");
        String number = scanner.nextLine();
        LocalDateTime created = LocalDateTime.now();
        LocalDateTime edited = LocalDateTime.now();

        boolean valid = checkNumberValidity(number);
        if (valid){
            build(firstName, lastName, birthDate, gender,number,created,edited);
        }else {
            System.out.println("Wrong number format!");
            build(firstName,lastName,birthDate,gender,"[no number]",created, edited);
        }
    }
}

interface PersonBuilder {
    void setFirstName(String name);
    void setLastName(String name);
    void setNumber(String number);
    void setBirthDate(String birthDate);
    void setGender(String gender);
    void setCreated(LocalDateTime created);
    void setEdited(LocalDateTime edited);
}
