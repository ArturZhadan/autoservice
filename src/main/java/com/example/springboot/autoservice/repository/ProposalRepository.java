package com.example.springboot.autoservice.repository;

import com.example.springboot.autoservice.model.Proposal;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProposalRepository extends JpaRepository<Proposal, Long> {
    @Query(value = "select p from Order o join o.proposals p where o.id = :id")
    List<Proposal> findAllProposalsByOrderId(Long id);

    @Query(value = "select p from Worker w join w.orders o join o.proposals p where w.id = :id")
    List<Proposal> findAllProposalsByWorkerId(Long id);
}
