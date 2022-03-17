package com.sdi21.socialnetwork.services;

import com.sdi21.socialnetwork.entities.User;
import com.sdi21.socialnetwork.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
public class UsersService {

    @Autowired
    private UsersRepository usersRepository;

    @PostConstruct
    public void init(){
        usersRepository.save(new User("Default"));

    }

    public List<User> getUsers() {
        List<User> users = new ArrayList<User>();
        usersRepository.findAll().forEach(users::add);
        return users;
    }

    public Page<User> getUsersWithRole(Pageable pageable, String role) {
        return usersRepository.findAllByRole(pageable, role);
    }

    public User getUser(Long id) {
        return usersRepository.findById(id).get();
    }

    public User getUserByEmail(String email){
        return usersRepository.findByEmail(email);
    }

    public void addUser(User user) {
        usersRepository.save(user);
    }

    public void deleteUser(Long id) {
        usersRepository.deleteById(id);
    }

    public User getDefaultUser() {
        return usersRepository.findByEmail("Default");
    }

    public Page<User> searchUsersByEmailNameAndSurnameWithRole(
            Pageable pageable, String searchText, String role) {
        return usersRepository.searchByEmailNameAndSurnameWithRole(pageable, '%'+searchText+'%', role);
    }

    public void addFriend (User receiver, User sender) {
        //receiver.getFriends().add(sender);
        //sender.getFriends().add(receiver);
    }
}
