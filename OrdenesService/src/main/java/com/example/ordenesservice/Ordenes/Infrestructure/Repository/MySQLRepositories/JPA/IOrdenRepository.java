package com.example.ordenesservice.Ordenes.Infrestructure.Repository.MySQLRepositories.JPA;

import com.example.ordenesservice.Ordenes.Infrestructure.Models.MySQLModels.OrdenModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IOrdenRepository extends JpaRepository<OrdenModel, String> {

}
