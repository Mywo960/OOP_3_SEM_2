package com.example.oop_3_sem_2;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.util.stream.Collectors;

@WebServlet(name = "LaptopServlet", value = "/laptop")
public class LaptopServlet extends HttpServlet {
    // Путь к JSON
    private static final String FILE_PATH = "C:\\Users\\Кирилл\\IdeaProjects\\OOP_3_SEM_2\\src\\main\\java\\com\\example\\oop_3_sem_2\\laptop.json";

    // Добавление объекта к JSON
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        String jsonRequest = request.getReader().lines().collect(Collectors.joining());
        JSONObject newLaptopJson = new JSONObject(jsonRequest);

        JSONArray laptopsJsonArray = new JSONArray(readJSON());
        laptopsJsonArray.put(newLaptopJson);

        writeJSON(laptopsJsonArray.toString());

        response.getWriter().write(laptopsJsonArray.toString());
    }

    // передача клиенту JSON
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        String laptopsJson = readJSON();
        response.getWriter().write(laptopsJson);
    }

    // Чтение JSON
    private String readJSON() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            return reader.lines().collect(Collectors.joining());
        } catch (IOException e) {
            e.printStackTrace();
            return "[]";
        }
    }
    // Запись в JSON
    private void writeJSON(String laptopsJson) {
        try (FileWriter fileWriter = new FileWriter(FILE_PATH)) {
            fileWriter.write(laptopsJson);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
