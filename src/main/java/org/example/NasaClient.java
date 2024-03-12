package org.example;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.net.ssl.SSLSocketFactory;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.stream.Collectors;

public class NasaClient {
    public static void main(String[] args) {
        // todo: Establish a SSL connection using host + port
        try (Socket nasaClient = SSLSocketFactory.getDefault().createSocket("", 0)) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(nasaClient.getInputStream()));
            PrintWriter writer = new PrintWriter(new OutputStreamWriter(nasaClient.getOutputStream()));

            // todo: Write an HTTP request according to its structure using "writer". Don't forget to "flush" message in
            // todo: your output stream

            String response;
            // todo: Extract body in json format from response (Check structure of HTTP response)
            response = reader.lines()
                    .collect(Collectors.joining(""));
            System.out.println(response); // Just to check response

            ObjectMapper objectMapper = new ObjectMapper();

            // todo: Convert JSON to Java object using "JsonNode" class or custom DTO class with with the corresponding
            //  json structure
            JsonNode jsonNode = objectMapper.readValue(response, JsonNode.class);
            System.out.println(jsonNode); // Just to check the conversion result

            // todo: Perform a HTTP request for each "img_src" and find the largest picture.
            // todo: Pay attention to the response from the server in the browser, status code and headers.
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}