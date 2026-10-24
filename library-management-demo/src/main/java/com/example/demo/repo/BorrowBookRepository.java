package com.example.demo.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.BorrowRecord;
import com.example.demo.entities.BorrowStatus;

@Repository
public interface BorrowBookRepository extends JpaRepository<BorrowRecord, Long> {

	long countByMemberIdAndStatusIn(Long memberId, List<BorrowStatus> statuses);

	boolean existsByMemberIdAndBookIdAndStatusIn(Long memberId, Long bookId, List<BorrowStatus> statuses);

	List<BorrowRecord> findByMemberId(Long memberId);
	
	boolean existsByBookId(Long bookId);
	
	void deleteByBookId(Long bookId);
}