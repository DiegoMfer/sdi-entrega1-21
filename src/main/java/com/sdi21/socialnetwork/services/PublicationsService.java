package com.sdi21.socialnetwork.services;

import com.sdi21.socialnetwork.entities.Publication;
import com.sdi21.socialnetwork.entities.User;
import com.sdi21.socialnetwork.repositories.PublicationsRepository;
import com.sdi21.socialnetwork.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PublicationsService {

    @Autowired
    private PublicationsRepository publicationsRepository;

    @Autowired
    private UsersRepository usersRepository;

    public void addPublication(Publication publication){
        publicationsRepository.save(publication);
    }


    public Page<Publication> getPublicationsByEmail(Pageable pageable, String email){
        return publicationsRepository.findByUserEmail(pageable, email);
    }

    public Page<Publication> getPublications(Pageable pageable){

        return publicationsRepository.findAll(pageable);
    }

    public Page<Publication> getPublicationsByText(Pageable pageable, String searchText) {
        return publicationsRepository.findAll(pageable, '%'+searchText+'%');
    }

    public void setPublicationState(Long id, String publicationStatus) {

        usersRepository.setPublicationState(id, publicationStatus);
    }

    public void addRecommendation(Long publicationId, User user) {
        Publication publication = publicationsRepository.findById(publicationId).get();
        publication.addRecommendation(user);
        publicationsRepository.save(publication);
    }

    public Page<Publication> getPublicPublicationsByEmail(Pageable pageable, String email) {
        return publicationsRepository.findPublicByUserEmail(pageable, email);
    }
}
