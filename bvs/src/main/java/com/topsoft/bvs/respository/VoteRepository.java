package com.topsoft.bvs.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.topsoft.bvs.entity.Vote;

@Repository
public interface VoteRepository  extends JpaRepository<Vote,Long>{

}
