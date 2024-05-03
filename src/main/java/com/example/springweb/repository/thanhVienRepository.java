package com.example.springweb.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.example.springweb.entity.thanhVien;

@Repository
public interface thanhVienRepository extends CrudRepository<thanhVien, Long> {}