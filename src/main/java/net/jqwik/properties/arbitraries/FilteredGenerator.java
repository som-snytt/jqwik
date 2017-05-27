package net.jqwik.properties.arbitraries;

import java.util.*;
import java.util.function.*;

import net.jqwik.properties.*;

public class FilteredGenerator<T> implements RandomGenerator<T> {
	private final RandomGenerator<T> toFilter;
	private final Predicate<T> filterPredicate;

	public FilteredGenerator(RandomGenerator<T> toFilter, Predicate<T> filterPredicate) {
		this.toFilter = toFilter;
		this.filterPredicate = filterPredicate;
	}

	@Override
	public Shrinkable<T> next(Random random) {
		while (true) {
			Shrinkable<T> next = toFilter.next(random);
			if (filterPredicate.test(next.value()))
				return new FilteredShrinkable<>(next, filterPredicate);
		}
	}
}
