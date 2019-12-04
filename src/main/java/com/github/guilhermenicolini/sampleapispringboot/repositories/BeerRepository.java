package com.github.guilhermenicolini.sampleapispringboot.repositories;

import com.github.guilhermenicolini.sampleapispringboot.domain.Beer;
import com.github.guilhermenicolini.sampleapispringboot.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BeerRepository extends JpaRepository<Beer, String> {

    List<Beer> findAllByUserOrderBySampledDesc(User user);

}