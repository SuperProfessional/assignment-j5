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
@Table(name = "NhanVien")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NhanVien {

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

  @Column(name = "GioiTinh")
  private String goiTinh;

  @Column(name = "NgaySinh")
  private LocalDateTime ngaySinh;

  @Column(name = "DiaChi")
  private String diaChi;

  @Column(name = "Sdt")
  private String sdt;

  @Column(name = "MatKhau")
  private String matKhau;

  @Column(name = "IdCH")
  private Integer idCH;

  @Column(name = "IdCV")
  private Integer idCV;

  @Column(name = "IdGuiBC")
  private Integer idGuiBC;

  @Column(name = "TrangThai")
  private Boolean trangThai;

}
