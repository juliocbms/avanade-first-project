package me.dio.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity(name = "tb_card")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Card {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    @Column(unique = true)
    private String Number;

    @Column(name = "available_limit",precision = 13, scale = 2)
    private BigDecimal limit;




}
