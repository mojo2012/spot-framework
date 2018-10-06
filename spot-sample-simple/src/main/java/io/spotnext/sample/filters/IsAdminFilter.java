package io.spotnext.sample.filters;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import io.spotnext.core.infrastructure.exception.AuthenticationException;
import io.spotnext.core.infrastructure.service.UserService;
import io.spotnext.core.management.support.AuthenticationFilter;
import io.spotnext.itemtype.core.user.User;
import io.spotnext.itemtype.core.user.UserGroup;
import spark.Request;
import spark.Response;

/**
 * Checks if the current user is in the "admin" usergroup.
 */
@Service
public class IsAdminFilter implements AuthenticationFilter {

	@Resource
	private UserService<User, UserGroup> userService;

	@Override
	public void handle(final Request request, final Response response) throws AuthenticationException {
		final User currentUser = userService.getCurrentUser();

		if (currentUser != null && userService.isUserInGroup(currentUser.getId(), "admin")) {
			request.attribute("isLoggedIn", false);
			response.redirect("/");
		}

		request.attribute("isLoggedIn", true);
	}
}