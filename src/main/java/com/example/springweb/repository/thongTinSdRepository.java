package com.example.springweb.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.example.springweb.entity.thongTinSd;

@Repository
public interface thongTinSdRepository extends CrudRepository<thongTinSd, Long> {}