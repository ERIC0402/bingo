/* Copyright c 2005-2012.
 * Licensed under GNU  LESSER General Public License, Version 3.
 * http://www.gnu.org/licenses
 */
package org.bingo.common.collection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.apache.commons.beanutils.PropertyUtils;

public final class CollectUtils {

	public static <E> List<E> newArrayList() {
		return new ArrayList<E>();
	}

	public static <E> List<E> newArrayList(int initialCapacity) {
		return new ArrayList<E>(initialCapacity);
	}

	public static <E> List<E> newArrayList(Collection<? extends E> c) {
		return new ArrayList<E>(c);
	}

	public static <E> List<E> newArrayList(E... values) {
		List<E> list = new ArrayList<E>(values.length);
		for (E e : values) {
			list.add(e);
		}
		return list;
	}

	/**
	 * 将一个集合按照固定大小查分成若干个集合。
	 * 
	 * @param list
	 * @param count
	 * @return
	 */
	public static <T> List<List<T>> split(final List<T> list, final int count) {
		List<List<T>> subIdLists = CollectUtils.newArrayList();
		if (list.size() < count) {
			subIdLists.add(list);
		} else {
			int i = 0;
			while (i < list.size()) {
				int end = i + count;
				if (end > list.size()) {
					end = list.size();
				}
				subIdLists.add(list.subList(i, end));
				i += count;
			}
		}
		return subIdLists;
	}

	public static <K, V> Map<K, V> newHashMap() {
		return new HashMap<K, V>();
	}

	public static <K, V> Map<K, V> newConcurrentHashMap() {
		return new ConcurrentHashMap<K, V>();
	}

	public static <E> Queue<E> newConcurrentLinkedQueue() {
		return new ConcurrentLinkedQueue<E>();
	}

	public static <K, V> Map<K, V> newHashMap(Map<? extends K, ? extends V> m) {
		return new HashMap<K, V>(m);
	}

	public static <K, V> Map<K, V> newLinkedHashMap(Map<? extends K, ? extends V> m) {
		return new LinkedHashMap<K, V>(m);
	}

	public static <K, V> Map<K, V> newLinkedHashMap(int size) {
		return new LinkedHashMap<K, V>(size);
	}

	public static <E> Set<E> newHashSet() {
		return new HashSet<E>();
	}

	public static <E> Set<E> newHashSet(E... values) {
		Set<E> set = new HashSet<E>(values.length);
		for (E e : values) {
			set.add(e);
		}
		return set;
	}

	public static <E> Set<E> newHashSet(Collection<? extends E> c) {
		return new HashSet<E>(c);
	}

	public static Map<?, ?> convertToMap(Collection<?> coll, String keyProperty) {
		Map<Object, Object> map = newHashMap();
		for (Object obj : coll) {
			Object key = null;
			try {
				key = PropertyUtils.getProperty(obj, keyProperty);
			} catch (Exception e) {
			}
			map.put(key, obj);
		}
		return map;
	}

	public static Map<?, ?> convertToMap(Collection<?> coll, String keyProperty, String valueProperty) {
		Map<Object, Object> map = newHashMap();
		for (Object obj : coll) {
			Object key = null;
			Object value = null;
			try {
				key = PropertyUtils.getProperty(obj, keyProperty);
				value = PropertyUtils.getProperty(obj, valueProperty);
			} catch (Exception e) {
			}
			map.put(key, value);
		}
		return map;
	}

	public static Map<String, String> toMap(String[]... wordMappings) {
		Map<String, String> mappings = new HashMap<String, String>();
		for (int i = 0; i < wordMappings.length; i++) {
			String singular = wordMappings[i][0];
			String plural = wordMappings[i][1];
			mappings.put(singular, plural);
		}
		return mappings;
	}
}
