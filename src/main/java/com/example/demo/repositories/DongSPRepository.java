package com.example.demo.repositories;

import com.example.demo.entities.DongSP;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DongSPRepository extends JpaRepository<DongSP, Integer> {

  @Query(value = """
      select count(d) from DongSP d where d.id <> :id and d.ma like :ma
      """)
  int checkExistMa(@Param("id") Integer id, @Param("ma") String ma);
}
