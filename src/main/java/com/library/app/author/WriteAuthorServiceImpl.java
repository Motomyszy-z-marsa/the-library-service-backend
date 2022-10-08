package com.library.app.author;

import com.library.app.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

@Service
class WriteAuthorServiceImpl implements WriteAuthorService {

    private final AuthorRepository authorRepository;

    public WriteAuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public AuthorDto create(AuthorDto newAuthor) {
        return AuthorMapper.map(authorRepository.save(AuthorMapper.map(null, newAuthor)));
    }

    @Override
    public AuthorDto update(Long id, AuthorDto authorDto) {
        if (!authorRepository.existsById(id)) {
            throw new ResourceNotFoundException();
        }
        return AuthorMapper.map(
                authorRepository.save(
                        AuthorMapper.map(id, authorDto)
                )
        );
    }
}
