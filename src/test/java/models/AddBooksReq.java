package models;

import lombok.Data;

import java.util.ArrayList;

@Data
public class AddBooksReq {

    private String userId;
    private ArrayList<CollectionOfIsbn> collectionOfIsbns;

    public void addIsbns(String isbns) {
        CollectionOfIsbn isbn = new CollectionOfIsbn();
        isbn.setIsbn(isbns);
        ArrayList<CollectionOfIsbn> isbnData = new ArrayList<>();
        isbnData.add(isbn);
        this.collectionOfIsbns = isbnData;

    }

}
