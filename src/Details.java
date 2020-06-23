import java.time.LocalDateTime;

public class Details {
    String fullName;
    String number;
    LocalDateTime created;
    LocalDateTime lastEdited;
    public Details(String fullName, String number, LocalDateTime created, LocalDateTime lastEdited){
        this.fullName = fullName;
        this.number = number;
        this.created = created;
        this.lastEdited = lastEdited;
    }

    @Override
    public String toString() {
        return fullName;
    }
    
    public String getInfo(){
        return "";
   }
   public  Details edit(){
        return null;
   }
   
}
