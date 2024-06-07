package com.example.demo.entities;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
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

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "IdCH", referencedColumnName = "id")
  @JsonBackReference
  private CuaHang cuaHang;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "IdCV", referencedColumnName = "id")
  @JsonBackReference
  private ChucVu chucVu;

  @OneToMany(mappedBy = "nhanVien", fetch = FetchType.LAZY)
  @JsonManagedReference
  private List<NhanVien> nhanViens;

  @ManyToOne
  @JoinColumn(name = "IdGuiBC", referencedColumnName = "id")
  @JsonBackReference
  private NhanVien nhanVien;

  @Column(name = "TrangThai")
  private Boolean trangThai;

}
