package com.example.demo.controller;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.example.demo.entities.DongSP;
import com.example.demo.repositories.DongSPRepository;
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
@RequestMapping("/dong-sp")
@RequiredArgsConstructor
public class DongSPController {

  private final String URI = "/dong-sp";

  private final DongSPRepository dongSPRepository;

  private final Validator validator;

  @GetMapping("/hien-thi")
  public String hienThi(Model model) {
    model.addAttribute("dongSPList", this.dongSPRepository.findAll());
    return this.URI + "/hien-thi";
  }

  @GetMapping("/remove/{id}")
  public String remove(@PathVariable(name = "id") Integer id) {
    this.dongSPRepository.deleteById(id);
    return "redirect:" + this.URI + "/hien-thi";
  }

  @GetMapping("/view-update/{id}")
  public String viewUpdate(Model model, @PathVariable(name = "id") Integer id) {
    model.addAttribute(
        "dongSP",
        this.dongSPRepository.findById(id)
            .orElseThrow()
    );
    return this.URI + "/update";
  }

  @PostMapping("/update/{id}")
  public String update(Model model,
                       @PathVariable(name = "id") Integer id,
                       @ModelAttribute(name = "dongSP") DongSP dongSP) {

    dongSP.setId(id);
    if (this.validate(model, dongSP)) {
      model.addAttribute("dongSP", dongSP);
      return this.URI + "/update";
    }
    this.dongSPRepository.findById(id)
        .ifPresent(
            p -> {
              p.setMa(dongSP.getMa());
              p.setTen(dongSP.getTen());

              this.dongSPRepository.save(p);
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
                    @ModelAttribute DongSP dongSP) {
    if (this.validate(model, dongSP)) {
      model.addAttribute("dongSP", dongSP);
      return this.URI + "/add";
    }
    this.dongSPRepository.save(dongSP);
    return "redirect:" + this.URI + "/hien-thi";
  }

  private boolean validate(Model model, DongSP dongSP) {
    Map<String, List<String>> rerult = this.validator.validate(dongSP)
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
        this.dongSPRepository.checkExistMa(
            Objects.nonNull(dongSP.getId())
                ? dongSP.getId()
                : 0,
            dongSP.getMa()
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
