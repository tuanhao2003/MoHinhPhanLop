package com.example.springweb.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.example.springweb.entity.xuLy;

@Repository
public interface xuLyRepository extends CrudRepository<xuLy, Long> {}