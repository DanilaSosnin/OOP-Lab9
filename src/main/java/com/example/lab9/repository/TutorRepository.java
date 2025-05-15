package com.example.lab9.repository;

import com.example.lab9.model.Tutor;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class TutorRepository {
    private static final String FILE_NAME = "tutors.json";
    private String filePath;
    private Gson gson = new Gson();

    public TutorRepository(String contextPath) {
        this.filePath = contextPath + FILE_NAME;
        File file = new File(filePath);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public List<Tutor> loadTutors() {
        try (Reader reader = new FileReader(filePath)) {
            Type listType = new TypeToken<ArrayList<Tutor>>(){}.getType();
            List<Tutor> tutors = gson.fromJson(reader, listType);
            return tutors != null ? tutors : new ArrayList<>();
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    public void saveTutors(List<Tutor> tutors) {
        try (Writer writer = new FileWriter(filePath)) {
            gson.toJson(tutors, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}