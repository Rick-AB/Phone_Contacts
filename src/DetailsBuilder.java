import java.time.LocalDateTime;

public class DetailsBuilder implements Builder {
    private String fullName;
    private String number = "";


    void execute() {

    }
    void edit(Details details){
    
    }

    @Override
    public void setName(String name) {
        this.fullName = name;
    }

    @Override
    public void setNumber(String number) {
        this.number = number;
    }
    public Details getDetails(){
        return new Details(this.fullName, this.number, LocalDateTime.now(), LocalDateTime.now());
    }
}

interface Builder{
    void setName(String name);
    void setNumber(String number);
}
