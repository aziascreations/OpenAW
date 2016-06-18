package com.azias.awbe;

import com.google.gson.Gson;

public class App {
    public static void main(String[] args) {
        Gson gson = new Gson();
        System.out.println(gson.toJson("Hello World!"));
    }
}
