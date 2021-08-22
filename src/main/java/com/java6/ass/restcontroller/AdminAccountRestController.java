package com.java6.ass.restcontroller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.java6.ass.dao.RoleDAO;
import com.java6.ass.dto.AccountDTO;
import com.java6.ass.entity.Role;
import com.java6.ass.servic.AccountServic;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/admin/api/account")

public class AdminAccountRestController {

	@Autowired
	AccountServic aServic;
	@Autowired
	RoleDAO r;
	@PreAuthorize("hasRole('DIRE')")
	@GetMapping("/table")
	public ResponseEntity<Page<AccountDTO>> table(@RequestParam("page") Optional<Integer> page){
		return ResponseEntity.ok(aServic.getAllAccountDtoByPage(page, 12));
	}
	@PreAuthorize("hasRole('DIRE')")
	@DeleteMapping("/delete/{username}")
	public String delete(@PathVariable("username") String username) {
		aServic.deleteAccount(username);
		return "ok";
	}
	
	@PreAuthorize("hasRole('DIRE')")
	@PutMapping("/update")
	public String delete(@RequestBody AccountDTO account) {
		return "ok";
	}
	
	@PreAuthorize("hasRole('DIRE')")
	@GetMapping("/role")
	public ResponseEntity<List<Role>> role() {
		return ResponseEntity.ok(r.findAll());
	}
	
}
