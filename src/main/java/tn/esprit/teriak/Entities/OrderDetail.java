package tn.esprit.teriak.Entities;

import jakarta.persistence.*;


@Entity
@Table(name = "order_details")
public class OrderDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    @Column(name = "code_pct")
    private String codePCT;

    @Column(name = "designation")
    private String designation;

    @Column(name = "unit_par_caisse")
    private Integer unitParCaisse;

    @Column(name = "prix")
    private Double prix;

    @Column(name = "remise")
    private Double remise;

    @Column(name = "quantite_demandee")
    private Integer quantiteDemandee;

    @Column(name = "quantite_arrondie")
    private Integer quantiteArrondie;

    @Column(name = "carton")
    private Integer carton;

    @Column(name = "vrac")
    private Integer vrac;

    @Column(name = "montant_apres_remise")
    private Double montantApresRemise;

    @Column(name = "disponible")
    private Boolean disponible;

    // Getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
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

    public Integer getUnitParCaisse() {
        return unitParCaisse;
    }

    public void setUnitParCaisse(Integer unitParCaisse) {
        this.unitParCaisse = unitParCaisse;
    }

    public Double getPrix() {
        return prix;
    }

    public void setPrix(Double prix) {
        this.prix = prix;
    }

    public Double getRemise() {
        return remise;
    }

    public void setRemise(Double remise) {
        this.remise = remise;
    }

    public Integer getQuantiteDemandee() {
        return quantiteDemandee;
    }

    public void setQuantiteDemandee(Integer quantiteDemandee) {
        this.quantiteDemandee = quantiteDemandee;
    }

    public Integer getQuantiteArrondie() {
        return quantiteArrondie;
    }

    public void setQuantiteArrondie(Integer quantiteArrondie) {
        this.quantiteArrondie = quantiteArrondie;
    }

    public Integer getCarton() {
        return carton;
    }

    public void setCarton(Integer carton) {
        this.carton = carton;
    }

    public Integer getVrac() {
        return vrac;
    }

    public void setVrac(Integer vrac) {
        this.vrac = vrac;
    }

    public Double getMontantApresRemise() {
        return montantApresRemise;
    }

    public void setMontantApresRemise(Double montantApresRemise) {
        this.montantApresRemise = montantApresRemise;
    }

    public Boolean getDisponible() {
        return disponible;
    }

    public void setDisponible(Boolean disponible) {
        this.disponible = disponible;
    }
}
