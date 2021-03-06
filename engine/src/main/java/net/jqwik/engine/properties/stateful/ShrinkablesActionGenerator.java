package net.jqwik.engine.properties.stateful;

import java.util.*;

import net.jqwik.api.*;
import net.jqwik.api.stateful.*;

class ShrinkablesActionGenerator<T> implements ActionGenerator<T> {

	private final Iterator<Shrinkable<Action<T>>> iterator;
	private List<Shrinkable<Action<T>>> shrinkables = new ArrayList<>();

	ShrinkablesActionGenerator(List<Shrinkable<Action<T>>> shrinkables) {
		iterator = shrinkables.iterator();
	}

	@Override
	public Action<T> next(T model) {
		while (iterator.hasNext()) {
			Shrinkable<Action<T>> next = iterator.next();
			if (!next.value().precondition(model)) {
				continue;
			}
			shrinkables.add(next);
			return next.value();
		}
		throw new NoSuchElementException("No more actions available");
	}

	@Override
	public List<Shrinkable<Action<T>>> generated() {
		return shrinkables;
	}
}
