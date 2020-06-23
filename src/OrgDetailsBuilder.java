import java.time.LocalDateTime;
import java.util.Scanner;
import java.util.regex.Pattern;

public class OrgDetailsBuilder extends DetailsBuilder implements OrgBuilder {
    private String name;
    private String address;
    private String number;
    private LocalDateTime created;
    private LocalDateTime lastEdited;

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    @Override
    public void setEdited(LocalDateTime edited) {
        this.lastEdited = edited;
    }

    public void build(String name, String address, String number, LocalDateTime created, LocalDateTime lastEdited){
        setName(name);
        setAddress(address);
        setNumber(number);
        setCreated(created);
        setEdited(lastEdited);
    }
    private boolean checkValidity(String number){
        Pattern regex = Pattern.compile("([+]?[\\w\\d]+)");
        Pattern regex1 = Pattern.compile("[+]*?[(]?[\\w\\d]+[)]?+([-\\s]?[\\w\\d]{2,})+");
        Pattern regex2 = Pattern.compile("[+]*?[\\w\\d]+([-\\s]?[(]?[\\w\\d]{2,}[)]?)+");
        Pattern regex3 = Pattern.compile("([+]?[([\\w\\d])]+)");
        return regex.matcher(number).matches() || regex1.matcher(number).matches() || regex2.matcher(number).matches() || regex3.matcher(number).matches();
    }
    

    @Override
    void edit(Details details) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Select a field (name, address, number): > ");
        String field = scanner.nextLine();
        executeUpdate(field, details);
    }

    private void executeUpdate(String field, Details details) {
        Scanner scanner = new Scanner(System.in);
        String name = ((OrganizationDets)details).getOrganizationName();
        String address = ((OrganizationDets) details).getOrganizationAddress();
        String number = ((OrganizationDets) details).getOrganizationNumber();
        LocalDateTime created = ((OrganizationDets) details).getCreated();
        if ("name".equals(field)){
            System.out.println("Enter name: ");
            String edited = scanner.nextLine();
            LocalDateTime now = LocalDateTime.now();
            build(edited, address, number,created,now);
        }else if ("address".equals(field)){
            System.out.println("Enter address: > ");
            String edited = scanner.nextLine();
            LocalDateTime now = LocalDateTime.now();
            build(name, edited, number, created, now);
        }else if ("number".equals(field)){
            System.out.println("Enter number: > ");
            String edited  = scanner.nextLine();
            LocalDateTime now = LocalDateTime.now();
            boolean valid = checkValidity(edited);
            if (valid){
                build(name,address,edited,created,now);
            }else {
                System.out.println("Wrong number format!");
                build(name,address,"",created,now);
            }
        }
    }

    @Override
    public void execute(){
        System.out.print("Enter the organization name: > ");
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();
        System.out.print("Enter the address: > ");
        String address = scanner.nextLine();
        System.out.print("Enter the number: > ");
        String number = scanner.nextLine();
        LocalDateTime created = LocalDateTime.now();
        LocalDateTime edited = LocalDateTime.now();

        boolean valid = checkValidity(number);
        if (valid){
            build(name, address, number, created,edited);
        }else {
            System.out.println("Wrong number format!");
            build(name, address,"[no number]",created,edited );
        }
    }

    public OrganizationDets getDetails(){
        return new OrganizationDets(name,number,address,created,lastEdited);
    }
}

interface OrgBuilder{
    void setName(String name);
    void setAddress(String address);
    void setNumber(String number);
    void setCreated(LocalDateTime created);
    void setEdited(LocalDateTime edited);
}
