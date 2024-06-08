package com.example.demo.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
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
  @NotBlank(message = "Vui lòng nhập!")
  private String ma;

  @Column(name = "Ten")
  @NotBlank(message = "Vui lòng nhập!")
  private String ten;

  @Column(name = "TenDem")
  @NotBlank(message = "Vui lòng nhập!")
  private String tenDem;

  @Column(name = "Ho")
  @NotBlank(message = "Vui lòng nhập!")
  private String ho;

  @Column(name = "NgaySinh")
  @NotBlank(message = "Vui lòng nhập!")
  private LocalDateTime ngaySinh;

  @Column(name = "Sdt")
  @NotBlank(message = "Vui lòng nhập!")
  private String sdt;

  @Column(name = "DiaChi")
  @NotBlank(message = "Vui lòng nhập!")
  private String diaChi;

  @Column(name = "ThanhPho")
  @NotBlank(message = "Vui lòng nhập!")
  private String thanhPho;

  @Column(name = "QuocGia")
  @NotBlank(message = "Vui lòng nhập!")
  private String quocGia;

  @Column(name = "MatKhau")
  @NotBlank(message = "Vui lòng nhập!")
  private String matKhau;
}
