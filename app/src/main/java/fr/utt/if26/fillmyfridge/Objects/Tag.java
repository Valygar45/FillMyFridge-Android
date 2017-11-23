package fr.utt.if26.fillmyfridge.Objects;

import java.io.Serializable;

/**
 * Created by alex2 on 15/11/2017.
 */

public class Tag implements Serializable {
    private int id;
    private String label;

    public Tag(int id, String label) {
        this.id = id;
        this.label = label;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return "Tag{" +
                "id=" + id +
                ", label='" + label + '\'' +
                '}';
    }
}
