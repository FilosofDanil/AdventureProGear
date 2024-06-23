package com.example.adventureprogearjava.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ContentDTO {
    public ContentDTO(String source, Long productId) {
        this.source = source;
        this.productId = productId;
    }
    Long id;
    @NotBlank
    String source;
    @JsonIgnore
    Long productId;
    String selfLink;
}
