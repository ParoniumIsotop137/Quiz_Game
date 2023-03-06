package com.example.ferenc.quiz_game;

public class Kerdes {

    private Integer id;
    private String kerdes;
    private String valasz_A;
    private String valasz_B;
    private String valasz_C;
    private String valasz_D;

    private String helyesValasz;


    public Kerdes(Integer id, String kerdes, String valasz_A, String valasz_B, String valasz_C, String valasz_D, String helyesValasz) {
        setId(id);
        this.kerdes = kerdes;
        this.valasz_A = valasz_A;
        this.valasz_B = valasz_B;
        this.valasz_C = valasz_C;
        this.valasz_D = valasz_D;
        this.helyesValasz = helyesValasz;
    }

    public void setId(Integer id) {
        if(this.id == null){
            this.id = id;
        }
        else{
            throw new IllegalStateException("Az ID csak egyszer kaphat értéket!");
        }
    }

    public Integer getId() {
        return id;
    }

    public String getKerdes() {
        return kerdes;
    }

    public String getValasz_A() {
        return valasz_A;
    }

    public String getValasz_B() {
        return valasz_B;
    }

    public String getValasz_C() {
        return valasz_C;
    }

    public String getValasz_D() {
        return valasz_D;
    }

    public String getHelyesValasz() {
        return helyesValasz;
    }

    @Override
    public String toString() {
        return "Kérdés: " + this.kerdes  +
                "\nA válasz: " + this.valasz_A  +
                ", B válasz: " + this.valasz_B +
                ", C válasz: " + this.valasz_C  +
                ", D válasz:" + this.valasz_D  +
                ", helyes válasz: " + this.helyesValasz;
    }
}
