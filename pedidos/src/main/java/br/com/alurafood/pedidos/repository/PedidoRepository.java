package br.com.alurafood.pedidos.repository;

import br.com.alurafood.pedidos.model.Pedido;
import br.com.alurafood.pedidos.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("UPDATE Pedido p SET p.status = :status WHERE p = :pedido")
    void atualizaStatus(Status status, Pedido pedido);

    @Query(value = "SELECT p FROM Pedido p LEFT JOIN FETCH p.itens WHERE p.id = :id")
    Optional<Pedido> porIdComItens(Long id);


}
