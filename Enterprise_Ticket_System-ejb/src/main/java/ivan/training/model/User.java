package ivan.training.model;

import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.persistence.NamedQuery;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Entity
@Table ( name = "t_users" , uniqueConstraints = @UniqueConstraint(columnNames = {"username"}))
@NamedQueries({@NamedQuery(name="User.findByUsername", query="Select u FROM User u WHERE u.username = :username")})
public class User implements Serializable {
	
	private static final long serialVersionUID = 2038224043966259335L;
	private static final Logger logger = LoggerFactory.getLogger(User.class);

	@NotNull
	@Column(name="username", length = 40)
	private String username;
	
	@NotNull
	@Size(min = 4, max = 25, message = "1-25 letters and spaces")
	@Column(name="password")
	private String password;
	

	@Id
	@GeneratedValue
	private int userId;
	
	public User(){
	}
	
	public User(String username) {
		this.username = username;
	}

	public User(String username, int userId) {
		this.username = username;
		this.userId = userId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		
		return password;
	}

	public void setPassword(String password) {
		this.password = hashPassword(password);
	}
	
/**
 * Hashes user's passwords using SHA-256
 * @param password
 * @return string representation of the hash
 */
	private String hashPassword(String password) {
		MessageDigest sha256;
		try {
			sha256 = MessageDigest.getInstance("SHA-256");
			byte[] passBytes = password.getBytes();
			byte[] passHash = sha256.digest(passBytes);
			return passHash.toString();
		} catch (NoSuchAlgorithmException e) {
			logger.error("Can not hash the password", e);
		}
		return password;	
	}

}
