package com.singularity_code.codebase.util.scope

import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

/**
 * Created by: stefanus
 * 25/09/23 01.36
 * Design by: stefanus.ayudha@gmail.com
 */
@OptIn(ExperimentalContracts::class)
@Suppress("SUBTYPING_BETWEEN_CONTEXT_RECEIVERS")
inline fun <A, B, R> with(
    a: A,
    b: B,
    block: context(A, B) () -> R
): R {
    contract {
        callsInPlace(block, InvocationKind.EXACTLY_ONCE)
    }
    return block(a, b)
}

@OptIn(ExperimentalContracts::class)
@Suppress("SUBTYPING_BETWEEN_CONTEXT_RECEIVERS")
inline fun <A, B, C, R> with(
    a: A,
    b: B,
    c: C,
    block: context(A, B, C) () -> R
): R {
    contract {
        callsInPlace(block, InvocationKind.EXACTLY_ONCE)
    }
    return block(a, b, c)
}

@OptIn(ExperimentalContracts::class)
@Suppress("SUBTYPING_BETWEEN_CONTEXT_RECEIVERS")
inline fun <A, B, C, D, R> with(
    a: A,
    b: B,
    c: C,
    d: D,
    block: context(A, B, C, D) () -> R
): R {
    contract {
        callsInPlace(block, InvocationKind.EXACTLY_ONCE)
    }
    return block(a, b, c, d)
}

@OptIn(ExperimentalContracts::class)
@Suppress("SUBTYPING_BETWEEN_CONTEXT_RECEIVERS")
inline fun <A, B, C, D, E, R> with(
    a: A,
    b: B,
    c: C,
    d: D,
    e: E,
    block: context(A, B, C, D, E) () -> R
): R {
    contract {
        callsInPlace(block, InvocationKind.EXACTLY_ONCE)
    }
    return block(a, b, c, d, e)
}

@OptIn(ExperimentalContracts::class)
@Suppress("SUBTYPING_BETWEEN_CONTEXT_RECEIVERS")
inline fun <A, B, C, D, E, F, R> with(
    a: A,
    b: B,
    c: C,
    d: D,
    e: E,
    f: F,
    block: context(A, B, C, D, E, F) () -> R
): R {
    contract {
        callsInPlace(block, InvocationKind.EXACTLY_ONCE)
    }
    return block(a, b, c, d, e, f)
}

@OptIn(ExperimentalContracts::class)
@Suppress("SUBTYPING_BETWEEN_CONTEXT_RECEIVERS")
inline fun <A, B, C, D, E, F, G, R> with(
    a: A,
    b: B,
    c: C,
    d: D,
    e: E,
    f: F,
    g: G,
    block: context(A, B, C, D, E, F, G) () -> R
): R {
    contract {
        callsInPlace(block, InvocationKind.EXACTLY_ONCE)
    }
    return block(a, b, c, d, e, f, g)
}