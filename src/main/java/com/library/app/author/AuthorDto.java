package com.library.app.author;

import javax.validation.constraints.Size;

public record AuthorDto(
        Long id,
        @Size(min = 1, max = 250) String name
) {
}
