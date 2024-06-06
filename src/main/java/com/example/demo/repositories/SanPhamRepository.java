package com.example.demo.repositories;

import com.example.demo.entities.CuaHang;
import com.example.demo.entities.SanPham;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SanPhamRepository extends JpaRepository<SanPham, Integer> {

}
