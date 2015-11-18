package wody.wody;

/**
 * Created by Falch on 13/11/15.
 */
public class TimelineObject {
    private String name;
    private long time;

    public TimelineObject( String name, long time){
        this.name = name;
        this.time = time;
    }


    public String getName() {
        return name;
    }

    public long getTime() {
        return time;
    }
}
