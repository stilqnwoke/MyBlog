package develop.site.service.impl;

import develop.site.model.entity.Author;
import develop.site.repository.AuthorRepository;
import develop.site.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }


    public List<Author> list() {
        return authorRepository.findAllByOrderByLastNameAscFirstNameAsc();
    }
}
