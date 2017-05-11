/* Copyright c 2005-2012.
 * Licensed under GNU  LESSER General Public License, Version 3.
 * http://www.gnu.org/licenses
 */
package org.bingo.hibernate.support;

import org.hibernate.DuplicateMappingException;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Mappings;
import org.hibernate.cfg.SettingsFactory;
import org.hibernate.mapping.Collection;
import org.hibernate.mapping.PersistentClass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Provide overriderable mapping in sessionFactory
 * 
 * @author chaostone
 */
@SuppressWarnings("serial")
public class OverrideConfiguration extends Configuration {

	private static Logger logger = LoggerFactory.getLogger(OverrideConfiguration.class);

	public OverrideConfiguration() {
		super();
		this.metadataSourceQueue = new StmartMetadataSourceQueue();
	}

	public OverrideConfiguration(SettingsFactory settingsFactory) {
		super(settingsFactory);
		this.metadataSourceQueue = new StmartMetadataSourceQueue();
	}

	@Override
	public Mappings createMappings() {
		return new OverrideMappings();
	}

	protected class OverrideMappings extends MappingsImpl {
		@SuppressWarnings("unchecked")
		@Override
		public void addClass(PersistentClass persistentClass) throws DuplicateMappingException {
			String entityName = persistentClass.getEntityName();
			PersistentClass old = (PersistentClass) classes.get(entityName);
			if (old == null) {
				classes.put(entityName, persistentClass);
			} else if (old.getMappedClass().isAssignableFrom(persistentClass.getMappedClass())) {
				classes.put(entityName, persistentClass);
				logger.info("{} override {} for entity configuration", persistentClass.getClassName(),
						old.getClassName());
			}
			classes.put(persistentClass.getMappedClass().getName(), (PersistentClass) classes.get(entityName));
		}

		@Override
		public void addCollection(Collection collection) throws DuplicateMappingException {
			collections.put(collection.getRole(), collection);
		}

	}

	protected class StmartMetadataSourceQueue extends MetadataSourceQueue {
		protected void syncAnnotatedClasses() {
		}
	}
}
