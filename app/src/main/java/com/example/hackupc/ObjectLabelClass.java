package com.example.hackupc;


public class ObjectLabelClass {

    public String Id;
    public String Name;
    public float Position_X;
    public float Position_Y;

    public ObjectLabelClass(int id, String name, float position_X, float position_Y) {
        this.Id = name+id;
        this.Name = name;
        this.Position_X = position_X;
        this.Position_Y = position_Y;
    }

}
