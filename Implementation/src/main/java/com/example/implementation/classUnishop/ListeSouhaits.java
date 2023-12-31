package com.example.implementation.classUnishop;

import java.util.ArrayList;
import java.util.List;

public class ListeSouhaits {
    private final Acheteur acheteur;
    private final List<ProduitEnVente> souhaits;

    public ListeSouhaits(Acheteur acheteur) {
        this.acheteur = acheteur;
        this.souhaits = new ArrayList<>();
    }

    public List<ProduitEnVente> getSouhaits() {
        return this.souhaits;
    }
}

