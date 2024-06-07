package com.example.demo.entities;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
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

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "IdSP", referencedColumnName = "id")
  @JsonBackReference
  private SanPham sanPham;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "IdNsx", referencedColumnName = "id")
  @JsonBackReference
  private NSX nsx;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "IdMauSac", referencedColumnName = "id")
  @JsonBackReference
  private MauSac mauSac;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "IdDongSP", referencedColumnName = "id")
  @JsonBackReference
  private DongSP dongSP;

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
