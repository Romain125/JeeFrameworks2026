package org.example;

import org.springframework.stereotype.Service;

@Service
public class TraceService {

    public void trace(Book book){
        //System.out.println("Le livre "+book.getTitle().getOriginalTitle()+" a été enregistré");

        //Ho noes ! Quel horrible code !!!
        throw new RuntimeException("Ca plante mais c'est fait exprès !");
    }

}
