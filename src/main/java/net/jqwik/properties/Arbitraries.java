package net.jqwik.properties;

import java.util.*;
import java.util.stream.*;

import net.jqwik.properties.arbitraries.*;

public class Arbitraries {

	public static <T> Arbitrary<T> fromGenerator(RandomGenerator<T> generator) {
		return tries -> generator;
	}

	@SafeVarargs
	public static <U> Arbitrary<U> of(U... values) {
		return fromGenerator(RandomGenerators.choose(values));
	}

	public static <T extends Enum> Arbitrary<T> of(Class<T> enumClass) {
		return fromGenerator(RandomGenerators.choose(enumClass));
	}

	public static Arbitrary<Integer> integer() {
		return new IntegerArbitrary();
	}

	public static Arbitrary<Integer> integer(int min, int max) {
		return new IntegerArbitrary(min, max);
	}

	public static Arbitrary<Long> longInteger(long min, long max) {
		return new LongArbitrary();
	}

	public static Arbitrary<Long> longInteger() {
		return new LongArbitrary();
	}

	public static Arbitrary<String> string() {
		return new NStringArbitrary();
	}

	public static Arbitrary<String> string(char[] validChars, int maxSize) {
		return new NStringArbitrary(validChars, maxSize);
	}

	public static Arbitrary<String> string(char[] validChars) {
		return new NStringArbitrary(validChars);
	}

	public static Arbitrary<String> string(char from, char to, int maxSize) {
		return new NStringArbitrary(from, to, maxSize);
	}

	public static Arbitrary<String> string(char from, char to) {
		return new NStringArbitrary(from, to);
	}

	public static <T> Arbitrary<List<T>> listOf(Arbitrary<T> elementArbitrary, int maxSize) {
		return new ListArbitrary<T>(elementArbitrary, maxSize);
	}

	public static <T> Arbitrary<List<T>> listOf(Arbitrary<T> elementArbitrary) {
		return new ListArbitrary<T>(elementArbitrary);
	}

	public static <T> Arbitrary<Set<T>> setOf(Arbitrary<T> elementArbitrary, int maxSize) {
		return new SetArbitrary<>(elementArbitrary, maxSize);
	}

	public static <T> Arbitrary<Set<T>> setOf(Arbitrary<T> elementArbitrary) {
		return new SetArbitrary<>(elementArbitrary);
	}

	public static <T> Arbitrary<Stream<T>> streamOf(Arbitrary<T> elementArbitrary, int maxSize) {
		return new StreamArbitrary<>(elementArbitrary, maxSize);
	}

	public static <T> Arbitrary<Stream<T>> streamOf(Arbitrary<T> elementArbitrary) {
		return new StreamArbitrary<>(elementArbitrary);
	}

	public static <T> Arbitrary<Optional<T>> optionalOf(Arbitrary<T> elementArbitrary) {
		return elementArbitrary.injectNull(0.1).map(Optional::ofNullable);
	}

	public static <A, T> Arbitrary<A> arrayOf(Class<A> arrayClass, Arbitrary<T> elementArbitrary, int maxSize) {
		return new ArrayArbitrary(arrayClass, elementArbitrary, maxSize);
	}

	public static <A, T> Arbitrary<A> arrayOf(Class<A> arrayClass, Arbitrary<T> elementArbitrary) {
		return new ArrayArbitrary(arrayClass, elementArbitrary);
	}


	@SafeVarargs
	public static <T> Arbitrary<T> samples(T... samples) {
		List<Shrinkable<T>> shrinkables = Arrays.stream(samples)
											.map(sample -> Shrinkable.unshrinkable(sample))
											.collect(Collectors.toList());
		return fromGenerator(RandomGenerators.samples(shrinkables));
	}

}
