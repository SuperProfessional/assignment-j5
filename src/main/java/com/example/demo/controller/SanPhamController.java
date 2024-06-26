package com.example.demo.controller;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.example.demo.entities.SanPham;
import com.example.demo.repositories.SanPhamRepository;
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
@RequestMapping("/san-pham")
@RequiredArgsConstructor
public class SanPhamController {

  private final String URI = "/san-pham";

  private final SanPhamRepository sanPhamRepository;

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
    Page<SanPham> sanPhamPage = this.sanPhamRepository.findAll(PAGE_REQUEST);

    model.addAttribute("sanPhamPage", sanPhamPage);
    return this.URI + "/hien-thi";
  }

  @GetMapping("/remove/{id}")
  public String remove(@PathVariable(name = "id") Integer id) {
    this.sanPhamRepository.deleteById(id);
    return "redirect:" + this.URI + "/hien-thi";
  }


  @GetMapping("/view-update/{id}")
  public String viewUpdate(Model model, @PathVariable(name = "id") Integer id) {

    model.addAttribute(
        "sanPham",
        this.sanPhamRepository.findById(id)
            .orElseThrow()
    );

    return this.URI + "/update";
  }

  @PostMapping("/update/{id}")
  public String update(Model model,
                       @PathVariable(name = "id") Integer id,
                       @ModelAttribute(name = "sanPham") SanPham sanPham) {
    if (this.validate(model, sanPham)) {
      model.addAttribute("sanPham", sanPham);
      return this.URI + "/update";
    }

    this.sanPhamRepository.findById(id)
        .ifPresent(
            o -> {
              o.setMa(sanPham.getMa());
              o.setTen(sanPham.getTen());

              this.sanPhamRepository.save(o);
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
                    @ModelAttribute(name = "sanPham") SanPham sanPham) {
    if (this.validate(model, sanPham)) {
      model.addAttribute("sanPham", sanPham);
      return this.URI + "/add";
    }
    this.sanPhamRepository.save(sanPham);

    return "redirect:" + this.URI + "/hien-thi";
  }

  private boolean validate(Model model, SanPham sanPham) {
    Map<String, List<String>> rerult = this.validator.validate(sanPham)
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
        this.sanPhamRepository.checkExistMa(
            Objects.nonNull(sanPham.getId())
                ? sanPham.getId()
                : 0,
            sanPham.getMa()
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

    if (!CollectionUtils.isEmpty(rerult)) {
      model.addAttribute("messageError", rerult);
      return Boolean.TRUE;
    }
    return Boolean.FALSE;
  }
}
