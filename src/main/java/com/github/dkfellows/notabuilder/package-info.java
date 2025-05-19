/**
 * A package of things relating to the not-a-builder pattern.
 * <p>
 * Note that labelling the arguments is always necessary; that can only be
 * done directly by their types when all of them are of different types.
 * Instead, we use static methods to wrap a label on the argument values
 * that the constructor can then inspect to decide what arguments have been
 * provided. This is an ideal case for a SUM type (a sealed type hierarchy).
 */
package com.github.dkfellows.notabuilder;
