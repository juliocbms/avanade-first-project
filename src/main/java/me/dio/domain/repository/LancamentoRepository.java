package me.dio.domain.repository;

import me.dio.domain.model.entity.Lancamento;
import me.dio.domain.model.enums.StatusLancamento;
import me.dio.domain.model.enums.TipoLancamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface LancamentoRepository extends JpaRepository<Lancamento, Long> {

    @Query(value =
            " select COALESCE(sum(l.valor), 0) from Lancamento l join l.user u "
                    + " where u.id = :idUsuario and l.tipo = :tipo and l.status = :status" )
    BigDecimal obterSaldoPorTipoLancamentoEUsuarioEStatus(
            @Param("idUsuario") Long id,
            @Param("tipo") TipoLancamento tipo,
            @Param("status") StatusLancamento status);


}
