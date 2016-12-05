package at.spot.core.security.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import at.spot.core.infrastructure.exception.ModelSaveException;
import at.spot.core.infrastructure.exception.ModelValidationException;
import at.spot.core.infrastructure.service.ModelService;
import at.spot.core.infrastructure.service.UserService;
import at.spot.core.infrastructure.service.impl.AbstractService;
import at.spot.core.model.user.User;
import at.spot.core.persistence.exception.ModelNotUniqueException;
import at.spot.core.security.strategy.PasswordEncryptionStrategy;

@Service
public class DefaultAuthenticationService extends AbstractService implements AuthenticationService {

	@Autowired
	protected ModelService modelService;

	@Autowired
	protected PasswordEncryptionStrategy passwordEncryptionStrategy;

	@Autowired
	protected UserService userService;

	@Override
	public User getAuthenticatedUser(final String name, final String encryptedPassword) {
		final User user = userService.getUser(name);

		if (user != null) {
			if (StringUtils.equals(user.password, encryptedPassword)) {
				return user;
			}
		}

		return null;
	}

	@Override
	public void setPassword(final User user, final String rawPassword) throws ModelSaveException {
		user.password = encryptPassword(rawPassword);

		try {
			modelService.save(user);
		} catch (ModelSaveException | ModelNotUniqueException | ModelValidationException e) {
			throw new ModelSaveException(e.getMessage(), e);
		}
	}

	@Override
	public String encryptPassword(final String rawPassword) {
		return passwordEncryptionStrategy.encryptPassword(rawPassword);
	}
}
