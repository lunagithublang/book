package dev.arena.book_network.dto.account;

import dev.arena.book_network.dto.BaseResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class AccountResponse extends BaseResponse {
    private String firstName;
    private String lastName;
    private String email;
}
