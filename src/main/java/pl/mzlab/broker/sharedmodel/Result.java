package pl.mzlab.broker.sharedmodel;

import java.util.LinkedList;
import java.util.List;

public class Result {

    private boolean resultSuccess;
    private List<Event> events = new LinkedList<>();

    private Result(boolean resultSuccess, Event event) {
        this.resultSuccess = resultSuccess;
        this.events.add(event);
    }

    private Result(boolean resultSuccess, List<Event> events) {
        this.resultSuccess = resultSuccess;
        this.events.addAll(events);
    }

    public static Result success(Event event) {
        return new Result(true, event);
    }

    public static Result success(List<Event> events) {
        return new Result(true, events);
    }

    public static Result failure(Event event) {
        return new Result(false, event);
    }

    public static Result failure(List<Event> events) {
        return new Result(false, events);
    }

    public List<Event> events(){
        return events;
    }

    public boolean isSuccess() {
        return resultSuccess;
    }
}
