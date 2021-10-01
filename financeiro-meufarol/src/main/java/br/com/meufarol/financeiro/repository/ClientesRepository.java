package br.com.meufarol.financeiro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.meufarol.financeiro.entity.Cliente;

@Repository
public interface ClientesRepository extends JpaRepository<Cliente, Integer>{

}
