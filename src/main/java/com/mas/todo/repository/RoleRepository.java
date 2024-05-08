package com.mas.todo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mas.todo.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(String name);

}
