package com.example.demo.repositories;

import com.example.demo.entities.KhachHang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface KhachHangRepository extends JpaRepository<KhachHang, Integer> {

  @Query(value = """
      select count(kh) from KhachHang kh where kh.id <> :id and kh.ma like :ma
      """)
  int checkExistMa(@Param("id") Integer id, @Param("ma") String ma);
}
