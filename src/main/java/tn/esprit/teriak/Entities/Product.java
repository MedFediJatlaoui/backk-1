package tn.esprit.teriak.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "products")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String codePCT;
    private String designation;
    private int uniteParCaisse;
    private double prix;
    private String productName ;
    private String avecvrac;
    private double remise;
    private int carton;
    private String vrac;
    private int quantiteTotale;
    private double montantApresRemise;
    private boolean disponible;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAvecvrac() {
        return avecvrac;
    }

    public void setAvecvrac(String avecvrac) {
        this.avecvrac = avecvrac;
    }

    public String getCodePCT() {
        return codePCT;
    }

    public void setCodePCT(String codePCT) {
        this.codePCT = codePCT;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public int getUniteParCaisse() {
        return uniteParCaisse;
    }

    public void setUniteParCaisse(int uniteParCaisse) {
        this.uniteParCaisse = uniteParCaisse;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public double getRemise() {
        return remise;
    }

    public void setRemise(double remise) {
        this.remise = remise;
    }

    public int getCarton() {
        return carton;
    }

    public void setCarton(int carton) {
        this.carton = carton;
    }

    public String getVrac() {
        return vrac;
    }

    public void setVrac(String vrac) {
        this.vrac = String.valueOf(vrac);
    }

    public int getQuantiteTotale() {
        return quantiteTotale;
    }

    public void setQuantiteTotale(int quantiteTotale) {
        this.quantiteTotale = quantiteTotale;
    }

    public double getMontantApresRemise() {
        return montantApresRemise;
    }

    public void setMontantApresRemise(double montantApresRemise) {
        this.montantApresRemise = montantApresRemise;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
}
