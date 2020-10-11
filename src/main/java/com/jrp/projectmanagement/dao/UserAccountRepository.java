package com.jrp.projectmanagement.dao;


import com.jrp.projectmanagement.entities.UserAccount;
import org.springframework.data.repository.CrudRepository;

public interface UserAccountRepository  extends CrudRepository<UserAccount, Long> {
}
