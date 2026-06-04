package com.oasis.OasisShop.repo;

import com.oasis.OasisShop.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
