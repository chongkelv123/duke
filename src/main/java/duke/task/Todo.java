package duke.task;

import duke.exception.InvalidArgumentException;

public class Todo extends Task {
    public Todo(String description)  {
        super(description);
        setAbbreviation();
    }

    public Todo(boolean isDone, String description){
        super(description);
        setAbbreviation();
        setDone(isDone);
    }

    public void execute()throws InvalidArgumentException {
        response();
    }

    public void setAbbreviation() {
        abbreviation = 'T';
    }

    public Todo(){
        isDone = false;
    }

    @Override
    public String toString() {
        return String.format("[%c][%s] %s",abbreviation,getStatusIcon(),dukeDescription);
    }

}
