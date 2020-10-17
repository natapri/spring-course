package com.jrp.projectmanagement.dao;


import com.jrp.projectmanagement.entities.UserAccount;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserAccountRepository  extends PagingAndSortingRepository<UserAccount, Long> {
}
