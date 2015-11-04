package models;

/**
 * Created by Kevin on 30-09-2015.
 */
public class SensorData {
    private long timestamp;
    private float[] data = {0,0,0};

    public SensorData(float[] data, long timestamp){
        this.data[0] = data[0];
        this.data[1] = data[1];
        this.data[2] = data[2];

        this.setTimestamp(timestamp);
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public float[] getData() {
        return data;
    }

    public void setData(float[] data) {
        this.data = data;
    }
}
