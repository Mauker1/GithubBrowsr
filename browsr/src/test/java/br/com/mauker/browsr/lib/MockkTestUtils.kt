package br.com.mauker.browsr.lib

import io.mockk.MockKVerificationScope
import io.mockk.coVerify
import io.mockk.verify

fun verifyOnce(verificationBlock: MockKVerificationScope.() -> Unit) =
        verify(exactly = 1, verifyBlock = verificationBlock)

fun coVerifyOnce(verificationBlock: suspend MockKVerificationScope.() -> Unit) =
        coVerify(exactly = 1, verifyBlock = verificationBlock)

fun verifyNever(verificationBlock: MockKVerificationScope.() -> Unit) =
        verify(exactly = 0, verifyBlock = verificationBlock)

fun coVerifyNever(verificationBlock: suspend MockKVerificationScope.() -> Unit) =
        coVerify(exactly = 0, verifyBlock = verificationBlock)