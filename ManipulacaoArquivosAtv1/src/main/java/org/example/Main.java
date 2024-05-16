package org.example;

import org.example.entities.Pessoa;

import java.io.*;
import java.lang.reflect.Method;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        String path = "C:\\Users\\gabri\\Desktop\\PastaAula\\pessoa.csv";
        /*
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

        */

        try{
            FileReader fileReader = new FileReader(path);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = bufferedReader.readLine();
            String[] valuesAux = line.split(";");

            ArrayList<Pessoa> list = new ArrayList<>();
            while ((line = bufferedReader.readLine()) != null) {
                /*Colunas: nome	idade	cpf	rg	data_nasc	sexo	signo	mae	pai	email	senha	cep	endereco	numero	bairro	cidade	estado	telefone_fixo	celular	altura	peso	tipo_sanguineo	cor
                 */
                String[] values = line.split(";");
                Pessoa pessoa = new Pessoa();
                pessoa.setNome(values[0]);
                pessoa.setIdade(Integer.parseInt(values[1]));
                pessoa.setCpf(values[2]);
                pessoa.setRg(values[3]);
                pessoa.setDataNasc(values[4]);
                pessoa.setSexo(values[5]);
                pessoa.setSigno(values[6]);
                pessoa.setMae(values[7]);
                pessoa.setPai(values[8]);
                pessoa.setEmail(values[9]);
                pessoa.setSenha(values[10]);
                pessoa.setCep(values[11]);
                pessoa.setEndereco(values[12]);
                pessoa.setNumero(values[13]);
                pessoa.setBairro(values[14]);
                pessoa.setCidade(values[15]);
                pessoa.setEstado(values[16]);
                pessoa.setTelefoneFixo(values[17]);
                pessoa.setCelular(values[18]);
                pessoa.setAltura(Double.parseDouble(values[19]));
                pessoa.setPeso(Double.parseDouble(values[20]));
                pessoa.setTipoSanguineo(values[21]);
                pessoa.setCor(values[22]);
                list.add(pessoa);


            }
            bufferedReader.close();
            fileReader.close();
            System.out.println(list.size());

            StringUtils stringUtils = new StringUtils();

            int aux = 0;
            for (int i = 0; i < Pessoa.class.getDeclaredMethods().length; i++) {
                if ( Pessoa.class.getDeclaredMethods()[i].getName().startsWith("set")) {
                    aux++;
                }
            }
            // Mapeie os nomes das propriedades para seus índices no array valuesAux
            Map<String, Integer> propertyIndexMap = new HashMap<>();
            for (int i = 0; i < valuesAux.length; i++) {
                propertyIndexMap.put(valuesAux[i], i);
            }
            String auxAux = valuesAux[0].toUpperCase().substring(1, 2) + valuesAux[0].substring(2);
            System.out.println("pessoa.set"+ auxAux +"(values[" + 0 + "]);");
            for (String property : Arrays.copyOfRange(valuesAux, 0, valuesAux.length)) {
                for (Method method : Pessoa.class.getDeclaredMethods()) {
                    String methodName = method.getName();
                    if (methodName.startsWith("set") && methodName.substring(3).equalsIgnoreCase(property)) {
                        System.out.println("pessoa." + methodName + "(values[" + propertyIndexMap.get(property) + "]);");
                        break;
                    }
                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }


    }
}