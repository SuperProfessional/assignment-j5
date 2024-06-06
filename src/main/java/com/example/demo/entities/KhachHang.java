package com.example.demo.entities;

import java.time.LocalDateTime;

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
@Table(name = "KhachHang")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class KhachHang {

  @Id
  @Column(name = "Id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(name = "Ma")
  private String ma;

  @Column(name = "Ten")
  private String ten;

  @Column(name = "TenDem")
  private String tenDem;

  @Column(name = "Ho")
  private String ho;

  @Column(name = "NgaySinh")
  private LocalDateTime ngaySinh;

  @Column(name = "Sdt")
  private String sdt;

  @Column(name = "DiaChi")
  private String diaChi;

  @Column(name = "ThanhPho")
  private String thanhPho;

  @Column(name = "QuocGia")
  private String quocGia;

  @Column(name = "MatKhau")
  private String matKhau;
}
