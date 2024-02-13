package com.eventures.models

enum class InterestType(
    val value: String
) {
    SIMPLE("Simple"),
    COMPOUND("Compound");

    override fun toString(): String = value
}