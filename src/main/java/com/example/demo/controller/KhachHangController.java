package com.example.demo.controller;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.example.demo.entities.KhachHang;
import com.example.demo.model.KhachHangModel;
import com.example.demo.repositories.KhachHangRepository;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/khach-hang")
@RequiredArgsConstructor
public class KhachHangController {

  private final String URI = "/khach-hang";

  private final KhachHangRepository khachHangRepository;

  private final Validator validator;

  private final Integer PAGE_NUMBER = 0;

  private final Integer PAGE_SIZE = 5;

  private Pageable PAGE_REQUEST = PageRequest.of(PAGE_NUMBER, PAGE_SIZE);

  @GetMapping("/hien-thi")
  public String hienThi(
      Model model,
      @RequestParam(name = "pageNumber", defaultValue = "0") Integer pageNumber
  ) {
    if (pageNumber > 0) {
      PAGE_REQUEST = PageRequest.of(pageNumber - 1, this.PAGE_SIZE);
    }
    Page<KhachHang> khachHangPage = this.khachHangRepository.findAll(PAGE_REQUEST);

    model.addAttribute("khachHangPage", khachHangPage);
    return this.URI + "/hien-thi";
  }

  @GetMapping("/remove/{id}")
  public String remove(@PathVariable(name = "id") Integer id) {
    this.khachHangRepository.deleteById(id);
    return "redirect:" + this.URI + "/hien-thi";
  }


  @GetMapping("/view-update/{id}")
  public String viewUpdate(Model model, @PathVariable(name = "id") Integer id) {

    model.addAttribute(
        "khachHang",
        this.khachHangRepository.findById(id)
            .map(
                khachHang -> KhachHangModel.builder()
                    .id(khachHang.getId())
                    .ma(khachHang.getMa())
                    .ten(khachHang.getTen())
                    .tenDem(khachHang.getTenDem())
                    .ho(khachHang.getHo())
                    .ngaySinh(khachHang.getNgaySinh())
                    .sdt(khachHang.getSdt())
                    .diaChi(khachHang.getDiaChi())
                    .thanhPho(khachHang.getThanhPho())
                    .quocGia(khachHang.getQuocGia())
                    .build()
            )
            .orElseThrow()
    );

    return this.URI + "/update";
  }

  @PostMapping("/update/{id}")
  public String update(Model model,
                       @PathVariable(name = "id") Integer id,
                       @ModelAttribute(name = "khachHang") KhachHangModel khachHang) {

    if (this.validate(model, khachHang)) {
      model.addAttribute("khachHang", khachHang);
      return this.URI + "/update";
    }

    this.khachHangRepository.findById(id)
        .ifPresent(
            o -> {
              o.setMa(khachHang.getMa());
              o.setTen(khachHang.getTen());
              o.setTenDem(khachHang.getTenDem());
              o.setHo(khachHang.getHo());
              o.setNgaySinh(khachHang.getNgaySinh());
              o.setSdt(khachHang.getSdt());
              o.setDiaChi(khachHang.getDiaChi());
              o.setThanhPho(khachHang.getThanhPho());
              o.setQuocGia(khachHang.getQuocGia());

              this.khachHangRepository.save(o);
            }
        );

    return "redirect:" + this.URI + "/hien-thi";
  }

  @GetMapping("/view-add")
  public String viewAdd() {
    return this.URI + "/add";
  }

  @PostMapping("/add")
  public String add(Model model,
                    @ModelAttribute(name = "khachHang") KhachHangModel khachHang) {
    if (this.validate(model, khachHang)) {
      model.addAttribute("khachHang", khachHang);
      return this.URI + "/add";
    }
    this.khachHangRepository.save(
        KhachHang.builder()
            .ma(khachHang.getMa())
            .ten(khachHang.getTen())
            .tenDem(khachHang.getTenDem())
            .ho(khachHang.getHo())
            .ngaySinh(khachHang.getNgaySinh())
            .sdt(khachHang.getSdt())
            .diaChi(khachHang.getDiaChi())
            .thanhPho(khachHang.getThanhPho())
            .quocGia(khachHang.getQuocGia())
            .matKhau(khachHang.getMatKhau())
            .build()
    );

    return "redirect:" + this.URI + "/hien-thi";
  }

  private boolean validate(Model model, KhachHangModel khachHang) {
    Map<String, List<String>> rerult = this.validator.validate(khachHang)
        .stream()
        .collect(
            Collectors.groupingBy(
                o -> o.getPropertyPath().toString(),
                Collectors.mapping(
                    ConstraintViolation::getMessage, Collectors.toList()
                )
            )
        );

    if (
        this.khachHangRepository.checkExistMa(
            Objects.nonNull(khachHang.getId())
                ? khachHang.getId()
                : 0,
            khachHang.getMa()
        ) > 0
    ) {
      rerult.merge(
          "ma",
          List.of("Mã đã tồi tại"),
          (oldValue, newValue) -> Stream.of(oldValue, newValue)
              .flatMap(List::stream)
              .collect(Collectors.toList())
      );
    }


    if (!Objects.equals(khachHang.getMatKhau(), khachHang.getConfirmMatKhau())) {
      rerult.merge(
          "confirmMatKhau",
          List.of("Mật khẩu xác nhận không khớp!"),
          (oldValue, newValue) -> Stream.of(oldValue, newValue)
              .flatMap(List::stream)
              .collect(Collectors.toList())
      );
    }

    if (!CollectionUtils.isEmpty(rerult)) {
      model.addAttribute("messageError", rerult);
      return Boolean.TRUE;
    }
    return Boolean.FALSE;
  }
}
