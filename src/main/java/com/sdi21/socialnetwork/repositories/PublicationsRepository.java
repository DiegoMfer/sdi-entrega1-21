package com.sdi21.socialnetwork.repositories;

import com.sdi21.socialnetwork.entities.User;
import org.springframework.data.repository.CrudRepository;

public interface PublicationsRepository extends CrudRepository<User,Long> {
}