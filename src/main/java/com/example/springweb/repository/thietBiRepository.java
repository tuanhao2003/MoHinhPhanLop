package com.example.springweb.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.example.springweb.entity.thietBi;
import java.util.ArrayList;

@Repository
public interface thietBiRepository extends CrudRepository<thietBi, Long> {
    ArrayList<thietBi> findByTentbLike(String tenTB);

    public thietBi findById(int id);
}