package com.kush.translate.example;

import com.google.api.client.util.Lists;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.translate.Translate;
import com.google.cloud.translate.TranslateOptions;
import com.google.cloud.translate.Translation;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TranslateMainClass {

    public static void main(String args[]){
        try {
            authExplicit("libs/civic-cedar-231707-fe76be122556.json");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void authExplicit(String jsonPath) throws IOException, FileNotFoundException {
        List<String> listOfScopes = new ArrayList<>();
        listOfScopes.add("https://www.googleapis.com/auth/cloud-platform");
        // You can specify a credential file by providing a path to GoogleCredentials.
        // Otherwise credentials are read from the GOOGLE_APPLICATION_CREDENTIALS environment variable.
        GoogleCredentials credentials = GoogleCredentials.fromStream(new FileInputStream(jsonPath))
                .createScoped(Lists.newArrayList(listOfScopes));

        Translate translate = TranslateOptions.newBuilder().setCredentials(credentials).build().getService();

        System.out.print("Enter a string: ");
        Scanner sc= new Scanner(System.in); //System.in is a standard input stream
        String str= sc.nextLine();

        Translation translation =translate.translate(str,Translate.TranslateOption.sourceLanguage("en"),Translate.TranslateOption.targetLanguage("gu"));
        System.out.println(translation.getTranslatedText());
    }
}
