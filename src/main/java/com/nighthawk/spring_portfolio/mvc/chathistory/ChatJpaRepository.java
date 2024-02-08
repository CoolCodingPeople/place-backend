package com.nighthawk.spring_portfolio.mvc.chathistory;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ChatJpaRepository extends JpaRepository<Chat, Long>{
	
	@Query(
            value = "SELECT * FROM chat WHERE person_id = ?1",
            nativeQuery = true)
	List<Chat> findAllChatsForPerson();

}
