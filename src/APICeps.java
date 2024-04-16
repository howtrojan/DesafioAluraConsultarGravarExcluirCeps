import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class APICeps {

    public static String consultarCEP(String cep) {
        CepResponse cepResponse = new CepResponse();

        try {
            String url = "https://viacep.com.br/ws/" + cep + "/json/";
            String jsonResponse = sendGetRequest(url);

            // Usar Gson para analisar o JSON
            Gson gson = new Gson();
            JsonObject jsonObject = gson.fromJson(jsonResponse, JsonObject.class);

            cepResponse.setCep(cep);
            cepResponse.setLogradouro(jsonObject.get("logradouro").getAsString());
            cepResponse.setBairro(jsonObject.get("bairro").getAsString());
            cepResponse.setLocalidade(jsonObject.get("localidade").getAsString());
            cepResponse.setUf(jsonObject.get("uf").getAsString());

            System.out.println(cepResponse);



                File meuArquivo = new File("endereço.txt");
                FileWriter jsonCep = new FileWriter(meuArquivo, true);
                jsonCep.write(cepResponse.toString() + System.lineSeparator());
                if (jsonCep != null) {
                try {
                    jsonCep.close();
                    System.out.println("Gravou no arquivo com sucesso!!");
                } catch (IOException e) {
                    System.out.println("Ocorreu um erro ao fechar o FileWriter:");
                    e.printStackTrace();
                }
            }



        } catch (Exception e) {
            System.out.println("Cep Inválido. Escolha novamente a opção e digite um Cep válido sem pontos nesse formato 99999999");
        }

        return cepResponse.toString();
    }

    public static void exibirEnderecosGravados() {
        try {
            File meuArquivo = new File("endereço.txt");
            BufferedReader reader = new BufferedReader(new FileReader(meuArquivo));
            String linha;

            System.out.println("Endereços gravados:");
            while ((linha = reader.readLine()) != null) {
                System.out.println(linha);
            }

            reader.close();
        } catch (IOException e) {
            System.out.println("Ocorreu um erro ao ler os endereços gravados:");
            e.printStackTrace();
        }
    }

    private static String sendGetRequest(String url) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        connection.setRequestMethod("GET");

        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null) {
            response.append(line);
        }
        reader.close();

        return response.toString();
    }

    public static void exibirEnderecosEDeletar() {
        try {
            File meuArquivo = new File("endereço.txt");
            BufferedReader reader = new BufferedReader(new FileReader(meuArquivo));
            List<String> linhas = new ArrayList<>();
            String linha;

            // Lê cada linha do arquivo e armazena em uma lista
            while ((linha = reader.readLine()) != null) {
                linhas.add(linha);
            }

            reader.close();

            // Exibe os endereços numerados
            System.out.println("Endereços gravados:");
            for (int i = 0; i < linhas.size(); i++) {
                System.out.println((i + 1) + ". " + linhas.get(i));
            }

            // Solicita ao usuário o número da linha que deseja deletar
            Scanner scanner = new Scanner(System.in);
            System.out.print("Digite o número da linha que deseja deletar: ");
            int numeroLinha = scanner.nextInt();

            // Deleta a linha escolhida
            linhas.remove(numeroLinha - 1);

            // Escreve de volta no arquivo apenas as linhas que não foram deletadas
            FileWriter writer = new FileWriter(meuArquivo);
            for (String l : linhas) {
                writer.write(l + System.lineSeparator());
            }
            writer.close();

            System.out.println("Linha " + numeroLinha + " deletada com sucesso.");

        } catch (IOException e) {
            System.out.println("Ocorreu um erro ao ler ou deletar os endereços gravados:");
            e.printStackTrace();
        }
    }
}
