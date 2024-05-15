package com.example.springweb.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.example.springweb.entity.thietBi;
import java.util.List;

@Repository
public interface thietBiRepository extends CrudRepository<thietBi, Long> {
    List<thietBi> findByNameContaining(String tenTB);

    public Object findById(int id);
}