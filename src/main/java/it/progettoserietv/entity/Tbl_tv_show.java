package it.progettoserietv.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
@Entity
@Table(name = "tbl_tv_show")
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Tbl_tv_show {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String url;

    private String name;

    private String language;

    private String status;
}




