import java.time.LocalDateTime;

public class OrganizationDets extends Details{
    private final String organizationAddress;

    public OrganizationDets(String fullName, String number, String organizationAddress, LocalDateTime created, LocalDateTime lastEdited) {
        super(fullName, number, created, lastEdited);
        this.organizationAddress = organizationAddress;
    }

    public String getOrganizationAddress() {
        return organizationAddress;
    }

    public String getOrganizationName() {
        return super.fullName;
    }

    public String getOrganizationNumber() {
        return super.number;
    }
    
    public LocalDateTime getCreated(){return super.created;}

    @Override
    public Details edit() {
        OrgDetailsBuilder builder = new OrgDetailsBuilder();
        builder.edit(this);
        return builder.getDetails();
    }

    @Override
    public String getInfo() {
        return "Organization name: " + super.fullName + "\n" +
                "Address: " + organizationAddress + "\n" +
                "Number: " + super.number + "\n" +
                "Time created: " + super.created + "\n" +
                "Time last edit: " + super.lastEdited;
    }
}
