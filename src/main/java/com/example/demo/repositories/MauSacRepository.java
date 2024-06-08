package com.example.demo.repositories;

import com.example.demo.entities.MauSac;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MauSacRepository extends JpaRepository<MauSac, Integer> {

  @Query(value = """
      select count(ms) from MauSac ms where ms.id <> :id and ms.ma like :ma
      """)
  int checkExistMa(@Param("id") Integer id, @Param("ma") String ma);
}
