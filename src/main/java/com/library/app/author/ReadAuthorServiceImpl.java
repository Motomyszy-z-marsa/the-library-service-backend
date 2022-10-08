package com.library.app.author;

import com.library.app.exception.ResourceNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
class ReadAuthorServiceImpl implements ReadAuthorService {

    private final AuthorRepository authorRepository;

    public ReadAuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public Page<AuthorDto> get(Pageable pageable) {
        return authorRepository.findAll(pageable)
                .map(AuthorMapper::map);
    }

    @Override
    public AuthorDto get(Long id) {
        return authorRepository.findById(id)
                .map(AuthorMapper::map)
                .orElseThrow(ResourceNotFoundException::new);
    }
}
