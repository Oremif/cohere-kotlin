package org.oremif.cohere.api.types

import kotlinx.serialization.Serializable

@Serializable
public enum class ParameterType(public val value: String) {
    STRING("str"),
    INT("int"),
    FLOAT("float"),
    COMPLEX("complex"),
    LIST("list"),
    TUPLE("tuple"),
    RANGE("range"),
    DICT("dict"),
    SET("set"),
    FROZENSET("frozenset"),
    BOOLEAN("bool"),
    BYTES("bytes"),
    BYTEARRAY("bytearray"),
    MEMORYVIEW("memoryview"),
    NONE("NoneType");

    public override fun toString(): String = this.value
}