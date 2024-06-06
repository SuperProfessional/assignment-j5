package com.example.demo.repositories;

import com.example.demo.entities.ChiTietSP;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChiTietSPRepository extends JpaRepository<ChiTietSP, Integer> {

}
