package at.spot.test.persistence;

import java.util.Collections;
import java.util.Locale;

import org.junit.Assert;
import org.junit.Test;

import at.spot.core.infrastructure.exception.ModelSaveException;
import at.spot.core.testing.AbstractIntegrationTest;
import at.spot.itemtype.core.catalog.Catalog;
import at.spot.itemtype.core.catalog.CatalogVersion;
import at.spot.itemtype.core.internationalization.LocalizationValue;
import at.spot.itemtype.core.user.User;
import at.spot.itemtype.core.user.UserAddress;
import at.spot.itemtype.core.user.UserGroup;

public class PersistenceIT extends AbstractIntegrationTest {

	@Override
	protected void prepareTest() {
		// TODO Auto-generated method stub
	}

	@Override
	protected void teardownTest() {
		// TODO Auto-generated method stub
	}

	/**
	 * The catalogVersion Default:Online already be available through the
	 * initial data. Therefore saving a second item will cause a uniqueness
	 * constraint violation.
	 */
	@Test(expected = ModelSaveException.class)
	public void testUniqueConstraintOfSubclass() {
		final Catalog catalog = modelService.get(Catalog.class,
				Collections.singletonMap(Catalog.PROPERTY_ID, "Default"));

		final CatalogVersion version = modelService.create(CatalogVersion.class);
		version.setCatalog(catalog);
		version.setId("Online");

		modelService.save(version);
	}

	@Test
	public void testBidirectionalOne2ManyRelationUpdateReferenceOnChildSideWithLoadedUser() throws Exception {
		final User user = modelService.get(User.class, Collections.singletonMap(User.PROPERTY_ID, "tester1"));

		final UserAddress address = modelService.create(UserAddress.class);
		address.setStreet("asf");
		user.getAddresses().add(address);

		modelService.save(user);

		final User loadedUser = modelService.get(User.class, user.getPk());

		Assert.assertEquals(loadedUser.getAddresses().iterator().next().getPk(), address.getPk());
	}

	// TODO: manytoone mapping not working yet
	@Test
	public void testBidirectionalOne2ManyRelationUpdateReferenceOnChildSide() throws Exception {
		final User user = modelService.create(User.class);
		user.setId("testUser");

		final UserAddress address = modelService.create(UserAddress.class);
		address.setStreet("asf");
		user.getAddresses().add(address);

		modelService.save(user);

		final User loadedUser = modelService.get(User.class, user.getPk());

		Assert.assertEquals(loadedUser.getAddresses().iterator().next().getPk(), address.getPk());
	}

	@Test
	public void testBidirectionalOne2ManyRelation() throws Exception {
		final User user = modelService.create(User.class);
		user.setId("testUser");

		final UserAddress address = modelService.create(UserAddress.class);
		address.setStreet("asf");
		address.setOwner(user);

		modelService.save(address);

		final User loadedUser = modelService.get(User.class, user.getPk());

		Assert.assertEquals(loadedUser.getAddresses().iterator().next().getPk(), address.getPk());
	}

	@Test
	public void testBidirectionalMany2ManyRelations() throws Exception {
		final UserGroup group = modelService.create(UserGroup.class);
		group.setId("testGroup");

		final User user = modelService.create(User.class);
		user.setId("testUser");
		user.getGroups().add(group);

		modelService.save(user);

		final User loadedUser = modelService.get(User.class, user.getPk());
		final UserGroup loadedGroup = modelService.get(UserGroup.class, group.getPk());

		Assert.assertEquals(loadedUser.getGroups().iterator().next().getPk(), loadedGroup.getPk());
		Assert.assertEquals(loadedUser.getPk(), loadedGroup.getMembers().iterator().next().getPk());
		Assert.assertEquals(user.getId(), loadedUser.getId());
		Assert.assertEquals(group.getPk(), loadedGroup.getPk());
	}

	@Test
	public void testQueryByExample() throws Exception {
		final LocalizationValue localization = modelService.create(LocalizationValue.class);
		localization.setId("test.key");
		localization.setLocale(Locale.ENGLISH);

		modelService.save(localization);

		final LocalizationValue example = new LocalizationValue();
		example.setId("test.key");
		example.setLocale(Locale.ENGLISH);

		final LocalizationValue loaded = modelService.getByExample(example);

		Assert.assertEquals(localization.getId(), loaded.getId());
	}

	@Test
	public void testSerialNumberGeneration() throws Exception {
		final User user = modelService.create(User.class);
		user.setShortName("test user");

		modelService.save(user);

		Assert.assertNotNull(user.getId());
	}

}
