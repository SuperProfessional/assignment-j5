package com.example.demo.controller;

import com.example.demo.entities.SanPham;
import com.example.demo.repositories.SanPhamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "san-pham")
@RequiredArgsConstructor
public class SanPhamController {

  private final SanPhamRepository sanPhamRepository;

  private final String URI = "/san-pham";

  private final Integer PAGE_SIZE = 5;

  private final Integer PAGE_NUMBER = 0;

  private Pageable pageable = PageRequest.of(PAGE_NUMBER, PAGE_SIZE);

  @GetMapping("/hien-thi")
  public String hienThi(
      Model model,
      @RequestParam(name = "pageNumber", defaultValue = "0") Integer pageNumber) {
    if (pageNumber > 0) {
      pageable.withPage(pageNumber - 1);
    }
    Page<SanPham> sanPhamPage = this.sanPhamRepository.findAll(pageable);
    model.addAttribute("sanPhamPage", sanPhamPage);

    return this.URI + "/hien-thi";
  }

}
