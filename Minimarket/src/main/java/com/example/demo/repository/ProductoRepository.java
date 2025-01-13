package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Categoria;
import com.example.demo.entity.Producto;

@Repository("productoRepository")
public interface ProductoRepository extends JpaRepository<Producto, Integer> {

    @Query(value = "select * from categoria where estado = :estado", nativeQuery = true)
    List<Categoria> ListarActivo(@Param("estado") Boolean estado);

    @Query(value = "select * from producto where id_categoria = :categoriaid", nativeQuery = true)
    List<Producto> findByCategoriaId(@Param("categoriaid") int categoriaid);

    List<Producto> findByStockBetween(int stock1, int stock2);

}