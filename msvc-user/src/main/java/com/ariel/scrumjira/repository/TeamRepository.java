package com.ariel.scrumjira.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ariel.scrumjira.entity.Team;


public interface TeamRepository extends JpaRepository<Team, UUID> {
	
	List<Team>findByTeamKey(String teamKey);
	
	List<Team>findByUsername(String username);
	
	boolean existsByTeamKey(String teamKey);
	
	Optional<Team>findByTeamKeyAndUsername(String teamKey, String username);
	
	@Modifying
	@Query("UPDATE Team t SET t.active = false WHERE t.teamKey = :teamKey AND t.username = :username")
	void deactivateUsernameInTeam(@Param("teamKey") String teamKey, @Param("username") String username);

	@Modifying
	@Query("UPDATE Team t SET t.active = false WHERE t.teamKey = :teamKey")
	void deactivateTeam(@Param("teamKey") String teamKey);

}
