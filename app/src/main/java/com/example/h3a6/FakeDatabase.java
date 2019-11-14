package com.example.h3a6;

import java.util.HashMap;
import java.util.List;

public class FakeDatabase {

    public static HashMap<String, Cats> catsarray = new HashMap<>();

    public static Cats getCatsById(long id) {
        return catsarray.get(id);
    }

    public static void saveCatsToFakeDatabase(List<Cats> catsToSave) {
        for(int i = 0; i < catsToSave.size(); i++) {
            Cats cats = catsToSave.get(i);
            catsarray.put(cats.getId(), cats);
        }
    }

}
