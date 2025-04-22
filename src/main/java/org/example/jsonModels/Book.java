package org.example.jsonModels;

public class Book {
    private Abbreviation abbrev;
    private String author;
    private int chapters;
    private String group;
    private String name;
    private String testament;

    public String GetAuthor(){
        return author;
    }

    public String GetName(){
        return name;
    }

    public static class Abbreviation {
        private String pt;
        private String en;

        public String getPt() { return pt; }
        public String getEn() { return en; }
    }
}
