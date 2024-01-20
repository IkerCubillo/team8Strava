package es.deusto.ingenieria.sd.auctions.client.controller;

import java.rmi.RemoteException;
import java.util.Date;
import java.time.LocalTime;
import java.util.List;


import es.deusto.ingenieria.sd.auctions.server.data.dto.ChallengeDTO;
import es.deusto.ingenieria.sd.auctions.server.data.dto.TrainingSessionDTO;
import es.deusto.ingenieria.sd.auctions.client.remote.ServiceLocator;

//This class implements Controller pattern.
public class MainController{
	
	//Reference to the Service Locator
	private ServiceLocator ServiceLocator;
	
	public MainController(ServiceLocator ServiceLocator) {
		this.ServiceLocator = ServiceLocator; 
	}

	public List<ChallengeDTO> getChallenges() {
		try {
			return this.ServiceLocator.getService().getChallenges();
		} catch (RemoteException e) {
			System.out.println("# Error getting all challenges: " + e);
			return null;
		}
	}
	
	public List<ChallengeDTO> getUserChallenges() {
		try {
			return this.ServiceLocator.getService().getUserChallenges();
		} catch (RemoteException e) {
			System.out.println("# Error getting all challenges: " + e);
			return null;
		}
	}

	public List<TrainingSessionDTO> getTrainingSessions(long token) {
		try {
			return this.ServiceLocator.getService().getTrainingSessions(token);
		} catch (RemoteException e) {
			System.out.println("# Error getting all training sessions: " + e);
			return null;
		}
	}

	public boolean setupDistanceChallenge(long token, String name, Date start, Date end, float metric, String sportType) {
		try {
			return this.ServiceLocator.getService().setupDistanceChallenge(token, name, start, end, metric, sportType);
		} catch (RemoteException e) {
			System.out.println("# Error setting up distance challenge: " + e);
			return false;
		}
	}
	
	public boolean setupActivityTimeChallenge(long token, String name, Date start, Date end, float metric, String sportType) {
		try {
			return this.ServiceLocator.getService().setupActivityTimeChallenge(token, name, start, end, metric, sportType);
		} catch (RemoteException e) {
			System.out.println("# Error setting up activity challenge: " + e);
			return false;
		}
	}
	
	public boolean acceptChallenge(long token, ChallengeDTO c) {
		try {
			return this.ServiceLocator.getService().acceptChallenge(token, c);
		} catch (RemoteException e) {
			System.err.println("# Error accepting challenge: " + e);	
			return false;
		}
	}
	
	public void createSession(long token, String title, String sport, float distance, Date startDate, LocalTime timeStart, float duration) {
		try {
			this.ServiceLocator.getService().createSession(token, title, sport, distance, startDate, timeStart, duration);;
		} catch (RemoteException e) {
			System.err.println("# Error creating training session: " + e);
		}
	}

	
}
