package com.example.demo.repositories;

import com.example.demo.entities.NSX;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface NSXRepository extends JpaRepository<NSX, Integer> {

  @Query(value = """
      select count(nsx) from NSX nsx where nsx.id <> :id and nsx.ma like :ma
      """)
  int checkExistMa(@Param("id") Integer id, @Param("ma") String ma);

}
