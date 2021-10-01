package br.com.meufarol.financeiro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.meufarol.financeiro.entity.OrderResponse;

@Repository
public interface OrderResponseRepository extends JpaRepository<OrderResponse, String>{

	@Query (value = "SELECT o "
			+ "FROM OrderResponse o "
			+ "WHERE o.id = :id ")
	OrderResponse buscarOrderResponseSalva(String id);

}
