public class BuilderFactory {

    public DetailsBuilder getBuilder(String type){
        if ("person".equals(type)){
            return new PersonDetailsBuilder();
        }else {
            return new OrgDetailsBuilder();
        }
    }
}
