package pl.mzlab.broker.sharedmodel;

import java.util.UUID;

public class Event {

    private UUID id;
    private String info;
    private EventType eventType;

    public Event(EventType eventType, String info) {
        this.id = UUID.randomUUID();
        this.eventType = eventType;
        this.info = info;
    }

    public UUID id() {
        return id;
    }

    public String info() {
        return info;
    }
}


