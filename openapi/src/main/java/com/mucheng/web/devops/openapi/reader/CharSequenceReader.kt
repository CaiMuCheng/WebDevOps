package com.mucheng.web.devops.openapi.reader

/*
 * Copyright (C) 2013 The Guava Authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

import java.io.IOException
import java.io.Reader
import java.nio.CharBuffer
import java.util.*
import kotlin.math.min


/**
 * A [Reader] that reads the characters in a [CharSequence]. Like `StringReader`,
 * but works with any [CharSequence].
 *
 * @author Colin Decker
 * Copy from: https://github.com/google/guava/blob/master/guava/src/com/google/common/io/CharSequenceReader.java
 */
class CharSequenceReader(seq: CharSequence?) : Reader() {
    private var seq: CharSequence?
    private var pos = 0
    private var mark = 0

    /** Creates a new reader wrapping the given character sequence.  */
    init {
        this.seq = checkNotNull(seq)
    }

    @Throws(IOException::class)
    private fun checkOpen() {
        if (seq == null) {
            throw IOException("reader closed")
        }
    }

    private fun hasRemaining(): Boolean {
        return remaining() > 0
    }

    private fun remaining(): Int {
        Objects.requireNonNull(seq) // safe as long as we call this only after checkOpen
        return seq!!.length - pos
    }

    /*
   * To avoid the need to call requireNonNull so much, we could consider more clever approaches,
   * such as:
   *
   * - Make checkOpen return the non-null `seq`. Then callers can assign that to a local variable or
   *   even back to `this.seq`. However, that may suggest that we're defending against concurrent
   *   mutation, which is not an actual risk because we use `synchronized`.
   * - Make `remaining` require a non-null `seq` argument. But this is a bit weird because the
   *   method, while it would avoid the instance field `seq` would still access the instance field
   *   `pos`.
   */
    @Synchronized
    @Throws(IOException::class)
    override fun read(target: CharBuffer): Int {
        checkOpen()
        Objects.requireNonNull(seq) // safe because of checkOpen
        if (!hasRemaining()) {
            return -1
        }
        val charsToRead = min(target.remaining(), remaining())
        for (i in 0 until charsToRead) {
            target.put(seq!![pos++])
        }
        return charsToRead
    }

    @Synchronized
    @Throws(IOException::class)
    override fun read(): Int {
        checkOpen()
        Objects.requireNonNull(seq) // safe because of checkOpen
        return if (hasRemaining()) seq!![pos++].code else -1
    }

    @Synchronized
    @Throws(IOException::class)
    override fun read(cbuf: CharArray, off: Int, len: Int): Int {
        checkOpen()
        Objects.requireNonNull(seq) // safe because of checkOpen
        if (!hasRemaining()) {
            return -1
        }
        val charsToRead = min(len, remaining())
        for (i in 0 until charsToRead) {
            cbuf[off + i] = seq!![pos++]
        }
        return charsToRead
    }

    @Synchronized
    @Throws(IOException::class)
    override fun skip(n: Long): Long {
        checkOpen()
        val charsToSkip = min(remaining().toLong(), n).toInt() // safe because remaining is an int
        pos += charsToSkip
        return charsToSkip.toLong()
    }

    @Synchronized
    @Throws(IOException::class)
    override fun ready(): Boolean {
        checkOpen()
        return true
    }

    override fun markSupported(): Boolean {
        return true
    }

    @Synchronized
    @Throws(IOException::class)
    override fun mark(readAheadLimit: Int) {
        checkOpen()
        mark = pos
    }

    @Synchronized
    @Throws(IOException::class)
    override fun reset() {
        checkOpen()
        pos = mark
    }

    @Synchronized
    @Throws(IOException::class)
    override fun close() {
        seq = null
    }
}