package org.example.data;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DataGenerator {
    public void generateFiles() {
        try {
            final String SOURCE = "src\\main\\java\\data\\";
            final int AMOUNT_OF_FILES = 100;
            final int NUMBER_INCREASING = 250;
            final int BOUND = 100;

            Random random = new Random();
            for (int i = 1; i <= AMOUNT_OF_FILES; i++) {
                List<String> list = new ArrayList<>();
                int num = (int) Math.sqrt(NUMBER_INCREASING * i);
                for (int j = 0; j < (Math.pow(num, 2) - num) / 2; j++) {
                    list.add("" + random.nextInt(BOUND));
                }
                String string = SOURCE + "TestFile" + i + ".txt";
                Files.write(Paths.get(string), list);
            }
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }
    }
}

