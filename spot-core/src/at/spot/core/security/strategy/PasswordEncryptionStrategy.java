package at.spot.core.security.strategy;

public interface PasswordEncryptionStrategy {
	/**
	 * Encrypts the given password. Depending on the implementation, this might
	 * be a simple hash function or a very complex algorithm.
	 * 
	 * @param rawPassword
	 * @return
	 */
	String encryptPassword(String rawPassword);
}
