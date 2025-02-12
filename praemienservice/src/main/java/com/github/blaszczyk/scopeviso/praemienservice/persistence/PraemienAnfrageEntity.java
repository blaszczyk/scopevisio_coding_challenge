package com.github.blaszczyk.scopeviso.praemienservice.persistence;

import com.github.blaszczyk.scopeviso.praemienservice.domain.Fahrzeugtyp;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Objects;
import java.util.UUID;

@Table(name = "praemien-anfrage")
public class PraemienAnfrageEntity {

    private UUID praemienId;

    private int kilometerleistung;

    private Fahrzeugtyp fahrzeugtyp;

    private String bundesland;

    private String kreis;

    private String stadt;

    private String postleitzahl;

    private String bezirk;

    private int praemie;

    public UUID getPraemienId() {
        return praemienId;
    }

    public void setPraemienId(UUID praemienId) {
        this.praemienId = praemienId;
    }

    public int getKilometerleistung() {
        return kilometerleistung;
    }

    public void setKilometerleistung(int kilometerleistung) {
        this.kilometerleistung = kilometerleistung;
    }

    public Fahrzeugtyp getFahrzeugtyp() {
        return fahrzeugtyp;
    }

    public void setFahrzeugtyp(Fahrzeugtyp fahrzeugtyp) {
        this.fahrzeugtyp = fahrzeugtyp;
    }

    public String getBundesland() {
        return bundesland;
    }

    public void setBundesland(String bundesland) {
        this.bundesland = bundesland;
    }

    public String getKreis() {
        return kreis;
    }

    public void setKreis(String kreis) {
        this.kreis = kreis;
    }

    public String getStadt() {
        return stadt;
    }

    public void setStadt(String stadt) {
        this.stadt = stadt;
    }

    public String getPostleitzahl() {
        return postleitzahl;
    }

    public void setPostleitzahl(String postleitzahl) {
        this.postleitzahl = postleitzahl;
    }

    public String getBezirk() {
        return bezirk;
    }

    public void setBezirk(String bezirk) {
        this.bezirk = bezirk;
    }

    public int getPraemie() {
        return praemie;
    }

    public void setPraemie(int praemie) {
        this.praemie = praemie;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PraemienAnfrageEntity that = (PraemienAnfrageEntity) o;
        return kilometerleistung == that.kilometerleistung && praemie == that.praemie && Objects.equals(praemienId, that.praemienId) && fahrzeugtyp == that.fahrzeugtyp && Objects.equals(bundesland, that.bundesland) && Objects.equals(kreis, that.kreis) && Objects.equals(stadt, that.stadt) && Objects.equals(postleitzahl, that.postleitzahl) && Objects.equals(bezirk, that.bezirk);
    }

    @Override
    public int hashCode() {
        return Objects.hash(praemienId, kilometerleistung, fahrzeugtyp, bundesland, kreis, stadt, postleitzahl, bezirk, praemie);
    }

    @Override
    public String toString() {
        return "PraemienAnfrage{" +
                "praemienId=" + praemienId +
                ", kilometerleistung=" + kilometerleistung +
                ", fahrzeugtyp=" + fahrzeugtyp +
                ", bundesland='" + bundesland + '\'' +
                ", kreis='" + kreis + '\'' +
                ", stadt='" + stadt + '\'' +
                ", postleitzahl='" + postleitzahl + '\'' +
                ", bezirk='" + bezirk + '\'' +
                ", praemie=" + praemie +
                '}';
    }
}
