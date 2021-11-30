package com.cs316.money_mind.dto.response.auth;

import lombok.*;

/**
 * AuthResponse
 *
 * @author Sainjargal Ishdorj
 **/

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class AuthResponse {

    private String accessToken;

    private String refreshToken;

}
