package by.restaurant.entity;

import javax.persistence.*;

/**
 * Created by MIKL on 11.04.2017.
 */
@Entity
public class Token {
    private int tokenId;
    private String token;
    private long userId;
    private User userByUserId;

    @Id
    @Column(name = "token_id", nullable = false)
    public int getTokenId() {
        return tokenId;
    }

    public void setTokenId(int tokenId) {
        this.tokenId = tokenId;
    }

    @Basic
    @Column(name = "token", nullable = false, length = -1)
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Basic
    @Column(name = "user_id", nullable = false)
    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Token token1 = (Token) o;

        if (tokenId != token1.tokenId) return false;
        if (userId != token1.userId) return false;
        if (token != null ? !token.equals(token1.token) : token1.token != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = tokenId;
        result = 31 * result + (token != null ? token.hashCode() : 0);
        result = 31 * result + (int) (userId ^ (userId >>> 32));
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
    public User getUserByUserId() {
        return userByUserId;
    }

    public void setUserByUserId(User userByUserId) {
        this.userByUserId = userByUserId;
    }
}
