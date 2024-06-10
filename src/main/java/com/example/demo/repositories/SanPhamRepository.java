package com.example.demo.repositories;

import com.example.demo.entities.SanPham;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SanPhamRepository extends JpaRepository<SanPham, Integer> {

  @Query(value = """
      select count(sp) from SanPham sp where sp.id <> :id and sp.ma like :ma
      """)
  int checkExistMa(@Param("id") Integer id, @Param("ma") String ma);

}
