package pl.mzlab.broker.sharedmodel;

import java.time.LocalDateTime;
import java.util.UUID;

public class Message {

    private UUID id;
    private String subject;
    private String title;
    private LocalDateTime termOfValidity;

    public Message(int validityPeriodInMinutes, String subject, String title) {
        this.id = UUID.randomUUID();
        this.subject = subject;
        this.title = title;
        this.termOfValidity = LocalDateTime.now()
                .plusMinutes(validityPeriodInMinutes);
    }

    public String subject() {
        return subject;
    }

    public boolean isValid(){
        if(termOfValidityValidation() &&
            subjectValidation() && titleValidation()){
            return true;
        }
        return false;
    }

    public UUID id(){
        return id;
    }

    private boolean termOfValidityValidation(){
        return  LocalDateTime.now().isBefore(termOfValidity);
    }

    private boolean subjectValidation(){
        return subject !=null && !subject.isBlank();
    }

    private boolean titleValidation(){
        return title !=null && !title.isBlank();
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", subject='" + subject + '\'' +
                ", title='" + title + '\'' +
                ", termOfValidity=" + termOfValidity +
                '}';
    }
}
