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
@Table(name = "CuaHang")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CuaHang {

  @Id
  @Column(name = "Id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(name = "Ma")
  private String ma;

  @Column(name = "Ten")
  private String ten;

  @Column(name = "DiaChi")
  private String diaChi;

  @Column(name = "ThanhPho")
  private String thanhPho;

  @Column(name = "QuocGia")
  private String quocGia;
}
