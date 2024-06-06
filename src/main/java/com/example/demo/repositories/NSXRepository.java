package com.example.demo.repositories;

import com.example.demo.entities.NSX;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NSXRepository extends JpaRepository<NSX, Integer> {

}
