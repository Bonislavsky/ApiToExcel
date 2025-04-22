
package org.example;

import org.example.jsonModels.*;
public class Main {
    public static void main(String[] args) {

        MagicDataCreator mdc = new MagicDataCreator();

        String token = mdc.GetToken("test123@gmail.com", "password");
        Book[] books = mdc.GetBooks(token);

        FileCreator fc = new FileCreator();
        fc.createExcel(books);
    }
}