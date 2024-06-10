package com.example.demo.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "NSX")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NSX {

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

  @OneToMany(mappedBy = "nsx", fetch = FetchType.LAZY)
  @JsonManagedReference
  private List<ChiTietSP> chiTietSPList;

}
