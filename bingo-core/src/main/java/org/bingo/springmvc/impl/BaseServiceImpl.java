package org.bingo.springmvc.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.bingo.hibernate.EntityDao;

public abstract class BaseServiceImpl implements ApplicationEventPublisherAware {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	protected EntityDao entityDao;
	
	protected ApplicationEventPublisher eventPublisher;

	public EntityDao getEntityDao() {
		return entityDao;
	}

	public void publish(ApplicationEvent event){
		eventPublisher.publishEvent(event);
	}

	public void setApplicationEventPublisher(ApplicationEventPublisher eventPublisher) {
		this.eventPublisher = eventPublisher;
	}
	
}
