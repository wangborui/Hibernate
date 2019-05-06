package source_class;

public enum PhoneType {
    WORK("WORK"), HOME("HOME"), PARENTS("PARENTS"), CUSTOM("CUSTOM");

    private String type;

    PhoneType(String type) {
        this.type = type;
    }

    public String getType(){
        return type;
    }
}
