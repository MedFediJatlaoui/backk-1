package tn.esprit.teriak.Entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "upload") // Replace "table_row" with the actual table name
@NoArgsConstructor
@Getter
@Setter
public class TableRow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // You can change the ID type based on your requirements

    @Column(name = "code_pct")
    private String codePct;

    @Column(name = "qte_initiale_commandee")
    private String qteInitialeCommandee;

    // Add other necessary fields/columns here

    public TableRow(String codePct, String qteInitialeCommandee) {
        this.codePct = codePct;
        this.qteInitialeCommandee = qteInitialeCommandee;
        // Initialize other fields
    }

    // Add getters and setters for the fields
}
