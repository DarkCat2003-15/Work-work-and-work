package com.example.cajero.repository;

import com.example.cajero.entity.Transaccion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TransaccionRepository extends JpaRepository<Transaccion, Long> {

    List<Transaccion> findAllByOrderByFechaDesc();

    List<Transaccion> findByCuentaIdOrderByFechaDesc(Long cuentaId);

    @Query("select coalesce(avg(t.monto), 0) from Transaccion t where t.tipo = :tipo")
    Double promedioPorTipo(String tipo);

    @Query("select coalesce(avg(t.monto), 0) from Transaccion t where t.tipo = :tipo and month(t.fecha) = :mes and year(t.fecha) = :year")
    Double promedioPorTipoMesYear(String tipo, int mes, int year);
}
