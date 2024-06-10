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
public class NhanVienModel {

  private Integer id;

  @NotBlank(message = "Vui lòng nhập!")
  private String ma;

  @NotBlank(message = "Vui lòng nhập!")
  private String ten;

  @NotBlank(message = "Vui lòng nhập!")
  private String tenDem;

  @NotBlank(message = "Vui lòng nhập!")
  private String ho;

  @NotBlank(message = "Vui lòng nhập!")
  @Pattern(regexp = "^(Nam|Nữ|Khác)$", message = "Giới tính không hợp lệ!")
  private String goiTinh;

  @NotNull(message = "Vui lòng nhập!")
  private LocalDate ngaySinh;

  @NotBlank(message = "Vui lòng nhập!")
  private String diaChi;

  @NotBlank(message = "Vui lòng nhập!")
  @Pattern(regexp = "^(0[0-9]{9})|(0[0-9]{10})$", message = "Số điện thoại không hợp lệ!")
  private String sdt;

  @NotBlank(message = "Vui lòng nhập!")
  private String matKhau;

  @NotNull(message = "Vui lòng nhập!")
  private Integer cuaHangId;

  @NotNull(message = "Vui lòng nhập!")
  private Integer chucVuId;

  @NotNull(message = "Vui lòng nhập!")
  private Integer nhanVienId;

  @NotNull(message = "Vui lòng nhập!")
  private Boolean trangThai;

}
