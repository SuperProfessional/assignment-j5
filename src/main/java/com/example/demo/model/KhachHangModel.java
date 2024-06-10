package com.example.demo.model;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class KhachHangModel {

  private Integer id;

  @NotBlank(message = "Vui lòng nhập!")
  private String ma;

  @NotBlank(message = "Vui lòng nhập!")
  private String ten;

  @NotBlank(message = "Vui lòng nhập!")
  private String tenDem;

  @NotBlank(message = "Vui lòng nhập!")
  private String ho;

  @NotNull(message = "Vui lòng nhập!")
  private LocalDate ngaySinh;

  @NotBlank(message = "Vui lòng nhập!")
  @Pattern(regexp = "^(0[0-9]{9})|(0[0-9]{10})$", message = "Số điện thoại không hợp lệ!")
  private String sdt;

  @NotBlank(message = "Vui lòng nhập!")
  private String diaChi;

  @NotBlank(message = "Vui lòng nhập!")
  private String thanhPho;

  @NotBlank(message = "Vui lòng nhập!")
  private String quocGia;

  @NotBlank(message = "Vui lòng nhập!")
  private String matKhau;

  @NotBlank(message = "Vui lòng nhập!")
  private String confirmMatKhau;
}
