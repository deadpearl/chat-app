package kz.pearl.chat_service.entity;

import javax.persistence.*;

import lombok.*;


@Getter
@Setter
@Entity
@Table(name = "_session")
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Session {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "token")
    private String token;

    @Column(name = "token_create_date")
    private String tokenCreateDate;

    @Column(name = "token_expire_date")
    private String tokenExpireDate;

}
