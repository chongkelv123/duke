package duke.task;

import duke.exception.MissingDescriptionException;
public class Deadline extends Task {
    protected String by;

    public Deadline(String description)  {
        super(description);
        setAbbreviation();        
        increaseNumberOfTaskByOne();
    }

    public Deadline(boolean isDone, String description, String by){
        super(description);
        setAbbreviation();
        setDone(isDone);
        this.by = by;
    }

    public void execute()throws MissingDescriptionException{
        pullDeadlineDate(dukeDescription);
        response();
    }

    public void setAbbreviation() {
        abbreviation = 'D';
    }

    public  void pullDeadlineDate (String input) throws MissingDescriptionException {
        final String BY = "/by";
        int len = BY.length();
        int pos = -1;

        pos = input.indexOf(BY);

        if(input.isEmpty()){
            String message = "OOPS!!! The \"description\" of a \"deadline\" cannot be empty :-(";
            throw new MissingDescriptionException(message);
        } else if(pos == -1){
            String message = "OOPS!!! The \"/by\" of a \"deadline\" cannot be empty :-(";
            throw new MissingDescriptionException(message);
        }

        by = input.substring(pos + len).trim();
        dukeDescription = input.substring(0,pos).trim();
    }
    @Override
    public String toString() {
        return String.format("[%c][%s] %s (by: %s)",abbreviation,getStatusIcon(),dukeDescription, by);
    }
    public String getBy() {
        return by;
    }
}
