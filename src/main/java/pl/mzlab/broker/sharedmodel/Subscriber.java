package pl.mzlab.broker.sharedmodel;

public class Subscriber {

    private String identity;
    private String address;
    private String subject;

    public Subscriber(String identity, String address, String subject) {
        this.identity = identity;
        this.address = address;
        this.subject = subject;
    }

    public boolean isValid() {
        if(identityValidation() &&
            addressValidation() && subjectValidation()){
            return true;
        }
        return false;
    }

    private boolean identityValidation() {
        return identity != null && !identity.isBlank();
    }

    private boolean addressValidation() {
        return address != null && !address.isBlank();
    }

    private boolean subjectValidation() {
        return subject != null && !subject.isBlank();
    }

    public String address() {
        return address;
    }

    public String subject() {
        return subject;
    }

    public String identity() {
        return identity;
    }

    @Override
    public String toString() {
        return "Subscriber{" +
                "identity='" + identity + '\'' +
                ", address='" + address + '\'' +
                ", subject='" + subject + '\'' +
                '}';
    }
}
