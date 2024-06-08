package com.example.demo.controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.example.demo.entities.ChucVu;
import com.example.demo.repositories.ChucVuRepository;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/chuc-vu")
@RequiredArgsConstructor
public class ChucVuController {

  private final String URI = "/chuc-vu";

  private final ChucVuRepository chucVuRepository;

  private final Validator validator;

  @GetMapping("/hien-thi")
  public String hienThi(Model model) {
    model.addAttribute("chucVuList", this.chucVuRepository.findAll());
    return this.URI + "/hien-thi";
  }

  @GetMapping("/remove/{id}")
  public String remove(@PathVariable(name = "id") Integer id) {
    this.chucVuRepository.deleteById(id);
    return "redirect:" + this.URI + "/hien-thi";
  }

  @GetMapping("/view-update/{id}")
  public String viewUpdate(Model model, @PathVariable(name = "id") Integer id) {
    model.addAttribute(
        "chucVu",
        this.chucVuRepository.findById(id)
            .orElseThrow()
    );
    return this.URI + "/update";
  }

  @PostMapping("/update/{id}")
  public String update(Model model,
                       @PathVariable(name = "id") Integer id,
                       @ModelAttribute(name = "chucVu") ChucVu chucVu) {

    chucVu.setId(id);
    if (this.validate(model, chucVu)) {
      model.addAttribute("chucVu", chucVu);
      return this.URI + "/update";
    }
    this.chucVuRepository.findById(id)
        .ifPresent(
            p -> {
              p.setMa(chucVu.getMa());
              p.setTen(chucVu.getTen());

              this.chucVuRepository.save(p);
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
                    @ModelAttribute ChucVu chucVu) {
    if (this.validate(model, chucVu)) {
      model.addAttribute("chucVu", chucVu);
      return this.URI + "/add";
    }
    this.chucVuRepository.save(chucVu);
    return "redirect:" + this.URI + "/hien-thi";
  }

  private boolean validate(Model model, ChucVu chucVu) {
    Map<String, List<String>> rerult = this.validator.validate(chucVu)
        .stream()
        .collect(
            Collectors.groupingBy(
                o -> o.getPropertyPath().toString(),
                Collectors.mapping(
                    ConstraintViolation::getMessage, Collectors.toList()
                )
            )
        );

    if (this.chucVuRepository.checkExistMa(chucVu.getId(), chucVu.getMa()) > 0) {
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
