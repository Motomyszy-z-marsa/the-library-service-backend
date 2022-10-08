package com.library.app.author;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ReadAuthorService {
    Page<AuthorDto> get(Pageable pageable);

    AuthorDto get(Long id);
}
