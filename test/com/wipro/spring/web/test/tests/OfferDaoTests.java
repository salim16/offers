package com.wipro.spring.web.test.tests;

import static org.junit.Assert.*;

import java.util.List;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.wipro.spring.web.dao.Offer;
import com.wipro.spring.web.dao.OffersDao;
import com.wipro.spring.web.dao.User;
import com.wipro.spring.web.dao.UsersDao;

@ActiveProfiles("dev")
@ContextConfiguration(locations = { "classpath:com/wipro/spring/web/config/dao-context.xml",
		"classpath:com/wipro/spring/web/config/security-context.xml",
		"classpath:com/wipro/spring/web/test/config/datasource.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class OfferDaoTests {

	@Autowired
	private OffersDao offersDao;

	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private UsersDao usersDao;
	
	
	private User user1 = new User("johnwpurcell", "John Purcell", "hellothere", "john@caveofprogramming.com", true,
			"ROLE_USER");
	private User user2 = new User("richardhannay", "Richard hannay", "the39steps", "richard@caveofprogramming.com", true,
			"ROLE_ADMIN");
	private User user3 = new User("suetheviolinist", "Sue Black", "iloveviolins", "sue@caveofprogramming.com", true,
			"ROLE_USER");
	private User user4 = new User("rogerblake", "Rog Blake", "liberator", "rog@caveofprogramming.com", false,
			"user");
	
	private Offer offer1 = new Offer(user1, "This is a test Offer");
	private Offer offer2 = new Offer(user1, "This is another test Offer");
	private Offer offer3 = new Offer(user2, "This is yet another test Offer");
	private Offer offer4 = new Offer(user3, "This is a test Offer once again");
	private Offer offer5 = new Offer(user3, "Here is an interesting offer of some kind");
	private Offer offer6 = new Offer(user3, "This is just a test Offer");
	private Offer offer7 = new Offer(user4, "This is a test Offer for a user that is not enabled");
	

	@Before
	public void init() {

		JdbcTemplate jdbc = new JdbcTemplate(dataSource);

		jdbc.execute("delete from offers");
		jdbc.execute("delete from messages");
		jdbc.execute("delete from users");

	}
	
	@Test
	public void testUpdate() {
		usersDao.create(user1);
		usersDao.create(user2);
		usersDao.create(user3);
		usersDao.create(user4);
		
		offersDao.saveOrUpdate(offer1);
		offersDao.saveOrUpdate(offer2);
		offersDao.saveOrUpdate(offer3);
		offersDao.saveOrUpdate(offer4);
		offersDao.saveOrUpdate(offer5);
		offersDao.saveOrUpdate(offer6);
		offersDao.saveOrUpdate(offer7);
		
		List<Offer> offers1 = offersDao.getOffers(user2.getUsername());
		
		Offer offer = offers1.get(0);
		
		System.out.println(offer);
		
		offer.setText("This is an updated offer");
	
		offersDao.saveOrUpdate(offer);
		System.out.println(offer);
		
		List<Offer> offers2 = offersDao.getOffers(user2.getUsername());
		
		Offer retrieved = offersDao.getOffer(offers2.get(0).getId());  
		assertEquals("Retrieved Offer should be updated", offer, retrieved);
		System.out.println(retrieved.getText()); // To print it in console, if any doubts
		
	}
	
	@Test
	public void testDelete() {
		usersDao.create(user1);
		usersDao.create(user2);
		usersDao.create(user3);
		usersDao.create(user4);
		
		offersDao.saveOrUpdate(offer1);
		offersDao.saveOrUpdate(offer2);
		offersDao.saveOrUpdate(offer3);
		offersDao.saveOrUpdate(offer4);
		offersDao.saveOrUpdate(offer5);
		offersDao.saveOrUpdate(offer6);
		offersDao.saveOrUpdate(offer7);
		
		List<Offer> offers1 = offersDao.getOffers(user1.getUsername());
		
        Offer retrieved1 = offersDao.getOffer(offers1.get(1).getId());
		assertNotNull("Offer with ID" + retrieved1.getId() + "should not be null", retrieved1);
		
		offersDao.delete(offers1.get(1).getId());
		
		//Offer retrieved2 = offersDao.getOffer(offers1.get(1).getId());
		//assertNull("Offer with ID" + retrieved2.getId() + "should be null", retrieved2);
	}
	
	@Test
	public void testCreateRetrieve() {
		usersDao.create(user1);
		usersDao.create(user2);
		usersDao.create(user3);
		usersDao.create(user4);
		
		offersDao.saveOrUpdate(offer1);
		
		List<Offer> offers1 = offersDao.getOffers();
		assertEquals("Should be 6 Offers for enabled users", 1, offers1.size());
		
		assertEquals("Retrieved offer should be equal to inserted offer", offer1, offers1.get(0));
		
		offersDao.saveOrUpdate(offer2);
		offersDao.saveOrUpdate(offer3);
		offersDao.saveOrUpdate(offer4);
		offersDao.saveOrUpdate(offer5);
		offersDao.saveOrUpdate(offer6);
		offersDao.saveOrUpdate(offer7);
		
		List<Offer> offers2 = offersDao.getOffers();
		assertEquals("Should be 6 Offers for enabled users", 6, offers2.size());
		
	}
	
	@Test
	public void testGetUsername() {
		
		usersDao.create(user1);
		usersDao.create(user2);
		usersDao.create(user3);
		usersDao.create(user4);
		
		offersDao.saveOrUpdate(offer1);
		offersDao.saveOrUpdate(offer2);
		offersDao.saveOrUpdate(offer3);
		offersDao.saveOrUpdate(offer4);
		offersDao.saveOrUpdate(offer5);
		offersDao.saveOrUpdate(offer6);
		offersDao.saveOrUpdate(offer7);
		
		List<Offer> offers1 = offersDao.getOffers(user3.getUsername());
		assertEquals("Should be 3 offers for this user", 3, offers1.size());
		
		List<Offer> offers2 = offersDao.getOffers("dcsvsvsvds");
		assertEquals("Should be 0 offers for this user", 0, offers2.size());
		
		List<Offer> offers3 = offersDao.getOffers(user2.getUsername());
		assertEquals("Should be 1 offers for this user", 1, offers3.size());
			
	}
	
	@Test
	public void testGetById() {
		usersDao.create(user1);
		usersDao.create(user2);
		usersDao.create(user3);
		usersDao.create(user4);
		
		offersDao.saveOrUpdate(offer1);
		offersDao.saveOrUpdate(offer2);
		offersDao.saveOrUpdate(offer3);
		offersDao.saveOrUpdate(offer4);
		offersDao.saveOrUpdate(offer5);
		offersDao.saveOrUpdate(offer6);
		offersDao.saveOrUpdate(offer7);
		
		List<Offer> offers1 = offersDao.getOffers(user1.getUsername());
		
		Offer retrieved1 = offersDao.getOffer(offers1.get(0).getId());
		assertEquals("Offers Should match", offer1, retrieved1);
			
	}
	
	
	
}
