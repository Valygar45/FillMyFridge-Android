package fr.utt.if26.fillmyfridge.Objects;

/**
 * Created by alex2 on 15/11/2017.
 */

public class Tag {
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
}
