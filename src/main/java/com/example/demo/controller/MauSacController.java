package com.example.demo.controller;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.example.demo.entities.MauSac;
import com.example.demo.repositories.MauSacRepository;
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
@RequestMapping("/mau-sac")
@RequiredArgsConstructor
public class MauSacController {

  private final String URI = "/mau-sac";

  private final MauSacRepository mauSacRepository;

  private final Validator validator;

  @GetMapping("/hien-thi")
  public String hienThi(Model model) {
    model.addAttribute("mauSacList", this.mauSacRepository.findAll());
    return this.URI + "/hien-thi";
  }

  @GetMapping("/remove/{id}")
  public String remove(@PathVariable(name = "id") Integer id) {
    this.mauSacRepository.deleteById(id);
    return "redirect:" + this.URI + "/hien-thi";
  }

  @GetMapping("/view-update/{id}")
  public String viewUpdate(Model model, @PathVariable(name = "id") Integer id) {
    model.addAttribute(
        "mauSac",
        this.mauSacRepository.findById(id)
            .orElseThrow()
    );
    return this.URI + "/update";
  }

  @PostMapping("/update/{id}")
  public String update(Model model,
                       @PathVariable(name = "id") Integer id,
                       @ModelAttribute(name = "mauSac") MauSac mauSac) {

    mauSac.setId(id);
    if (this.validate(model, mauSac)) {
      model.addAttribute("mauSac", mauSac);
      return this.URI + "/update";
    }
    this.mauSacRepository.findById(id)
        .ifPresent(
            o -> {
              o.setMa(mauSac.getMa());
              o.setTen(mauSac.getTen());

              this.mauSacRepository.save(o);
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
                    @ModelAttribute MauSac mauSac) {
    if (this.validate(model, mauSac)) {
      model.addAttribute("mauSac", mauSac);
      return this.URI + "/add";
    }
    this.mauSacRepository.save(mauSac);
    return "redirect:" + this.URI + "/hien-thi";
  }

  private boolean validate(Model model, MauSac mauSac) {
    Map<String, List<String>> rerult = this.validator.validate(mauSac)
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
        this.mauSacRepository.checkExistMa(
            Objects.nonNull(mauSac.getId())
                ? mauSac.getId()
                : 0,
            mauSac.getMa()
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
