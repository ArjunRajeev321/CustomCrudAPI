package com.arjun.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arjun.entity.DemoEntity;
import com.arjun.repo.DemoRepo;

@RestController
@RequestMapping("/hello")
@CrossOrigin(origins = "http://localhost:4200")
public class DemoController {

	@Autowired
	private DemoRepo repo;

	@PostMapping("/world/{name}")
	public List<DemoEntity> saveData(@PathVariable("name") String name) {
		List<DemoEntity> lst = new ArrayList<>();
		DemoEntity ent = new DemoEntity();
		ent.setName(name);
		repo.save(ent);
		lst = repo.findAll();
		System.out.println("ADDED IN DB");
		return lst;
	}

	@GetMapping("/getAllResult/")
	public List<DemoEntity> getAllResult() {
		List<DemoEntity> lst = new ArrayList<>();
		DemoEntity ent = new DemoEntity();
		lst = repo.findAll();
		System.out.println("Got from DB");
		lst = lst.stream().sorted(Comparator.comparing(DemoEntity::getName)).collect(Collectors.toList());
		return lst;
	}

	@GetMapping("/getResult/{id}")
	public List<DemoEntity> getResult(@PathVariable("id") Long id) {
		List<DemoEntity> lst = new ArrayList<>();
		DemoEntity ent = new DemoEntity();
		Optional<DemoEntity> opt = repo.findById(id);
		if (opt.isPresent()) {
			lst.add(opt.get());
		} else {
			ent.setName("No Result");
			lst.add(ent);
		}

		System.out.println("Got from DB");
		return lst;
	}

	@PostMapping("/delete/{id}")
	public List<DemoEntity> delete(@PathVariable("id") Long id) {
		List<DemoEntity> lst = new ArrayList<>();
		lst = repo.findAll();
		DemoEntity ent = new DemoEntity();
		lst.stream().forEach(a -> {
			if (a.getId().equals(id)) {
				repo.deleteById(id);
			}
		});
		lst = repo.findAll();
		System.out.println("DELETED");

		return lst;
	}

	@PostMapping("/update/{id}/{name}")
	public List<DemoEntity> update(@PathVariable(name = "id") Long id, @PathVariable(name = "name") String name) {
		List<DemoEntity> lst = new ArrayList<>();
		DemoEntity ent = new DemoEntity();
		repo.findAll().stream().forEach(a -> {
			if (a.getId() == (id)) {
				ent.setId(id);
				ent.setName(name);
				repo.saveAndFlush(ent);
			}
		});
		lst = repo.findAll();
		return lst;
	}

}
