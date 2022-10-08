package com.library.app.author;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/authors")
class AuthorController {

    private final ReadAuthorService readAuthorService;
    private final WriteAuthorService writeAuthorService;
    private final DeleteAuthorService deleteAuthorService;

    AuthorController(
            ReadAuthorService readAuthorService,
            WriteAuthorService writeAuthorService,
            DeleteAuthorService deleteAuthorService) {
        this.readAuthorService = readAuthorService;
        this.writeAuthorService = writeAuthorService;
        this.deleteAuthorService = deleteAuthorService;
    }

    @PostMapping
    AuthorDto create(@Valid @RequestBody AuthorDto newAuthor) {
        return writeAuthorService.create(newAuthor);
    }

    @GetMapping
    Page<AuthorDto> get(@PageableDefault Pageable pageable) {
        return readAuthorService.get(pageable);
    }

    @GetMapping("/{id}")
    AuthorDto get(@PathVariable Long id) {
        return readAuthorService.get(id);
    }

    @PutMapping("/{id}")
    AuthorDto update(@PathVariable Long id, @Valid @RequestBody AuthorDto authorDto) {
        return writeAuthorService.update(id, authorDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void delete(@PathVariable Long id) {
        deleteAuthorService.delete(id);
    }
}
