package com.wipro.spring.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import com.wipro.spring.web.dao.Offer;
import com.wipro.spring.web.dao.OffersDao;



@Service
public class OffersService {
	
	
	private OffersDao offersDao;
	
	
	@Autowired
	public void setOffersDao(OffersDao offersDao) {
		this.offersDao = offersDao;
	}



	public List<Offer> getCurrent(){
		return offersDao.getOffers();
	}


	@Secured({"ROLE_USER", "ROLE_ADMIN"})
	public void create(Offer offer) {
		offersDao.saveOrUpdate(offer); 
	}



	public boolean hasOffer(String username) {
		// TODO Auto-generated method stub
		
		if(username == null) {
			return false;
		}
		
		List<Offer> offers = offersDao.getOffers(username);
		
		if(offers.size() == 0) {
			return false;
		} 
		
		return true;
		
	}



	public Offer getOffer(String username) {
		
		if(username == null) {
			return null;
		}
		
		List<Offer> offers = offersDao.getOffers(username);
		
		if(offers.size() == 0) {
			return null;
		}

		return offers.get(0);
	}



	public void saveOrUpdate(Offer offer) {
		
		offersDao.saveOrUpdate(offer);
	}



	public void delete(int id) {
		offersDao.delete(id);
	}

	
}
