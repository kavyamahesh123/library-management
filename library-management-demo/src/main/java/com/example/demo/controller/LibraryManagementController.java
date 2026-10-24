package com.example.demo.controller;

import static com.example.demo.constants.Constants.*;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.AuthorResponse;
import com.example.demo.dto.BookResponse;
import com.example.demo.dto.BorrowResponse;
import com.example.demo.dto.CreateAuthorRequest;
import com.example.demo.dto.CreateBookRequest;
import com.example.demo.dto.CreateBorrowRequest;
import com.example.demo.dto.CreateMemberRequest;
import com.example.demo.dto.MemberResponse;
import com.example.demo.service.LibraryManagementService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class LibraryManagementController {

	private final LibraryManagementService libraryManagementService;

	// AUTHOR Management API's
	public LibraryManagementController(LibraryManagementService libraryManagementService) {
		this.libraryManagementService = libraryManagementService;
	}

	private static final Logger logger = LoggerFactory.getLogger(LibraryManagementController.class);

	@PostMapping("/authors")
	public ResponseEntity<?> createAuthor(@Valid @RequestBody CreateAuthorRequest request) throws Exception {

		logger.info("Request to create an author...");
		AuthorResponse response = libraryManagementService.createAuthor(request);

		return ResponseEntity.status(HttpStatus.CREATED).body(Map.of(RESPONSE_CODE, HttpStatus.CREATED.value(), MESSAGE,
				SUCCESSFULLY_CREATED_AN_AUTHOR, RESPONSE_BODY, response));
	}

	@GetMapping("/authors")
	public ResponseEntity<?> getAllAuthors() throws Exception {

		logger.info("Request to get all authors...");
		List<AuthorResponse> response = libraryManagementService.getAllAuthors();

		return ResponseEntity.status(HttpStatus.OK).body(Map.of(RESPONSE_CODE, HttpStatus.OK.value(), MESSAGE,
				SUCCESSFULLY_FETCHED_ALL_AUTHOR, RESPONSE_BODY, response));
	}

	@GetMapping("/authors/{id}")
	public ResponseEntity<?> getAuthorById(@PathVariable Long id) throws Exception {

		logger.info("Request to get author by ID: {}", id);
		AuthorResponse response = libraryManagementService.getAuthorById(id);

		return ResponseEntity.status(HttpStatus.OK).body(Map.of(RESPONSE_CODE, HttpStatus.OK.value(), MESSAGE,
				SUCCESSFULLY_FETCHED_AN_AUTHOR + id, RESPONSE_BODY, response));
	}

	@PutMapping("/authors/{id}")
	public ResponseEntity<Map<String, Object>> updateAuthor(@PathVariable Long id,
			@Valid @RequestBody CreateAuthorRequest request) throws Exception {

		logger.info("Request to update author with ID: {}", id);
		AuthorResponse response = libraryManagementService.updateAuthor(id, request);

		return ResponseEntity.ok(Map.of(RESPONSE_CODE, HttpStatus.OK.value(), MESSAGE,
				SUCCESSFULLY_UPDATED_AN_AUTHOR + id, RESPONSE_BODY, response));
	}

	// -----------------BOOK
	// ManagementAPI's-----------------------------------------

	@PostMapping("/books")
	public ResponseEntity<?> createNewBook(@Valid @RequestBody CreateBookRequest request) throws Exception {

		logger.info("Request to create a new book...");
		BookResponse response = libraryManagementService.createNewBook(request);

		return ResponseEntity.status(HttpStatus.CREATED).body(Map.of(RESPONSE_CODE, HttpStatus.CREATED.value(), MESSAGE,
				SUCCESSFULLY_CREATED_NEW_BOOK, RESPONSE_BODY, response));
	}

	@GetMapping("/books")
	public ResponseEntity<?> getAllBooks() throws Exception {

		logger.info("Request to get all books...");
		List<BookResponse> response = libraryManagementService.getAllBooks();

		return ResponseEntity.status(HttpStatus.OK).body(Map.of(RESPONSE_CODE, HttpStatus.OK.value(), MESSAGE,
				SUCCESSFULLY_FTECHED_ALL_BOOKS, RESPONSE_BODY, response));
	}

	@GetMapping("/books/{id}")
	public ResponseEntity<?> getBookById(@PathVariable Long id) throws Exception {

		logger.info("Request to get a book by ID: {}", id);
		BookResponse response = libraryManagementService.getBookById(id);

		return ResponseEntity.status(HttpStatus.OK).body(Map.of(RESPONSE_CODE, HttpStatus.OK.value(), MESSAGE,
				SUCCESSFULLY_FTECHED_A_BOOK + id, RESPONSE_BODY, response));
	}

	@PutMapping("/books/{id}")
	public ResponseEntity<?> updateBook(@PathVariable Long id, @Valid @RequestBody CreateBookRequest request)
			throws Exception {

		logger.info("Request to update a book with ID: {}", id);
		BookResponse response = libraryManagementService.updateBook(id, request);

		return ResponseEntity.ok(Map.of(RESPONSE_CODE, HttpStatus.OK.value(), MESSAGE, SUCCESSFULLY_UPDATED_A_BOOK + id,
				RESPONSE_BODY, response));
	}

	@DeleteMapping("/books/{id}")
	public ResponseEntity<?> deletebook(@PathVariable Long id) throws Exception {

		logger.info("Request to delete a book with ID: {}", id);
		libraryManagementService.deletebook(id);

		return ResponseEntity
				.ok(Map.of(RESPONSE_CODE, HttpStatus.OK.value(), MESSAGE, SUCCESSFULLY_DELETED_A_BOOK + id));
	}

	// ------------------------------------ MEMBER Management
	// API's----------------------------------------------------------------------------------------------------

	@PostMapping("/members")
	public ResponseEntity<?> createNewMember(@Valid @RequestBody CreateMemberRequest request) throws Exception {

		logger.info("Request to register a new member...");
		MemberResponse response = libraryManagementService.createNewMember(request);

		return ResponseEntity.status(HttpStatus.CREATED).body(Map.of(RESPONSE_CODE, HttpStatus.CREATED.value(), MESSAGE,
				SUCCESSFULLY_REGISTERED_A_MEMBER, RESPONSE_BODY, response));
	}

	@GetMapping("/members")
	public ResponseEntity<?> getAllMembers() throws Exception {

		logger.info("Request to get all members...");
		List<MemberResponse> response = libraryManagementService.getAllMembers();

		return ResponseEntity.status(HttpStatus.OK).body(Map.of(RESPONSE_CODE, HttpStatus.OK.value(), MESSAGE,
				SUCCESSFULLY_FETCHED_ALL_MEMBER, RESPONSE_BODY, response));
	}

	@GetMapping("/members/{id}")
	public ResponseEntity<?> getMemberById(@PathVariable Long id) throws Exception {

		logger.info("Request to get a member info. by ID: {}", id);
		MemberResponse response = libraryManagementService.getMemberById(id);

		return ResponseEntity.status(HttpStatus.OK).body(Map.of(RESPONSE_CODE, HttpStatus.OK.value(), MESSAGE,
				SUCCESSFULLY_FETCHED_MEMBER + id, RESPONSE_BODY, response));
	}

	@PutMapping("/members/{id}")
	public ResponseEntity<?> updateMember(@PathVariable Long id, @Valid @RequestBody CreateMemberRequest request)
			throws Exception {

		logger.info("Request to update a member details with ID: {}", id);
		MemberResponse response = libraryManagementService.updateMember(id, request);

		return ResponseEntity.ok(Map.of(RESPONSE_CODE, HttpStatus.OK.value(), MESSAGE, SUCCESSFULLY_UPDATED_MEMBER + id,
				RESPONSE_BODY, response));
	}

	@PostMapping("/borrows")
	public ResponseEntity<?> borrowNewBook(@Valid @RequestBody CreateBorrowRequest request) throws Exception {

		logger.info("Request to borrow a new book...");
		BorrowResponse response = libraryManagementService.borrowNewBook(request);

		return ResponseEntity.status(HttpStatus.CREATED).body(Map.of(RESPONSE_CODE, HttpStatus.CREATED.value(), MESSAGE,
				SUCCESSFULLY_BORROWED_BOOK, RESPONSE_BODY, response));
	}

	@PutMapping("/borrows/{id}/return")
	public ResponseEntity<?> returnBorrowedBook(@PathVariable Long id) throws Exception {

		logger.info("Request to return a book...");
		BorrowResponse response = libraryManagementService.returnBorrowedBook(id);

		return ResponseEntity.status(HttpStatus.CREATED).body(Map.of(RESPONSE_CODE, HttpStatus.CREATED.value(), MESSAGE,
				SUCCESSFULLY_RETURNED_BOOK, RESPONSE_BODY, response));
	}

	@GetMapping("/members/{id}/borrows")
	public ResponseEntity<?> getMemberBorrowHistory(@PathVariable(name = "id") Long memberId) {

		logger.info("Fetching borrow history for member ID: {}", memberId);
		List<BorrowResponse> history = libraryManagementService.getMemberBorrowHistory(memberId);

		return ResponseEntity.ok(Map.of(RESPONSE_CODE, HttpStatus.OK.value(), MESSAGE,
				SUCCESSFULLY_FETCHED_MEMBER_BORROW_HISTORY + memberId, RESPONSE_BODY, history));
	}
}
