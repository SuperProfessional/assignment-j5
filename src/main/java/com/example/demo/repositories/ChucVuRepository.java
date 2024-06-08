package com.example.demo.repositories;

import com.example.demo.entities.ChucVu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ChucVuRepository extends JpaRepository<ChucVu, Integer> {

  @Query(value = """
      select count(cv) from ChucVu cv where cv.id <> :id and cv.ma like :ma
      """)
  int checkExistMa(@Param("id") Integer id, @Param("ma") String ma);
}
