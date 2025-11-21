package br.com.rodneybarreto.mybatch.repository;

import br.com.rodneybarreto.mybatch.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
