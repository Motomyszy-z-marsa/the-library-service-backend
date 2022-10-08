package com.library.app.author;

public interface WriteAuthorService {
    AuthorDto create(AuthorDto newAuthor);

    AuthorDto update(Long id, AuthorDto authorDto);
}
