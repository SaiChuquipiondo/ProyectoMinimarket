package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.categoria;
import com.example.demo.entity.producto;

@Repository("productoRepository")
public interface ProductoRepository extends JpaRepository<producto, Integer> {

    @Query(value = "select * from categoria where estado = :estado", nativeQuery = true)
    List<categoria> ListarActivo(@Param("estado") Boolean estado);

    @Query(value = "select * from producto where id_categoria = :categoriaid", nativeQuery = true)
    List<producto> findByCategoriaId(@Param("categoriaid") int categoriaid);

}
