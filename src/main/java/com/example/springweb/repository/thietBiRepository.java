package com.example.springweb.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.example.springweb.entity.thietBi;

@Repository
public interface thietBiRepository extends CrudRepository<thietBi, Long> {}