package com.library.app.author;

import org.springframework.stereotype.Service;

@Service
class DeleteAuthorServiceImpl implements DeleteAuthorService {

    private final AuthorRepository authorRepository;

    DeleteAuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public void delete(Long id) {
        authorRepository.deleteById(id);
    }
}
