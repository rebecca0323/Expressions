package com.example.livewell2020;

public class Emotion {
    private double probability;
    private String emotion;

    public Emotion(double probability, String emotion){
        this.probability = probability;
        this.emotion = emotion;
    }

    public double getProbability() {
        return probability;
    }

    public void setProbability(double probability) {
        this.probability = probability;
    }

    public String getEmotion() {
        return emotion;
    }

    public void setEmotion(String emotion) {
        this.emotion = emotion;
    }
}
