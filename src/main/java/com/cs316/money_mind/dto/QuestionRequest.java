package com.cs316.money_mind.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class QuestionRequest {
    private String title;
    private String description;
    private Long userId;

}
