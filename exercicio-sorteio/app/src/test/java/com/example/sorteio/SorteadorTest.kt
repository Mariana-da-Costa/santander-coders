package com.example.sorteio

import org.junit.Assert.assertSame
import org.junit.Test

class SorteadorTest {

    @Test
    fun testInputNumberIsSmallest() {
        val sorted = 7
        val inputed = 5
        val result = validar(inputed, sorted)
        val expected = "O número sorteado é maior"
        assertSame(expected, result)
    }

    @Test
    fun testInputNumberIsBiggest() {
        val sorted = 4
        val inputed = 5
        val result = validar(inputed, sorted)
        val expected = "O número sorteado é menor"
        assertSame(expected, result)
    }

    @Test
    fun testInputNumberIsSame() {
        val sorted = 7
        val inputed = 7
        val result = validar(inputed, sorted)
        val expected = "Você acertou!"
        assertSame(expected, result)
    }
}