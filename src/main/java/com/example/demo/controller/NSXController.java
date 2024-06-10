package com.example.demo.controller;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.example.demo.entities.NSX;
import com.example.demo.repositories.NSXRepository;
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
@RequestMapping("/nsx")
@RequiredArgsConstructor
public class NSXController {

  private final String URI = "/nsx";

  private final NSXRepository nsxRepository;

  private final Validator validator;

  @GetMapping("/hien-thi")
  public String hienThi(Model model) {
    model.addAttribute("nsxList", this.nsxRepository.findAll());
    return this.URI + "/hien-thi";
  }

  @GetMapping("/remove/{id}")
  public String remove(@PathVariable(name = "id") Integer id) {
    this.nsxRepository.deleteById(id);
    return "redirect:" + this.URI + "/hien-thi";
  }

  @GetMapping("/view-update/{id}")
  public String viewUpdate(Model model, @PathVariable(name = "id") Integer id) {
    model.addAttribute(
        "nsx",
        this.nsxRepository.findById(id)
            .orElseThrow()
    );
    return this.URI + "/update";
  }

  @PostMapping("/update/{id}")
  public String update(Model model,
                       @PathVariable(name = "id") Integer id,
                       @ModelAttribute(name = "nsx") NSX nsx) {

    nsx.setId(id);
    if (this.validate(model, nsx)) {
      model.addAttribute("nsx", nsx);
      return this.URI + "/update";
    }
    this.nsxRepository.findById(id)
        .ifPresent(
            o -> {
              o.setMa(nsx.getMa());
              o.setTen(nsx.getTen());

              this.nsxRepository.save(o);
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
                    @ModelAttribute NSX nsx) {
    if (this.validate(model, nsx)) {
      model.addAttribute("nsx", nsx);
      return this.URI + "/add";
    }
    this.nsxRepository.save(nsx);
    return "redirect:" + this.URI + "/hien-thi";
  }

  private boolean validate(Model model, NSX nsx) {
    Map<String, List<String>> rerult = this.validator.validate(nsx)
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
        this.nsxRepository.checkExistMa(
            Objects.nonNull(nsx.getId())
                ? nsx.getId()
                : 0,
            nsx.getMa()
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
