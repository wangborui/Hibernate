package contact_management;

public enum  PhoneType {
    WORK("WORK"),
    HOME("HOME"),
    CUSTOM("CUSTOM");

    private String type;

    private PhoneType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
