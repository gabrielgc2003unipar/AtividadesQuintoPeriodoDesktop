package org.example;

import java.io.*;

public class Main {
    public static void main(String[] args) {

        String path = "C:\\Users\\gabri\\Desktop\\PastaAula\\tabuada.txt";

        File file = new File(path);

        if (!file.exists()) {
            try {
                file.getParentFile().mkdirs();
                file.createNewFile();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        try{
            FileWriter fileWriter = new FileWriter(path);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for (int i = 1; i <= 10; i++) {
                bufferedWriter.write("9 x " + i + " = " + (9 * i));
                bufferedWriter.newLine();
            }
            bufferedWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            FileReader fileReader = new FileReader(path);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }
            bufferedReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            if (file.delete()) {
                System.out.println("Arquivo deletado com sucesso");
            } else {
                System.out.println("Falha ao deletar arquivo");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}