package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.categoria;

@Repository("categoriaRepository")
public interface CategoriaRepository extends JpaRepository<categoria, Integer> {

	@Query(value = "select c.id, c.nombre, c.estado from categoria c", nativeQuery = true)
	List<categoria> listar();

	@Modifying
	@Transactional
	@Query(value = "INSERT INTO categoria (nombre, estado) VALUES (:nombre, :estado)", nativeQuery = true)
	void insertarCategoria(@Param("nombre") String nombre, @Param("estado") Boolean estado);

	@Modifying
	@Transactional
	@Query(value = "UPDATE categoria set nombre = :nombre, estado = :estado where id = :id", nativeQuery = true)
	void editarCategoria(@Param("id") int id, @Param("nombre") String nombre, @Param("estado") Boolean estado);
}
