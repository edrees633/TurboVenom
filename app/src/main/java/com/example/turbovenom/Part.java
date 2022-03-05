package com.example.turbovenom;

import java.io.Serializable;

enum psCategory
{
    Turbo, Supercharger, Intake, Exhaust
}

public class Part implements Serializable {
    private int name;
    private int description;
    private int history;
    private int finalInfo;
    private String photo;


    public Part(int parseInt1, int i, int parseInt, int anInt, int i1, psCategory psCategory) {
    }



    public int getId() {
        return name;
    }

    public Part(int name, int description, int history, int finalInfo, int id, int brand, int size, int modelYear, String photo) {
        this.name = name;
        this.description = description;
        this.history = history;
        this.finalInfo = finalInfo;

        this.photo = photo;
    }



    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public int getName() {
        return name;
    }

    public void setName(int name) {
        this.name = name;
    }

    public int getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "Part{" +
                "name=" + name +
                ", description=" + description +
                ", history=" + history +
                ", finalInfo=" + finalInfo +

                ", photo='" + photo + '\'' +
                '}';
    }

    public void setDiscreption(int discreption) {
        this.description = discreption;
    }

    public int getHistory() {
        return history;
    }

    public void setHistory(int history) {
        this.history = history;
    }

    public int getFinalInfo() {
        return finalInfo;
    }

    public void setFinalInfo(int finalInfo) {
        this.finalInfo = finalInfo;
    }

}
