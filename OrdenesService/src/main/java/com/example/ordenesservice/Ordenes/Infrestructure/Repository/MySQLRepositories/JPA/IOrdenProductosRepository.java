package com.example.ordenesservice.Ordenes.Infrestructure.Repository.MySQLRepositories.JPA;

import com.example.ordenesservice.Ordenes.Infrestructure.Models.MySQLModels.OrdenProductosModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IOrdenProductosRepository extends JpaRepository<OrdenProductosModel, String> {
    @Query(value = "SELECT * FROM ordenes_productos WHERE orden_id = :ordenId ;", nativeQuery = true)
    List<OrdenProductosModel> findByOrdenId(String ordenId);
}
