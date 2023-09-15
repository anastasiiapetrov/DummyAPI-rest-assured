package dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter@NoArgsConstructor@AllArgsConstructor
public class User {
    private String id;
    private String title;
    private String firstName;
    private String lastName;
    private String picture;
}
