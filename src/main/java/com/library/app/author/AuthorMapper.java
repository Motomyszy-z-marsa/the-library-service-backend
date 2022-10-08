package com.library.app.author;

class AuthorMapper {

    private AuthorMapper() {
    }

    public static AuthorDto map(Author author) {
        return new AuthorDto(author.getId(), author.getName());
    }

    static Author map(Long id, AuthorDto dto) {
        var author = new Author(dto.name());
        author.setId(id);
        return author;
    }
}
