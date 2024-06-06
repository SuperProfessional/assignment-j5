package com.example.demo.entities;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "ChiTietSP")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChiTietSP {

  @Id
  @Column(name = "Id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(name = "IdSP")
  private Integer idSP;

  @Column(name = "IdNsx")
  private Integer idNsx;

  @Column(name = "IdMauSac")
  private Integer idMauSac;

  @Column(name = "IdDongSP")
  private Integer idDongSP;

  @Column(name = "NamBH")
  private Integer namBH;

  @Column(name = "MoTa")
  private String moTa;

  @Column(name = "SoLuongTon")
  private Integer soLuongTon;

  @Column(name = "GiaNhap")
  private BigDecimal giaNhap;

  @Column(name = "GiaBan")
  private BigDecimal giaBan;
}
