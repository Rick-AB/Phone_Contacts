import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Contacts implements Serializable {
    private final List<Details> list;
    final Scanner scanner;
    public Contacts(){
        scanner = new Scanner(System.in);
        list = new ArrayList<>();
        menu();
    }
    private void menu(){
        String action;
        do {
            System.out.print("[menu] Enter action (add, list, search, count, exit): > ");
            action = scanner.nextLine();
            switch (action) {
                case "add":
                    addContact();
                    break;
                case "search":
                    search();
                    break;
                case "list":
                    printList();
                    break;
                case "count":
                    count();
                    break;
                case "exit":
                    exit();
                    break;
            }
        }while (true);



    }
    private void addContact(){
        System.out.println("Enter the type (person, organization): >");
        String type = scanner.nextLine();
        DetailsBuilder builder = new BuilderFactory().getBuilder(type);
        builder.execute();
        list.add(builder.getDetails());
        System.out.println("The record added.");
        printEmptyLine();
    }

//    private boolean checkValidity(String number) {
//        Pattern regex = Pattern.compile("([+]?[\\w\\d]+)");
//        Pattern regex1 = Pattern.compile("[+]*?[(]?[\\w\\d]+[)]?+([-\\s]?[\\w\\d]{2,})+");
//        Pattern regex2 = Pattern.compile("[+]*?[\\w\\d]+([-\\s]?[(]?[\\w\\d]{2,}[)]?)+");
//        Pattern regex3 = Pattern.compile("([+]?[([\\w\\d])]+)");
//        return regex.matcher(number).matches() || regex1.matcher(number).matches() || regex2.matcher(number).matches() || regex3.matcher(number).matches();
//    }

    private void modifyContact(int index){
        do {
            System.out.print("[record] Enter action (edit, delete, menu): > ");
            String action = scanner.nextLine();
            switch (action) {
                case "edit":
                    Details edited = list.get(index).edit();
                    list.set(index, edited);
                    System.out.println("Saved");
                    System.out.println(edited.getInfo());
                    printEmptyLine();
                    break;
                case "delete":
                    list.remove(index);
                    break;
                case "menu":
                    menu();
                    break;
            }
        }while (true);
    }
    
    private void count(){
        System.out.println("The Phone Book has " + this.list.size() + " records.");
        printEmptyLine();
    }
    
//    private void removeContact(){
//        if (list.isEmpty()){
//            System.out.println("No records to remove!");
//        }else {
//            printList();
//            System.out.print("Select a record: > ");
//            int position = Integer.parseInt(scanner.nextLine());
//            this.list.remove(list.get(position - 1));
//            System.out.println("The record removed!");
//        }
//        printEmptyLine();
//    }
    private void printList(){
        if (list.isEmpty()){
            System.out.println("No record to list");
        }
        for (int i = 0; i < list.size(); i++){
            System.out.println((i + 1) + ". " + list.get(i));
        }
        printEmptyLine();
        String action;
        do {
            
            System.out.println("[list] Enter action ([number], back):");
            action = scanner.nextLine();
    
            if ("back".equals(action)) {
                menu();
            } else {
                try {
                    int position = Integer.parseInt(action);
                    System.out.println(list.get(position - 1).getInfo());
                    printEmptyLine();
                    modifyContact(list.indexOf(list.get(position - 1)));
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input!");
                }
            }
        }while (true);
    }
    private void exit(){
        System.exit(0);
    }
    private void printEmptyLine(){
        System.out.println();
    }
//    private void showInfo(){
//        printList();
//        System.out.print("Enter index to show info: > ");
//        int position = Integer.parseInt(scanner.nextLine());
//        Details details = list.get(position - 1);
//        if (details.getClass() == PersonDets.class){
//            System.out.println(details.getInfo());
//        }else if (details.getClass() == OrganizationDets.class){
//            System.out.println(details.getInfo());
//        }
//        printEmptyLine();
//    }
    private void search(){
        System.out.print("Enter search query: > ");
        List<Details> result = new ArrayList<>();
        String query = scanner.nextLine();
        Pattern pattern = Pattern.compile(query, Pattern.CASE_INSENSITIVE);
        if (!list.isEmpty()){
            for (Details d : list){
                Matcher matcher = pattern.matcher(d.getInfo());
                if (matcher.find()){
                    result.add(d);
                }
            }
        }

        if (result.size() != 0){
            System.out.println("Found " + result.size() + " results:");
            for (int i = 0; i<result.size(); i++){
                System.out.println(i + 1 + ". " + result.get(i).toString());
            }
        }else {
            System.out.println("No record found!");
        }
        
        printEmptyLine();

        String action;
        do {
            System.out.println("[search] Enter action ([number], back, again):");
            action = scanner.nextLine();

            switch (action){
                case "back":
                    menu();
                    break;
                case "again":
                    search();
                    break;
                default:{
                    try {
                        int position = Integer.parseInt(action);
                        System.out.println(result.get(position - 1).getInfo());
                        printEmptyLine();
                        modifyContact(list.indexOf(result.get(position - 1)));
                    }catch (NumberFormatException e){
                        System.out.println("Invalid input");
                    }
                    break;
                }

            }
        }while (true);
    }
    
    private void writeObject(ObjectOutputStream oos) throws Exception {
        oos.defaultWriteObject();
    }
    
    private void readObject(ObjectInputStream ois) throws Exception {
        ois.defaultReadObject();
    }

}
